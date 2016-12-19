import java.util.*;

/**
 * Created by qcrao on 16/12/14.
 */
public class KeyCountryDeadWeightQuery {
    List<Port_Port> port_ports;
    String country_code;
    MonthCountryDeadWeight[] monthCountryDeadWeights = new MonthCountryDeadWeight[13]; //12 months + all year
    int year;
    List<CountryDeadWeight> res = new ArrayList<CountryDeadWeight>();

    public KeyCountryDeadWeightQuery(List<Port_Port> port_ports, String country_code, int year) {
        this.port_ports = port_ports;
        this.country_code = country_code;
        this.year = year;
        countDeadWeight();
    }

    private void countDeadWeight()
    {
        Calendar calendar = Calendar.getInstance();

        for (Port_Port port_port : port_ports)
        {
            calendar.setTimeInMillis((long)((long)port_port.getStart_Datetime()*1000));
            int src_month = calendar.get(Calendar.MONTH); //0-11

            calendar.setTimeInMillis((long)((long)port_port.getEnd_Datetime()*1000));
            int dst_month = calendar.get(Calendar.MONTH);

            String src_country_code = port_port.getCountry_Code_Src();
            String dst_country_code = port_port.getCountry_Code_Dst();

            if (!src_country_code.equals(country_code) && !dst_country_code.equals(country_code))
                continue;
            else if (src_country_code.equals(country_code) && dst_country_code.equals(country_code))
                continue;

            else if (src_country_code.equals(country_code))
            {
                monthCountryDeadWeights[src_month].addWeight(port_port.getCountry_Code_Dst(), port_port.getShip_Dead_Weight_Tonnage());
                monthCountryDeadWeights[12].addWeight(port_port.getCountry_Code_Dst(), port_port.getShip_Dead_Weight_Tonnage());
            }

            else
            {
                monthCountryDeadWeights[dst_month].addWeight(port_port.getCountry_Code_Src(), port_port.getShip_Dead_Weight_Tonnage());
                monthCountryDeadWeights[12].addWeight(port_port.getCountry_Code_Src(), port_port.getShip_Dead_Weight_Tonnage());
            }
        }

    }

    public static Comparator<CountryDeadWeight> Country_deadWeightComparator = new Comparator<CountryDeadWeight>(){

        @Override
        public int compare(CountryDeadWeight p1, CountryDeadWeight p2) {
            return (int) (p2.getTonnage() - p1.getTonnage());
        }
    };

    private void getTopCountry(MonthCountryDeadWeight monthCountryDeadWeight)
    {
        Queue<CountryDeadWeight> q = new PriorityQueue<CountryDeadWeight>(1, Country_deadWeightComparator);

        Iterator<Map.Entry<String, Long>> iter = monthCountryDeadWeight.getMp().entrySet().iterator();
        Map.Entry<String, Long> entry;
        while (iter.hasNext()) {
            entry = iter.next();
            String code = entry.getKey();
            long tonnage = entry.getValue();

            q.add(new CountryDeadWeight(code, tonnage));

        }

        //for (int i = 0; i < 10 && !q.isEmpty(); ++i)
        for (int i = 0; i < 10; ++i)
            res.add(q.poll());

    }

    public List<CountryDeadWeight> getRes()
    {
        for (int i = 0; i < 13; ++i)
        {
            getTopCountry(monthCountryDeadWeights[i]);
        }
        return res;
    }
}

class CountryDeadWeight
{
    String Country_code;
    long tonnage;

    public long getTonnage() {
        return tonnage;
    }

    public CountryDeadWeight(String country_code, long tonnage) {
        Country_code = country_code;
        this.tonnage = tonnage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryDeadWeight that = (CountryDeadWeight) o;

        return Country_code != null ? Country_code.equals(that.Country_code) : that.Country_code == null;

    }

    @Override
    public int hashCode() {
        return Country_code != null ? Country_code.hashCode() : 0;
    }
}


class MonthCountryDeadWeight
{

    HashMap<String, Long> mp = new HashMap<String, Long>();

    public HashMap<String, Long> getMp() {
        return mp;
    }

    public void addWeight(String country_code, long weight)
    {

        if (!mp.containsKey(country_code))
            mp.put(country_code, 0L);
        mp.put(country_code, mp.get(country_code)+weight);
    }

}

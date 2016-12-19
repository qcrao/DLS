import javax.sound.sampled.Port;
import java.util.*;

/**
 * Created by qcrao on 16/11/23.
 * 港口-港口吞吐量(可选时间,国家,地区)
 */
public class PortDeadQuery {

    boolean hasComputed = false;
    private HashMap<Port_Src_Dest, Port_Dead_Weight> mp_Export_dead_weights = new HashMap<Port_Src_Dest, Port_Dead_Weight>();
    private HashMap<Port_Src_Dest, Port_Dead_Weight> mp_Import_dead_weights = new HashMap<Port_Src_Dest, Port_Dead_Weight>();

    private List<Port_Port> port_ports;
    private Port_Port_Arg port_port_arg;

    private Queue<Port_Dead_Weight> ExPort_Dead_WeightPriorityQueue;
    private Queue<Port_Dead_Weight> ImPort_Dead_WeightPriorityQueue;
    private Queue<Port_Dead_Weight> Total_Dead_WeightPriorityQueue;

    private HashMap<Integer, Long> mp_Export_singlePort = new HashMap<Integer, Long>();
    private HashMap<Integer, Long> mp_Import_singlePort = new HashMap<Integer, Long>();

    public PortDeadQuery(List<Port_Port> port_ports, Port_Port_Arg port_port_arg) {
        this.port_ports = port_ports;
        this.port_port_arg = port_port_arg;
        int outputSZ = port_port_arg.getMax_return_strip();
        if (outputSZ == 0) outputSZ = 1;

        this.ExPort_Dead_WeightPriorityQueue = new PriorityQueue<Port_Dead_Weight>(outputSZ, deadWeightComparator);
        this.ImPort_Dead_WeightPriorityQueue = new PriorityQueue<Port_Dead_Weight>(outputSZ, deadWeightComparator);

        countDeadWeight(port_ports, port_port_arg);
    }

    private Port_Dead_Weight getPortPortDeadWeight(Port_Port port_port, HashMap<Port_Src_Dest, Port_Dead_Weight> mp_port_dead_weights)
    {
        Port_Src_Dest port_src_dest = new Port_Src_Dest(port_port.getWorld_port_index_number_src(), port_port.getWorld_port_index_number_dst());

        if (!mp_port_dead_weights.containsKey(port_src_dest))
            mp_port_dead_weights.put(port_src_dest, new Port_Dead_Weight(port_port.getWorld_port_index_number_src(), port_port.getWorld_port_index_number_dst()));

        return mp_port_dead_weights.get(port_src_dest);
    }

    private Port_Dead_Weight getExportDeadWeight(Port_Port port_port)
    {
        return getPortPortDeadWeight(port_port, mp_Export_dead_weights);
    }

    private Port_Dead_Weight getImportDeadWeight(Port_Port port_port)
    {
        return getPortPortDeadWeight(port_port, mp_Import_dead_weights);
    }

    private void countExportDeadWeight(Port_Port port_port)
    {
        Port_Dead_Weight port_dead_weight = getExportDeadWeight(port_port);
        port_dead_weight.add_tonnage(port_port.getShip_Dead_Weight_Tonnage());
        port_dead_weight.add_shipInOutTimes();

    }

    private void countImportDeadWeight(Port_Port port_port)
    {
        Port_Dead_Weight port_dead_weight = getImportDeadWeight(port_port);
        port_dead_weight.add_tonnage(port_port.getShip_Dead_Weight_Tonnage());
        port_dead_weight.add_shipInOutTimes();

    }



    private void countDeadWeight(List<Port_Port> port_ports, Port_Port_Arg port_port_arg)
    {
        int World_port_index_number_src = port_port_arg.getWorld_port_index_number_src();
        int World_port_index_number_dst = port_port_arg.getWorld_port_index_number_dst();

        int Region_Index_Src = port_port_arg.getRegion_Index_Src();
        int Region_Index_Dst = port_port_arg.getRegion_Index_Dst();

        String Country_Code_Src = port_port_arg.getCountry_Code_Src();
        String Country_Code_Dst = port_port_arg.getCountry_Code_Dst();

        int Particular_year = port_port_arg.getParticular_year();
        int Months = port_port_arg.getMonths();

        HashSet<Integer> monthsToQuery = new HashSet<Integer>();
        if (Months == 0)
        {
            for (int i = 1; i <= 12; ++i)
                monthsToQuery.add(i);
        }
        else if (!(Months == 21 || Months == 22 || Months == 23 || Months == 24))
            monthsToQuery.add(Months);

        else if (Months == 21 || Months == 22 || Months == 23 || Months == 24)
        {
            for (int i = 1; i <= 3; ++i)
                monthsToQuery.add(3*(Months-21)+i);
        }
        else
            System.err.println("Month error!");

        byte Ship_Type_AIS = port_port_arg.getShip_Type_AIS();
        byte Ship_Type_IMO = port_port_arg.getShip_Type_IMO();

        int Ship_Dead_Weight_Tonnage = port_port_arg.getMinShip_Dead_Weight_Tonnage();

        List<Port_Port> res = new ArrayList<Port_Port>();

        Calendar calendar = Calendar.getInstance();

        for (Port_Port port_port : port_ports)
        {

            while (true)
            {
                if (Ship_Type_AIS != 0 && Ship_Type_AIS != port_port.getShip_Type_AIS())
                    break;
                if (Ship_Type_IMO != 0 && Ship_Type_IMO != port_port.getShip_Type_IMO())
                    break;

                if (World_port_index_number_src != 0 && World_port_index_number_src != port_port.getWorld_port_index_number_src())
                    break;
                if (Region_Index_Src != 0 && Region_Index_Src != port_port.getRegion_Index_Src())
                    break;
                if (!Country_Code_Src.isEmpty() && !Country_Code_Src.equals(port_port.getCountry_Code_Src()))
                    break;
                int utc_start_time = port_port.getStart_Datetime();
                calendar.setTimeInMillis((long)((long)utc_start_time*1000));
                if (Particular_year == 0 || Particular_year == calendar.get(Calendar.YEAR) && monthsToQuery.contains(calendar.get(Calendar.MONTH)+1))
                    countExportDeadWeight(port_port);
                break;

            }


            if (Ship_Type_AIS != 0 && Ship_Type_AIS != port_port.getShip_Type_AIS())
                continue;
            if (Ship_Type_IMO != 0 && Ship_Type_IMO != port_port.getShip_Type_IMO())
                continue;
            if (World_port_index_number_dst != 0 && World_port_index_number_dst != port_port.getWorld_port_index_number_dst())
                continue;
            if (Region_Index_Dst != 0 && Region_Index_Dst != port_port.getRegion_Index_Dst())
                continue;
            if (!Country_Code_Dst.isEmpty() && !Country_Code_Dst.equals(port_port.getCountry_Code_Dst()))
                continue;
            int utc_end_time = port_port.getEnd_Datetime();
            calendar.setTimeInMillis((long)((long)utc_end_time*1000));
            if (Particular_year == 0 || Particular_year == calendar.get(Calendar.YEAR) && monthsToQuery.contains(calendar.get(Calendar.MONTH)+1))
                countImportDeadWeight(port_port);


        }
    }

    private List<Port_Dead_Weight> singlePort_sort(HashMap<Integer, Integer> singlePortDeadWeight, Queue<Port_Dead_Weight> Dead_WeightPriorityQueue)
    {
        Iterator<Map.Entry<Integer, Integer>> iter = singlePortDeadWeight.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        while (iter.hasNext()) {
            entry = iter.next();
            int wpi_num = entry.getKey();
            int tonnage = entry.getValue();

            if (tonnage > port_port_arg.getMinShip_Dead_Weight_Tonnage())
            {
                Port_Dead_Weight port_dead_weight = new Port_Dead_Weight(wpi_num, wpi_num);
                port_dead_weight.setTonnage(tonnage);
                Dead_WeightPriorityQueue.add(port_dead_weight);
            }

        }

        List<Port_Dead_Weight> res = new ArrayList<Port_Dead_Weight>();
        for (int i = 0; (port_port_arg.getMax_return_strip() == 0 || i < port_port_arg.getMax_return_strip()) && !Dead_WeightPriorityQueue.isEmpty(); ++i)
            res.add(Dead_WeightPriorityQueue.poll());

        System.out.println("result size : " + res.size());
        return res;
    }

    private List<Port_Dead_Weight> getRes_SinglePort_Export_DeadWeight(HashMap<Port_Src_Dest, Port_Dead_Weight> mp_dead_weights)
    {
        HashMap<Integer, Integer> singlePortDeadWeight = new HashMap<Integer, Integer>();
        for (Port_Dead_Weight port_dead_weight : mp_dead_weights.values())
        {
            int wpi_start = port_dead_weight.getPort_start_wpi();
            if (!mp_Export_singlePort.containsKey(wpi_start))
                mp_Export_singlePort.put(wpi_start, 0L);
            mp_Export_singlePort.put(wpi_start, mp_Export_singlePort.get(wpi_start)+port_dead_weight.getTonnage());
        }
        return singlePort_sort(singlePortDeadWeight, ExPort_Dead_WeightPriorityQueue);
    }
    private List<Port_Dead_Weight> getRes_SinglePort_Import_DeadWeight(HashMap<Port_Src_Dest, Port_Dead_Weight> mp_dead_weights)
    {
        HashMap<Integer, Integer> singlePortDeadWeight = new HashMap<Integer, Integer>();
        for (Port_Dead_Weight port_dead_weight : mp_dead_weights.values())
        {
            int wpi_end = port_dead_weight.getPort_start_wpi();
            if (!mp_Import_singlePort.containsKey(wpi_end))
                mp_Import_singlePort.put(wpi_end, 0L);
            mp_Import_singlePort.put(wpi_end, mp_Import_singlePort.get(wpi_end)+port_dead_weight.getTonnage());
        }

        return singlePort_sort(singlePortDeadWeight, ImPort_Dead_WeightPriorityQueue);


    }


    private List<Port_Dead_Weight> getRes_portDeadWeight(HashMap<Port_Src_Dest, Port_Dead_Weight> mp_dead_weights, Queue<Port_Dead_Weight> Dead_WeightPriorityQueue)
    {
        System.out.println("mp_dead_weights"+mp_dead_weights.size());

        for (Port_Dead_Weight port_dead_weight : mp_dead_weights.values())
        {
            System.out.println("--");
            System.out.println(port_dead_weight.toString());
            System.out.println("--");

        if (port_dead_weight.getTonnage() > port_port_arg.getMinShip_Dead_Weight_Tonnage())
                Dead_WeightPriorityQueue.add(port_dead_weight);

        }

        List<Port_Dead_Weight> res = new ArrayList<Port_Dead_Weight>();
        for (int i = 0; (port_port_arg.getMax_return_strip() == 0 || i < port_port_arg.getMax_return_strip()) && !Dead_WeightPriorityQueue.isEmpty(); ++i)
            res.add(Dead_WeightPriorityQueue.poll());

        System.out.println("result size : " + res.size());
        return res;
    }

    public List<Port_Dead_Weight> getRes_ExportDeadWeight()
    {
        System.out.println("getRes_ExportDeadWeight ");
        return getRes_SinglePort_Export_DeadWeight(mp_Export_dead_weights);
    }

    public List<Port_Dead_Weight> getRes_ImportDeadWeight()
    {
        return getRes_SinglePort_Import_DeadWeight(mp_Import_dead_weights);
    }

    //此接口调完之后,内部数据结构已被破坏,不能再使用
    public List<Port_Dead_Weight> getRes_TotalDeadWeight()
    {
        Total_Dead_WeightPriorityQueue = new PriorityQueue<>(1);
        for (Port_Dead_Weight port_dead_weight : mp_Import_dead_weights.values())
        {
            Port_Src_Dest port_src_dest = new Port_Src_Dest(port_dead_weight.getPort_end_wpi(), port_dead_weight.getPort_start_wpi());
            if (mp_Export_dead_weights.containsKey(port_src_dest))
            {
                port_dead_weight.add_tonnage(mp_Export_dead_weights.get(port_src_dest).getTonnage());
                mp_Export_dead_weights.remove(port_src_dest);
            }
            Total_Dead_WeightPriorityQueue.add(port_dead_weight);
        }

        for (Port_Dead_Weight port_dead_weight : mp_Export_dead_weights.values())
        {
            Total_Dead_WeightPriorityQueue.add(port_dead_weight);
        }

        List<Port_Dead_Weight> res = new ArrayList<Port_Dead_Weight>();
        for (int i = 0; (port_port_arg.getMax_return_strip() == 0 || i < port_port_arg.getMax_return_strip()) && !Total_Dead_WeightPriorityQueue.isEmpty(); ++i)
            res.add(Total_Dead_WeightPriorityQueue.poll());

        System.out.println("getRes_TotalDeadWeight() result size : " + res.size());
        return res;
    }

    public static Comparator<Port_Dead_Weight> deadWeightComparator = new Comparator<Port_Dead_Weight>(){

        @Override
        public int compare(Port_Dead_Weight p1, Port_Dead_Weight p2) {
            return (int) (p2.getTonnage() - p1.getTonnage());
        }
    };


}

class Port_Src_Dest
{
    int src_wpi, dest_wpi;

    public Port_Src_Dest(int src_wpi, int dest_wpi) {
        this.src_wpi = src_wpi;
        this.dest_wpi = dest_wpi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Port_Src_Dest that = (Port_Src_Dest) o;

        if (src_wpi != that.src_wpi) return false;
        return dest_wpi == that.dest_wpi;

    }

    @Override
    public int hashCode() {
        int result = src_wpi;
        result = 31 * result + dest_wpi;
        return result;
    }

    @Override
    public String toString() {
        return "Port_Src_Dest{" +
                "src_wpi=" + src_wpi +
                ", dest_wpi=" + dest_wpi +
                '}';
    }
}



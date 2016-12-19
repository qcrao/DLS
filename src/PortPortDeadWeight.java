import java.util.HashMap;

/**
 * Created by qcrao on 16/12/12.
 */
public class PortPortDeadWeight {
    private HashMap<CountUnit_PortMonthShipType, Long> mp_DeadWeight = new HashMap<CountUnit_PortMonthShipType, Long>();

    public void count(CountUnit_PortMonthShipType countUnit_portMonthShipType, int tonnage)
    {
        if (!mp_DeadWeight.containsKey(countUnit_portMonthShipType))
            mp_DeadWeight.put(countUnit_portMonthShipType, 0L);
        mp_DeadWeight.put(countUnit_portMonthShipType, mp_DeadWeight.get(countUnit_portMonthShipType)+tonnage);
    }




}

class CountUnit_PortMonthShipType
{
    int src_wpi, dst_wpi;
    int src_month, dst_month;
    int shipType; //0货轮, 1油轮

    public CountUnit_PortMonthShipType(int src_wpi, int dst_wpi, int src_month, int dst_month, int shipType) {
        this.src_wpi = src_wpi;
        this.dst_wpi = dst_wpi;
        this.src_month = src_month;
        this.dst_month = dst_month;
        this.shipType = shipType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountUnit_PortMonthShipType that = (CountUnit_PortMonthShipType) o;

        if (src_wpi != that.src_wpi) return false;
        if (dst_wpi != that.dst_wpi) return false;
        if (src_month != that.src_month) return false;
        if (dst_month != that.dst_month) return false;
        return shipType == that.shipType;

    }

    @Override
    public int hashCode() {
        int result = src_wpi;
        result = 31 * result + dst_wpi;
        result = 31 * result + src_month;
        result = 31 * result + dst_month;
        result = 31 * result + shipType;
        return result;
    }
}

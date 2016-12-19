/**
 * Created by qcrao on 16/11/25.
 */
public class Port_Dead_Weight {
    private long tonnage = 0;
    private int shipInOutTimes = 0;
    private int port_start_wpi, port_end_wpi;

    public Port_Dead_Weight(int port_start_wpi, int port_end_wpi) {
        this.port_start_wpi = port_start_wpi;
        this.port_end_wpi = port_end_wpi;
    }

    public void add_tonnage(long tonnage)
    {
        this.tonnage += tonnage;
    }

    public void add_shipInOutTimes()
    {
        ++shipInOutTimes;
    }

    public long getTonnage() {
        return tonnage;
    }

    @Override
    public String toString() {
        return "Port_Dead_Weight{" +
                "tonnage=" + tonnage +
                ", shipInOutTimes=" + shipInOutTimes +
                ", port_start_wpi=" + port_start_wpi +
                ", port_end_wpi=" + port_end_wpi +
                '}';
    }

    public void setTonnage(long tonnage) {
        this.tonnage = tonnage;
    }

    public int getShipInOutTimes() {
        return shipInOutTimes;
    }

    public void setShipInOutTimes(int shipInOutTimes) {
        this.shipInOutTimes = shipInOutTimes;
    }

    public int getPort_start_wpi() {
        return port_start_wpi;
    }

    public void setPort_start_wpi(int port_start_wpi) {
        this.port_start_wpi = port_start_wpi;
    }

    public int getPort_end_wpi() {
        return port_end_wpi;
    }

    public void setPort_end_wpi(int port_end_wpi) {
        this.port_end_wpi = port_end_wpi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Port_Dead_Weight that = (Port_Dead_Weight) o;

        if (port_start_wpi != that.port_start_wpi) return false;
        return port_end_wpi == that.port_end_wpi;

    }

    @Override
    public int hashCode() {
        int result = port_start_wpi;
        result = 31 * result + port_end_wpi;
        return result;
    }
}

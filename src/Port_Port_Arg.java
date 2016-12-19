
/**
 * Created by Administrator on 2016/11/23.
 */
public class Port_Port_Arg {
    private int World_port_index_number_dst;
    private int World_port_index_number_src;
    private String Country_Code_Src;
    private String Country_Code_Dst;
    private int Region_Index_Src;
    private int Region_Index_Dst;
    private byte Ship_Type_AIS;
    private byte Ship_Type_IMO;
    private int Max_return_strip;
    private int Particular_year; //0，表示任意年份
    private int Months; //1表示一月

    int minShip_Dead_Weight_Tonnage;

    public int getMinShip_Dead_Weight_Tonnage() {
        return minShip_Dead_Weight_Tonnage;
    }

    public void setMinShip_Dead_Weight_Tonnage(int minShip_Dead_Weight_Tonnage) {
        this.minShip_Dead_Weight_Tonnage = minShip_Dead_Weight_Tonnage;
    }

    public int getParticular_year() {
        return Particular_year;
    }

    public void setParticular_year(int particular_year) {
        Particular_year = particular_year;
    }

    public int getMonths() {
        return Months;
    }

    public void setMonths(int months) {
        Months = months;
    }

    public int getWorld_port_index_number_dst() {
        return World_port_index_number_dst;
    }

    public void setWorld_port_index_number_dst(int world_port_index_number_dst) {
        World_port_index_number_dst = world_port_index_number_dst;
    }

    public int getWorld_port_index_number_src() {
        return World_port_index_number_src;
    }

    public void setWorld_port_index_number_src(int world_port_index_number_src) {
        World_port_index_number_src = world_port_index_number_src;
    }

    public String getCountry_Code_Src() {
        return Country_Code_Src;
    }

    public void setCountry_Code_Src(String country_Code_Src) {
        Country_Code_Src = country_Code_Src;
    }

    public String getCountry_Code_Dst() {
        return Country_Code_Dst;
    }

    public void setCountry_Code_Dst(String country_Code_Dst) {
        Country_Code_Dst = country_Code_Dst;
    }

    public int getRegion_Index_Src() {
        return Region_Index_Src;
    }

    public void setRegion_Index_Src(int region_Index_Src) {
        Region_Index_Src = region_Index_Src;
    }

    public int getRegion_Index_Dst() {
        return Region_Index_Dst;
    }

    public void setRegion_Index_Dst(int region_Index_Dst) {
        Region_Index_Dst = region_Index_Dst;
    }

    public byte getShip_Type_AIS() {
        return Ship_Type_AIS;
    }

    public void setShip_Type_AIS(byte ship_Type_AIS) {
        Ship_Type_AIS = ship_Type_AIS;
    }

    public byte getShip_Type_IMO() {
        return Ship_Type_IMO;
    }

    public void setShip_Type_IMO(byte ship_Type_IMO) {
        Ship_Type_IMO = ship_Type_IMO;
    }

    public int getMax_return_strip() {
        return Max_return_strip;
    }

    public void setMax_return_strip(int max_return_strip) {
        Max_return_strip = max_return_strip;
    }

    @Override
    public String toString() {
        return "Port_Port_Arg{" +
                "World_port_index_number_dst=" + World_port_index_number_dst +
                ", World_port_index_number_src=" + World_port_index_number_src +
                ", Country_Code_Src='" + Country_Code_Src + '\'' +
                ", Country_Code_Dst='" + Country_Code_Dst + '\'' +
                ", Region_Index_Src=" + Region_Index_Src +
                ", Region_Index_Dst=" + Region_Index_Dst +
                ", Ship_Type_AIS=" + Ship_Type_AIS +
                ", Ship_Type_IMO=" + Ship_Type_IMO +
                ", Max_return_strip=" + Max_return_strip +
                ", Particular_year=" + Particular_year +
                ", Months=" + Months +

                '}';
    }
}

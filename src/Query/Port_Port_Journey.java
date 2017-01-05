package Query;

import java.util.Calendar;

/**
 * Created by FCC on 2016/11/22.
 */
public class Port_Port_Journey {

    //some added properties
    private int src_year, src_month;
    private int dst_year, dst_month;
    private int shipTypeAIS;
    private int tradeType;

    private void setTradeType()
    {
        if (getCountry_Code_Src().equals(getCountry_Code_Dst()))
            tradeType = Query_Arg.TRADE_IN_COUNTYR; //trade in code
        else
            tradeType = 2;
    }

    private Calendar calendar = Calendar.getInstance();
    private void setDstYearQuarterMonth(int utcTime)
    {
        calendar.setTimeInMillis(((long)((long)utcTime*1000)));
        setDst_year(calendar.get(Calendar.YEAR));
        setDst_month(calendar.get(Calendar.MONTH)+1); //1-12
    }

    private void setSrcYearQuarterMonth(int utcTime)
    {
        calendar.setTimeInMillis(((long)((long)utcTime*1000)));
        setSrc_year(calendar.get(Calendar.YEAR));
        setSrc_month(calendar.get(Calendar.MONTH)+1); //1-12
    }

    private void setYearQuarterMonth()
    {
        setSrcYearQuarterMonth(getStart_Datetime());
        setDstYearQuarterMonth(getEnd_Datetime());
    }

    public void setAddedProperties()
    {
        setYearQuarterMonth();
        setTradeType();
    }

    public int getSrc_year() {
        return src_year;
    }

    private void setSrc_year(int src_year) {
        this.src_year = src_year;
    }

    public int getSrc_month() {
        return src_month;
    }

    private void setSrc_month(int src_month) {
        this.src_month = src_month;
    }

    public int getDst_year() {
        return dst_year;
    }

    private void setDst_year(int dst_year) {
        this.dst_year = dst_year;
    }

    public int getDst_month() {
        return dst_month;
    }

    private void setDst_month(int dst_month) {
        this.dst_month = dst_month;
    }

    public int getShipTypeAIS() {
        return shipTypeAIS;
    }

    public void setShipTypeAIS(int shipTypeAIS) {
        this.shipTypeAIS = shipTypeAIS;
    }

    public int getTradeType() {
        return tradeType;
    }

    //end

    long Ship_Dead_Weight_Tonnage;

    public long getShip_Dead_Weight_Tonnage() {
        return Ship_Dead_Weight_Tonnage;
    }

    public void setShip_Dead_Weight_Tonnage(long ship_Dead_Weight_Tonnage) {
        Ship_Dead_Weight_Tonnage = ship_Dead_Weight_Tonnage;
    }

    private int World_port_index_number_src;//起始港口ID

    private int World_port_index_number_dst;//目的港口的ID

    private int Start_Datetime;//出发的utc时间

    private int End_Datetime;//达到目的港的UTC时间

    private int Ship_ID;//不明确（暂定为船的类型号）

    private int ClassType;//A类设备还是B类设备。A:0  B:1  未知：2

    private int MMSI;//船舶海上移动识别码


    private int Ship_Type_IMO;//劳氏数据库中的船舶细分类型。0：默认值

    private String Country_Code_Src;//起始国家代码

    private String Country_Code_Dst;//目的国家地区代码

    private int Region_Index_Src;//起始港口所属的地区号

    private int Region_Index_Dst;//目的港口所属的地区号


    public int getWorld_port_index_number_src() {
        return World_port_index_number_src;
    }

    public void setWorld_port_index_number_src(int world_port_index_number_src) {
        World_port_index_number_src = world_port_index_number_src;
    }

    public int getWorld_port_index_number_dst() {
        return World_port_index_number_dst;
    }

    public void setWorld_port_index_number_dst(int world_port_index_number_dst) {
        World_port_index_number_dst = world_port_index_number_dst;
    }

    public int getStart_Datetime() {
        return Start_Datetime;
    }

    public void setStart_Datetime(int start_Datetime) {
        Start_Datetime = start_Datetime;
    }

    public int getEnd_Datetime() {
        return End_Datetime;
    }

    public void setEnd_Datetime(int end_Datetime) {
        End_Datetime = end_Datetime;
    }

    public int getShip_ID() {
        return Ship_ID;
    }

    public void setShip_ID(int ship_ID) {
        Ship_ID = ship_ID;
    }

    public int getClassType() {
        return ClassType;
    }

    public void setClassType(int classType) {
        ClassType = classType;
    }

    public int getMMSI() {
        return MMSI;
    }

    public void setMMSI(int MMSI) {
        this.MMSI = MMSI;
    }

    public int getShip_Type_IMO() {
        return Ship_Type_IMO;
    }

    public void setShip_Type_IMO(int ship_Type_IMO) {
        Ship_Type_IMO = ship_Type_IMO;
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

}

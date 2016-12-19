
/**
 * Created by FCC on 2016/11/22.
 */
public class Port_Port {

    int Ship_Dead_Weight_Tonnage;

    public int getShip_Dead_Weight_Tonnage() {
        return Ship_Dead_Weight_Tonnage;
    }

    public void setShip_Dead_Weight_Tonnage(int ship_Dead_Weight_Tonnage) {
        Ship_Dead_Weight_Tonnage = ship_Dead_Weight_Tonnage;
    }

    private int id;//留备用

    private int World_port_index_number_src;//起始港口ID
    private int min_World_port_index_number_src;
    private int max_World_port_index_number_src;

    private int World_port_index_number_dst;//目的港口的ID
    private int min_World_port_index_number_dst;
    private int max_World_port_index_number_dst;

    private int Start_Datetime;//出发的utc时间
    private int min_Start_Datetime;
    private int max_Start_Datetime;

    private int End_Datetime;//达到目的港的UTC时间
    private int min_End_Datetime;
    private int max_End_Datetime;

    private int Ship_ID;//不明确（暂定为船的类型号）
    private int min_Ship_ID;
    private int max_Ship_ID;

    private byte ClassType;//A类设备还是B类设备。A:0  B:1  未知：2

    private int MMSI;//船舶海上移动识别码
    private byte Ship_Type_AIS;//AIS静态消息中记录的船舶类型
    private byte min_Ship_Type_AIS;
    private byte max_Ship_Type_AIS;// max value = 255(11111111)

    private byte Ship_Type_IMO;//劳氏数据库中的船舶细分类型。0：默认值
    private byte min_Ship_Type_IMO;
    private byte max_Ship_Type_IMO;

    private String Country_Code_Src;//起始国家代码
    private String min_Country_Code_Src;
    private String max_Country_Code_Src;

    private String Country_Code_Dst;//目的国家地区代码
    private String min_Country_Code_Dst;
    private String max_Country_Code_Dst;

    private int Region_Index_Src;//起始港口所属的地区号
    private int min_Region_Index_Src;
    private int max_Region_Index_Src;

    private int Region_Index_Dst;//目的港口所属的地区号
    private int min_Region_Index_Dst;
    private int max_Region_Index_Dst;

    private int Min_load_capacity;//最低载重吨位
    private int default_Min_load_capacity; //default value is 0

    private int Max_return_strip;//最大返回条数
    private int default_Max_return_strip;//default value 1000000

    private int Particular_year;//0，表示任意年份
    private int min_Particular_year;
    private int max_Particular_year;

    private int Months;//1表示一月
    private int min_Month;
    private int max_Month;

    private int Quarter;//1表示1季度，0表示任意
    private int min_Quarter;
    private int max_Quarter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorld_port_index_number_src() {
        return World_port_index_number_src;
    }

    public void setWorld_port_index_number_src(int world_port_index_number_src) {
        World_port_index_number_src = world_port_index_number_src;
    }

    public int getMin_World_port_index_number_src() {
        return min_World_port_index_number_src;
    }

    public void setMin_World_port_index_number_src(int min_World_port_index_number_src) {
        this.min_World_port_index_number_src = min_World_port_index_number_src;
    }

    public int getMax_World_port_index_number_src() {
        return max_World_port_index_number_src;
    }

    public void setMax_World_port_index_number_src(int max_World_port_index_number_src) {
        this.max_World_port_index_number_src = max_World_port_index_number_src;
    }

    public int getWorld_port_index_number_dst() {
        return World_port_index_number_dst;
    }

    public void setWorld_port_index_number_dst(int world_port_index_number_dst) {
        World_port_index_number_dst = world_port_index_number_dst;
    }

    public int getMin_World_port_index_number_dst() {
        return min_World_port_index_number_dst;
    }

    public void setMin_World_port_index_number_dst(int min_World_port_index_number_dst) {
        this.min_World_port_index_number_dst = min_World_port_index_number_dst;
    }

    public int getMax_World_port_index_number_dst() {
        return max_World_port_index_number_dst;
    }

    public void setMax_World_port_index_number_dst(int max_World_port_index_number_dst) {
        this.max_World_port_index_number_dst = max_World_port_index_number_dst;
    }

    public int getStart_Datetime() {
        return Start_Datetime;
    }

    public void setStart_Datetime(int start_Datetime) {
        Start_Datetime = start_Datetime;
    }

    public int getMin_Start_Datetime() {
        return min_Start_Datetime;
    }

    public void setMin_Start_Datetime(int min_Start_Datetime) {
        this.min_Start_Datetime = min_Start_Datetime;
    }

    public int getMax_Start_Datetime() {
        return max_Start_Datetime;
    }

    public void setMax_Start_Datetime(int max_Start_Datetime) {
        this.max_Start_Datetime = max_Start_Datetime;
    }

    public int getEnd_Datetime() {
        return End_Datetime;
    }

    public void setEnd_Datetime(int end_Datetime) {
        End_Datetime = end_Datetime;
    }

    public int getMin_End_Datetime() {
        return min_End_Datetime;
    }

    public void setMin_End_Datetime(int min_End_Datetime) {
        this.min_End_Datetime = min_End_Datetime;
    }

    public int getMax_End_Datetime() {
        return max_End_Datetime;
    }

    public void setMax_End_Datetime(int max_End_Datetime) {
        this.max_End_Datetime = max_End_Datetime;
    }

    public int getShip_ID() {
        return Ship_ID;
    }

    public void setShip_ID(int ship_ID) {
        Ship_ID = ship_ID;
    }

    public int getMin_Ship_ID() {
        return min_Ship_ID;
    }

    public void setMin_Ship_ID(int min_Ship_ID) {
        this.min_Ship_ID = min_Ship_ID;
    }

    public int getMax_Ship_ID() {
        return max_Ship_ID;
    }

    public void setMax_Ship_ID(int max_Ship_ID) {
        this.max_Ship_ID = max_Ship_ID;
    }

    public byte getClassType() {
        return ClassType;
    }

    public void setClassType(byte classType) {
        ClassType = classType;
    }

    public int getMMSI() {
        return MMSI;
    }

    public void setMMSI(int MMSI) {
        this.MMSI = MMSI;
    }

    public byte getShip_Type_AIS() {
        return Ship_Type_AIS;
    }

    public void setShip_Type_AIS(byte ship_Type_AIS) {
        Ship_Type_AIS = ship_Type_AIS;
    }

    public byte getMin_Ship_Type_AIS() {
        return min_Ship_Type_AIS;
    }

    public void setMin_Ship_Type_AIS(byte min_Ship_Type_AIS) {
        this.min_Ship_Type_AIS = min_Ship_Type_AIS;
    }

    public byte getMax_Ship_Type_AIS() {
        return max_Ship_Type_AIS;
    }

    public void setMax_Ship_Type_AIS(byte max_Ship_Type_AIS) {
        this.max_Ship_Type_AIS = max_Ship_Type_AIS;
    }

    public byte getShip_Type_IMO() {
        return Ship_Type_IMO;
    }

    public void setShip_Type_IMO(byte ship_Type_IMO) {
        Ship_Type_IMO = ship_Type_IMO;
    }

    public byte getMin_Ship_Type_IMO() {
        return min_Ship_Type_IMO;
    }

    public void setMin_Ship_Type_IMO(byte min_Ship_Type_IMO) {
        this.min_Ship_Type_IMO = min_Ship_Type_IMO;
    }

    public byte getMax_Ship_Type_IMO() {
        return max_Ship_Type_IMO;
    }

    public void setMax_Ship_Type_IMO(byte max_Ship_Type_IMO) {
        this.max_Ship_Type_IMO = max_Ship_Type_IMO;
    }

    public String getCountry_Code_Src() {
        return Country_Code_Src;
    }

    public void setCountry_Code_Src(String country_Code_Src) {
        Country_Code_Src = country_Code_Src;
    }

    public String getMin_Country_Code_Src() {
        return min_Country_Code_Src;
    }

    public void setMin_Country_Code_Src(String min_Country_Code_Src) {
        this.min_Country_Code_Src = min_Country_Code_Src;
    }

    public String getMax_Country_Code_Src() {
        return max_Country_Code_Src;
    }

    public void setMax_Country_Code_Src(String max_Country_Code_Src) {
        this.max_Country_Code_Src = max_Country_Code_Src;
    }

    public String getCountry_Code_Dst() {
        return Country_Code_Dst;
    }

    public void setCountry_Code_Dst(String country_Code_Dst) {
        Country_Code_Dst = country_Code_Dst;
    }

    public String getMin_Country_Code_Dst() {
        return min_Country_Code_Dst;
    }

    public void setMin_Country_Code_Dst(String min_Country_Code_Dst) {
        this.min_Country_Code_Dst = min_Country_Code_Dst;
    }

    public String getMax_Country_Code_Dst() {
        return max_Country_Code_Dst;
    }

    public void setMax_Country_Code_Dst(String max_Country_Code_Dst) {
        this.max_Country_Code_Dst = max_Country_Code_Dst;
    }

    public int getRegion_Index_Src() {
        return Region_Index_Src;
    }

    public void setRegion_Index_Src(int region_Index_Src) {
        Region_Index_Src = region_Index_Src;
    }

    public int getMin_Region_Index_Src() {
        return min_Region_Index_Src;
    }

    public void setMin_Region_Index_Src(int min_Region_Index_Src) {
        this.min_Region_Index_Src = min_Region_Index_Src;
    }

    public int getMax_Region_Index_Src() {
        return max_Region_Index_Src;
    }

    public void setMax_Region_Index_Src(int max_Region_Index_Src) {
        this.max_Region_Index_Src = max_Region_Index_Src;
    }

    public int getRegion_Index_Dst() {
        return Region_Index_Dst;
    }

    public void setRegion_Index_Dst(int region_Index_Dst) {
        Region_Index_Dst = region_Index_Dst;
    }

    public int getMin_Region_Index_Dst() {
        return min_Region_Index_Dst;
    }

    public void setMin_Region_Index_Dst(int min_Region_Index_Dst) {
        this.min_Region_Index_Dst = min_Region_Index_Dst;
    }

    public int getMax_Region_Index_Dst() {
        return max_Region_Index_Dst;
    }

    public void setMax_Region_Index_Dst(int max_Region_Index_Dst) {
        this.max_Region_Index_Dst = max_Region_Index_Dst;
    }

    public int getMin_load_capacity() {
        return Min_load_capacity;
    }

    public void setMin_load_capacity(int min_load_capacity) {
        Min_load_capacity = min_load_capacity;
    }

    public int getDefault_Min_load_capacity() {
        return default_Min_load_capacity;
    }

    public void setDefault_Min_load_capacity(int default_Min_load_capacity) {
        this.default_Min_load_capacity = default_Min_load_capacity;
    }

    public int getMax_return_strip() {
        return Max_return_strip;
    }

    public void setMax_return_strip(int max_return_strip) {
        Max_return_strip = max_return_strip;
    }

    public int getDefault_Max_return_strip() {
        return default_Max_return_strip;
    }

    public void setDefault_Max_return_strip(int default_Max_return_strip) {
        this.default_Max_return_strip = default_Max_return_strip;
    }

    public int getParticular_year() {
        return Particular_year;
    }

    public void setParticular_year(int particular_year) {
        Particular_year = particular_year;
    }

    public int getMin_Particular_year() {
        return min_Particular_year;
    }

    public void setMin_Particular_year(int min_Particular_year) {
        this.min_Particular_year = min_Particular_year;
    }

    public int getMax_Particular_year() {
        return max_Particular_year;
    }

    public void setMax_Particular_year(int max_Particular_year) {
        this.max_Particular_year = max_Particular_year;
    }

    public int getMonths() {
        return Months;
    }

    public void setMonths(int months) {
        Months = months;
    }

    public int getMin_Month() {
        return min_Month;
    }

    public void setMin_Month(int min_Month) {
        this.min_Month = min_Month;
    }

    public int getMax_Month() {
        return max_Month;
    }

    public void setMax_Month(int max_Month) {
        this.max_Month = max_Month;
    }

    public int getQuarter() {
        return Quarter;
    }

    public void setQuarter(int quarter) {
        Quarter = quarter;
    }

    public int getMin_Quarter() {
        return min_Quarter;
    }

    public void setMin_Quarter(int min_Quarter) {
        this.min_Quarter = min_Quarter;
    }

    public int getMax_Quarter() {
        return max_Quarter;
    }

    public void setMax_Quarter(int max_Quarter) {
        this.max_Quarter = max_Quarter;
    }

    @Override
    public String toString() {
        return "Port_Port{" +
                "World_port_index_number_src=" + World_port_index_number_src +
                ", World_port_index_number_dst=" + World_port_index_number_dst +
                ", Start_Datetime=" + Start_Datetime +
                ", End_Datetime=" + End_Datetime +
                ", Ship_ID=" + Ship_ID +
                ", ClassType=" + ClassType +
                ", MMSI=" + MMSI +
                ", Ship_Type_AIS=" + Ship_Type_AIS +
                ", Ship_Type_IMO=" + Ship_Type_IMO +
                ", Country_Code_Src='" + Country_Code_Src + '\'' +
                ", Country_Code_Dst='" + Country_Code_Dst + '\'' +
                ", Region_Index_Src=" + Region_Index_Src +
                ", Region_Index_Dst=" + Region_Index_Dst +
                ", Particular_year=" + Particular_year +
                ", Months=" + Months +
                ", Quarter=" + Quarter +
                '}';
    }
}

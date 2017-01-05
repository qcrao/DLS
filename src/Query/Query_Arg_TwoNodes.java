package Query;

import java.util.HashMap;

/**
 * Created by Administrator on 16/12/19.
 * 此为查询两个节点间的货运量的参数类。
 */
public class Query_Arg_TwoNodes extends Query_Arg implements Cloneable{
    int srcPortID = -1, dstPortID = -1;
    int srcRegionID = -1, dstRegionID = -1;
    String srcCountryCode = "", dstCountryCode = "";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Query_Arg_TwoNodes)) return false;
        if (!super.equals(o)) return false;

        Query_Arg_TwoNodes that = (Query_Arg_TwoNodes) o;

        if (srcPortID != that.srcPortID) return false;
        if (dstPortID != that.dstPortID) return false;
        if (srcRegionID != that.srcRegionID) return false;
        if (dstRegionID != that.dstRegionID) return false;
        if (!srcCountryCode.equals(that.srcCountryCode)) return false;
        return dstCountryCode.equals(that.dstCountryCode);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + srcPortID;
        result = 31 * result + dstPortID;
        result = 31 * result + srcRegionID;
        result = 31 * result + dstRegionID;
        result = 31 * result + srcCountryCode.hashCode();
        result = 31 * result + dstCountryCode.hashCode();
        return result;
    }

    public Query_Arg_TwoNodes(String[] args_pair)
    {
        super();
        HashMap<String, String> mp_args = new HashMap<>();
        int sz = args_pair.length;
        System.out.println("args: ");
        for (int i = 1; i < sz; ++i)
        {
            String[] pair = args_pair[i].trim().split(":");
            System.out.println(pair[0] + " " + pair[1]);
            mp_args.put(pair[0].trim(), pair[1].trim());
        }

        if (mp_args.containsKey("srcPortID"))
            setSrcPortID(Integer.parseInt(mp_args.get("srcPortID")));
        if (mp_args.containsKey("dstPortID"))
            setDstPortID(Integer.parseInt(mp_args.get("dstPortID")));
        if (mp_args.containsKey("srcRegionID"))
            setSrcRegionID(Integer.parseInt(mp_args.get("srcRegionID")));
        if (mp_args.containsKey("dstRegionID"))
            setDstRegionID(Integer.parseInt(mp_args.get("dstRegionID")));
        if (mp_args.containsKey("srcCountryCode") && !mp_args.get("srcCountryCode").equals("empty"))
            setSrcCountryCode(mp_args.get("srcCountryCode"));
        if (mp_args.containsKey("dstCountryCode") && !mp_args.get("dstCountryCode").equals("empty"))
            setDstCountryCode(mp_args.get("dstCountryCode"));
        if (mp_args.containsKey("throughputType"))
            setThroughputType(Integer.parseInt(mp_args.get("throughputType")));
        if (mp_args.containsKey("year"))
            setYear(Integer.parseInt(mp_args.get("year")));
        if (mp_args.containsKey("month"))
            setMonth(Integer.parseInt(mp_args.get("month")));
        if (mp_args.containsKey("aisShipType"))
            setAisShipType(Integer.parseInt(mp_args.get("aisShipType")));
        if (mp_args.containsKey("imoShipType"))
            setImoShipType(Integer.parseInt(mp_args.get("imoShipType")));
        if (mp_args.containsKey("lowerThreshDWT"))
            setLowerThreshDWT(Integer.parseInt(mp_args.get("lowerThreshDWT")));
        if (mp_args.containsKey("tradeType"))
            setTradeType(Integer.parseInt(mp_args.get("tradeType")));

    }

    public Query_Arg_TwoNodes() {
    }

    public Query_Arg_TwoNodes(int year, int month) {
        super(year, month);
    }

    @Override
    public String toString() {
        return "Query_Arg_TwoNodes{" +
                "srcPortID=" + srcPortID +
                ", dstPortID=" + dstPortID +
                ", srcRegionID=" + srcRegionID +
                ", dstRegionID=" + dstRegionID +
                ", srcCountryCode='" + srcCountryCode + '\'' +
                ", dstCountryCode='" + dstCountryCode + '\'' +
                '}';
    }

    @Override
    protected Query_Arg_TwoNodes clone()  {
        //return super.clone();
        Query_Arg_TwoNodes newQuery_ArgTwoNodes = null;
        newQuery_ArgTwoNodes = (Query_Arg_TwoNodes) super.clone();

        return newQuery_ArgTwoNodes;
    }

    public int getSrcPortID() {
        return srcPortID;
    }

    public void setSrcPortID(int srcPortID) {
        this.srcPortID = srcPortID;
    }

    public int getDstPortID() {
        return dstPortID;
    }

    public void setDstPortID(int dstPortID) {
        this.dstPortID = dstPortID;
    }

    public int getSrcRegionID() {
        return srcRegionID;
    }

    public void setSrcRegionID(int srcRegionID) {
        this.srcRegionID = srcRegionID;
    }

    public int getDstRegionID() {
        return dstRegionID;
    }

    public void setDstRegionID(int dstRegionID) {
        this.dstRegionID = dstRegionID;
    }

    public String getSrcCountryCode() {
        return srcCountryCode;
    }

    public void setSrcCountryCode(String srcCountryCode) {
        this.srcCountryCode = srcCountryCode;
    }

    public String getDstCountryCode() {
        return dstCountryCode;
    }

    public void setDstCountryCode(String dstCountryCode) {
        this.dstCountryCode = dstCountryCode;
    }
}

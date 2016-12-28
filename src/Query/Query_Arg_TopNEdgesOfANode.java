package Query;

import java.util.HashMap;

/**
 * Created by Administrator on 16/12/23.
 */
public class Query_Arg_TopNEdgesOfANode extends Query_Arg implements Cloneable{
    int portID = -1;
    int regionID = -1;
    String countryCode = "";
    int dstNodeType = Query_Arg.PORT;
    int N = 10;

    public Query_Arg_TopNEdgesOfANode(String[] args_pair)
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

        if (mp_args.containsKey("portID"))
            setPortID(Integer.parseInt(mp_args.get("portID")));
        if (mp_args.containsKey("regionID"))
            setRegionID(Integer.parseInt(mp_args.get("regionID")));
        if (mp_args.containsKey("countryCode"))
            setCountryCode(mp_args.get("countryCode"));

        if (mp_args.containsKey("dstNodeType"))
            setDstNodeType(Integer.parseInt(mp_args.get("dstNodeType")));
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
        if (mp_args.containsKey("N"))
            setN(Integer.parseInt(mp_args.get("N")));
    }

    public Query_Arg_TopNEdgesOfANode(int year, int month) {
        super(year, month);
    }

    public int getPortID() {
        return portID;
    }

    public void setPortID(int portID) {
        this.portID = portID;
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getDstNodeType() {
        return dstNodeType;
    }

    public void setDstNodeType(int dstNodeType) {
        this.dstNodeType = dstNodeType;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public DstNode getDstNode(Port_Port_Journey port_port_journey)
    {
        DstNode res = null;
        switch ((throughputType-1)*3 + dstNodeType)
        {
            case 1:
                res = new PortRegion_DstNode(port_port_journey.getWorld_port_index_number_src());
                break;
            case 2:
                res = new Country_DstNode(port_port_journey.getCountry_Code_Src());
                break;
            case 3:
                res = new PortRegion_DstNode(port_port_journey.getRegion_Index_Src());
                break;
            case 4:
                res = new PortRegion_DstNode(port_port_journey.getWorld_port_index_number_dst());
                break;
            case 5:
                res = new Country_DstNode(port_port_journey.getCountry_Code_Dst());
                break;
            case 6:
                res = new PortRegion_DstNode(port_port_journey.getWorld_port_index_number_dst());
                break;
            default:
                break;
        }
        return res;
    }

    @Override
    protected Query_Arg_TopNEdgesOfANode clone()  {
        //return super.clone();
        Query_Arg_TopNEdgesOfANode newQuery_ArgTwoNodes = null;
        newQuery_ArgTwoNodes = (Query_Arg_TopNEdgesOfANode) super.clone();

        return newQuery_ArgTwoNodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Query_Arg_TopNEdgesOfANode)) return false;
        if (!super.equals(o)) return false;

        Query_Arg_TopNEdgesOfANode that = (Query_Arg_TopNEdgesOfANode) o;

        if (portID != that.portID) return false;
        if (regionID != that.regionID) return false;
        if (dstNodeType != that.dstNodeType) return false;
        if (N != that.N) return false;
        return countryCode.equals(that.countryCode);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + portID;
        result = 31 * result + regionID;
        result = 31 * result + countryCode.hashCode();
        result = 31 * result + dstNodeType;
        result = 31 * result + N;
        return result;
    }
}

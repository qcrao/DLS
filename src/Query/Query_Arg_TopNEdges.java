package Query;

import java.util.HashMap;

/**
 * Created by Administrator on 16/12/26.
 */
public class Query_Arg_TopNEdges extends Query_Arg {

    public Query_Arg_TopNEdges(String[] args_pair)
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
        if (mp_args.containsKey("srcNodeType"))
            setSrcNodeType(Integer.parseInt(mp_args.get("srcNodeType")));
        if (mp_args.containsKey("dstNodeType"))
            setDstNodeType(Integer.parseInt(mp_args.get("dstNodeType")));
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
    private int srcNodeType = Query_Arg.PORT, dstNodeType = Query_Arg.PORT;

    public int getSrcNodeType() {
        return srcNodeType;
    }

    public void setSrcNodeType(int srcNodeType) {
        this.srcNodeType = srcNodeType;
    }

    public int getDstNodeType() {
        return dstNodeType;
    }

    public void setDstNodeType(int dstNodeType) {
        this.dstNodeType = dstNodeType;
    }

    public void setN(int n) {
        N = n;
    }

    private int N = 10;

    public int getN() {
        return N;
    }

    public Query_Arg_TopNEdges() {
    }

    public Query_Arg_TopNEdges(int year, int month) {
        super(year, month);
    }

    @Override
    protected Query_Arg_TopNEdges clone()  {
        //return super.clone();
        Query_Arg_TopNEdges newQuery_Arg_TopNEdges = null;
        newQuery_Arg_TopNEdges = (Query_Arg_TopNEdges) super.clone();
        return newQuery_Arg_TopNEdges;
    }

    public Edge getEdge(Port_Port_Journey port_port_journey)
    {
        int src_portID = port_port_journey.getWorld_port_index_number_src();
        int dst_portID = port_port_journey.getWorld_port_index_number_dst();
        int src_regionID = port_port_journey.getRegion_Index_Src();
        int dst_regionID = port_port_journey.getRegion_Index_Dst();
        String src_countryCode = port_port_journey.getCountry_Code_Src();
        String dst_countryCode = port_port_journey.getCountry_Code_Dst();

        switch ((srcNodeType-1)*3+dstNodeType)
        {
            case 1:
                return new Edge(new Port_Node(src_portID), new Port_Node(dst_portID));

            case 2:
                return new Edge(new Port_Node(src_portID), new Country_Node(dst_countryCode));

            case 3:
                return new Edge(new Port_Node(src_portID), new Region_Node(dst_regionID));

            case 4:
                return new Edge(new Country_Node(src_countryCode), new Port_Node(dst_portID));

            case 5:
                return new Edge(new Country_Node(src_countryCode), new Country_Node(dst_countryCode));

            case 6:
                return new Edge(new Country_Node(src_countryCode), new Region_Node(dst_regionID));

            case 7:
                return new Edge(new Region_Node(src_regionID), new Port_Node(dst_portID));

            case 8:
                return new Edge(new Region_Node(src_regionID), new Country_Node(dst_countryCode));

            case 9:
                return new Edge(new Region_Node(src_regionID), new Region_Node(dst_regionID));

            default:
                return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Query_Arg_TopNEdges)) return false;
        if (!super.equals(o)) return false;

        Query_Arg_TopNEdges that = (Query_Arg_TopNEdges) o;

        if (srcNodeType != that.srcNodeType) return false;
        if (dstNodeType != that.dstNodeType) return false;
        return N == that.N;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + srcNodeType;
        result = 31 * result + dstNodeType;
        result = 31 * result + N;
        return result;
    }
}

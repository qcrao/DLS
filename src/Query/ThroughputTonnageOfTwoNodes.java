package Query;

import java.util.HashSet;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 16/12/19.
 */
public class ThroughputTonnageOfTwoNodes {
    private ShareData shareData;
    private Query_Arg_TwoNodes query_argTwoNodes;
    private WeakHashMap<Query_Arg_TwoNodes, Long> mp_res = new WeakHashMap<>();

    public void setQuery_argTwoNodes(Query_Arg_TwoNodes query_argTwoNodes) {
        this.query_argTwoNodes = query_argTwoNodes;
        this.query_argTwoNodes.addMonths();
    }



    public ThroughputTonnageOfTwoNodes(ShareData shareData) {
        this.shareData = shareData;
    }

    private boolean checkLocation(Port_Port_Journey port_port_journey)
    {
        int World_port_index_number_src = port_port_journey.getWorld_port_index_number_src();
        String Country_Code_Src = port_port_journey.getCountry_Code_Src();
        int Region_Index_Src = port_port_journey.getRegion_Index_Src();

        if (query_argTwoNodes.getSrcPortID() != -1 && query_argTwoNodes.getSrcPortID() != World_port_index_number_src)
            return false;
        if (!query_argTwoNodes.getSrcCountryCode().isEmpty() && !query_argTwoNodes.getSrcCountryCode().equals(Country_Code_Src))
            return false;
        if (query_argTwoNodes.getSrcRegionID() != -1 && query_argTwoNodes.getSrcRegionID() != Region_Index_Src)
            return false;

        int World_port_index_number_dst = port_port_journey.getWorld_port_index_number_dst();
        String Country_Code_Dst = port_port_journey.getCountry_Code_Dst();
        int Region_Index_Dst = port_port_journey.getRegion_Index_Dst();

        if (query_argTwoNodes.getDstPortID() != -1 && query_argTwoNodes.getDstPortID() != World_port_index_number_dst)
            return false;
        if (!query_argTwoNodes.getDstCountryCode().isEmpty() && !query_argTwoNodes.getDstCountryCode().equals(Country_Code_Dst))
            return false;
        if (query_argTwoNodes.getDstRegionID() != -1 && query_argTwoNodes.getDstRegionID() != Region_Index_Dst)
            return false;

        return true;
    }

    /*private boolean checkTime(Port_Port_Journey port_port_journey)
    {
        if (query_argTwoNodes.getThroughputType() == 1) //import
        {
            if (query_argTwoNodes.getYear() != 0 && query_argTwoNodes.getYear() != port_port_journey.getDst_year())
                return false;
            if (!monthsToQuery.contains(port_port_journey.getDst_month()))
                return false;
        }

        else if (query_argTwoNodes.getThroughputType() == 2) //export
        {
            if (query_argTwoNodes.getYear() != 0 && query_argTwoNodes.getYear() != port_port_journey.getSrc_month())
                return false;
            if (!monthsToQuery.contains(port_port_journey.getSrc_month()))
                return false;
        }

        else
            System.err.println("month input error!");

        return true;
    }*/





    private boolean checkClassType(Port_Port_Journey port_port_journey)
    {
        if (port_port_journey.getClassType() != 0)
            return false;
        return true;
    }

    private long countDeadWeight()
    {

        List<Port_Port_Journey> port_port_journeys = shareData.getAll_port_port_journey();
        int sz = port_port_journeys.size();
        long res = 0;
        for (int i = 0; i < sz; ++i)
        {
            Port_Port_Journey port_port_journey = port_port_journeys.get(i);
            //if (!checkClassType(port_port_journey)) continue;
            if (!checkLocation(port_port_journey)) continue;
            if (!query_argTwoNodes.checkTime(port_port_journey)) continue;
            if (!query_argTwoNodes.checkShipTypeAIS(port_port_journey)) continue;
            //if (!query_arg_topNEdgesOfANode.checkShipTypeIMO(port_port_journey)) continue;
            if (!query_argTwoNodes.checkTradeType(port_port_journey)) continue;
            if (!query_argTwoNodes.checkLowestDeadWeight(port_port_journey)) continue;
            res += port_port_journey.getShip_Dead_Weight_Tonnage();
        }
        return res;
    }

    public long getRes(Query_Arg_TwoNodes query_argTwoNodes)
    {
        setQuery_argTwoNodes(query_argTwoNodes);
        //long startMili=System.currentTimeMillis();
        long deadWeight_res = 0;

        //look up the cache first
        if (mp_res.containsKey(query_argTwoNodes))
        {
            System.out.println("ThroughputTonnageOfTwoNodes: Get the result from cache!");
            deadWeight_res = mp_res.get(query_argTwoNodes);
        }

        else
        {
            //check up the direction: import or export or both
            if (query_argTwoNodes.getThroughputType() == Query_Arg.IM_EXPORT)
            {
                Query_Arg_TwoNodes import_Query_ArgTwoNodes = query_argTwoNodes.clone();
                import_Query_ArgTwoNodes.setThroughputType(1);

                setQuery_argTwoNodes(import_Query_ArgTwoNodes);
                long importDeadWeight = getRes(import_Query_ArgTwoNodes);

                /*long importDeadWeight = countDeadWeight();
                mp_res.put(import_Query_ArgTwoNodes, importDeadWeight);*/

                Query_Arg_TwoNodes export_Query_ArgTwoNodes = query_argTwoNodes.clone();
                export_Query_ArgTwoNodes.setThroughputType(2);

                setQuery_argTwoNodes(export_Query_ArgTwoNodes);
                long exportDeadWeight = getRes(export_Query_ArgTwoNodes);

                /*setQuery_argTwoNodes(export_Query_ArgTwoNodes);
                long exportDeadWeight = countDeadWeight();
                mp_res.put(export_Query_ArgTwoNodes, exportDeadWeight);*/

                mp_res.put(query_argTwoNodes.clone(), importDeadWeight+exportDeadWeight);
                deadWeight_res = importDeadWeight+exportDeadWeight;
            }
            else
            {
                long deadWeight = countDeadWeight();
                mp_res.put(query_argTwoNodes.clone(), deadWeight);
                deadWeight_res = deadWeight;
            }
        }
        //System.out.println("计算总耗时为："+(System.currentTimeMillis()-startMili)+"毫秒");
        System.out.println("res: " + deadWeight_res);
        return deadWeight_res;
    }
}

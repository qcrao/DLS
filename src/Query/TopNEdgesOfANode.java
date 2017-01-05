package Query;

import java.util.*;

/**
 * Created by Administrator on 16/12/23.
 * 若果求与给定结点贸易总量最多的N个结点，则先拆成分别求进口、和出口，存入缓存
 */
public class TopNEdgesOfANode {
    private ShareData shareData;
    private Query_Arg_TopNEdgesOfANode query_arg_topNEdgesOfANode;

    private WeakHashMap<Query_Arg_TopNEdgesOfANode, List<DstNode>> mp_res = new WeakHashMap<Query_Arg_TopNEdgesOfANode, List<DstNode>>();
    private HashMap<DstNode, Long> mp_node_import = new HashMap<>();
    private HashMap<DstNode, Long> mp_node_export = new HashMap<>();

    public TopNEdgesOfANode(ShareData shareData) {
        this.shareData = shareData;
    }

    public void setQuery_arg_topNEdgesOfANode(Query_Arg_TopNEdgesOfANode query_arg_topNEdgesOfANode) {
        this.query_arg_topNEdgesOfANode = query_arg_topNEdgesOfANode;
        query_arg_topNEdgesOfANode.addMonths();
    }

    private boolean checkLocation(Port_Port_Journey port_port_journey)
    {
        //import
        if (query_arg_topNEdgesOfANode.getThroughputType() == Query_Arg.EXPORT)
        {
            if (query_arg_topNEdgesOfANode.getPortID() != -1 && query_arg_topNEdgesOfANode.getPortID() != port_port_journey.getWorld_port_index_number_src())
                return false;
            if (!query_arg_topNEdgesOfANode.getCountryCode().isEmpty() && !query_arg_topNEdgesOfANode.getCountryCode().equals(port_port_journey.getCountry_Code_Src()))
                return false;
            if (query_arg_topNEdgesOfANode.getRegionID() != -1 && query_arg_topNEdgesOfANode.getRegionID() != port_port_journey.getRegion_Index_Src())
                return false;
        }
        //export
        else if (query_arg_topNEdgesOfANode.getThroughputType() == Query_Arg.IMPORT)
        {
            if (query_arg_topNEdgesOfANode.getPortID() != -1 && query_arg_topNEdgesOfANode.getPortID() != port_port_journey.getWorld_port_index_number_dst())
                return false;
            if (!query_arg_topNEdgesOfANode.getCountryCode().isEmpty() && !query_arg_topNEdgesOfANode.getCountryCode().equals(port_port_journey.getCountry_Code_Dst()))
                return false;
            if (query_arg_topNEdgesOfANode.getRegionID() != -1 && query_arg_topNEdgesOfANode.getRegionID() != port_port_journey.getRegion_Index_Dst())
                return false;
        }
        else
            System.err.println("ThroughputType input error!");

        return true;

    }

    private void addToMAP(HashMap<DstNode, Long> mp, Port_Port_Journey port_port_journey)
    {
        DstNode dstNode = query_arg_topNEdgesOfANode.getDstNode(port_port_journey);
        if (!mp.containsKey(dstNode))
            mp.put(dstNode, port_port_journey.getShip_Dead_Weight_Tonnage());
        else
            mp.put(dstNode, mp.get(dstNode)+port_port_journey.getShip_Dead_Weight_Tonnage());

    }

    private List<DstNode> getTopN(HashMap<DstNode, Long> mp)
    {
        List<DstNode> res = new ArrayList<>(query_arg_topNEdgesOfANode.getN());
        Queue<DstNode> q = new PriorityQueue<>(query_arg_topNEdgesOfANode.getN());
        Iterator<Map.Entry<DstNode, Long>> iter = mp.entrySet().iterator();
        Map.Entry<DstNode, Long> entry;
        while (iter.hasNext()) {
            entry = iter.next();
            DstNode dstNode = entry.getKey();
            dstNode.setDeadWeight(entry.getValue());
            q.add(dstNode);
        }

        for (int i = 0; i < query_arg_topNEdgesOfANode.getN() && !q.isEmpty(); ++i)
            res.add(q.poll());
        return res;
    }

    private boolean checkClassType(Port_Port_Journey port_port_journey)
    {
        if (port_port_journey.getClassType() != 0)
            return false;
        return true;
    }

    private List<DstNode> countDeadWeight(HashMap<DstNode, Long> mp)
    {
        mp.clear();
        List<Port_Port_Journey> port_port_journeys = shareData.getAll_port_port_journey();
        int sz = port_port_journeys.size();
        for (int i = 0; i < sz; ++i)
        {
            Port_Port_Journey port_port_journey = port_port_journeys.get(i);
            //if (!checkClassType(port_port_journey)) continue;
            if (!query_arg_topNEdgesOfANode.checkTime(port_port_journey)) continue;
            if (!query_arg_topNEdgesOfANode.checkShipTypeAIS(port_port_journey)) continue;
            //if (!query_arg_topNEdgesOfANode.checkShipTypeIMO(port_port_journey)) continue;
            if (!query_arg_topNEdgesOfANode.checkTradeType(port_port_journey)) continue;
            if (!query_arg_topNEdgesOfANode.checkLowestDeadWeight(port_port_journey)) continue;

            if (!checkLocation(port_port_journey))  continue;
            addToMAP(mp, port_port_journey);
        }

        return getTopN(mp);

    }

    private List<DstNode> countImExportDeadWeight()
    {
        HashMap<DstNode, Long> mp_node_im_export = new HashMap<>();
        Iterator<Map.Entry<DstNode, Long>> import_iter = mp_node_import.entrySet().iterator();
        Map.Entry<DstNode, Long> import_entry;
        while (import_iter.hasNext()) {
            import_entry = import_iter.next();
            DstNode dstNode = import_entry.getKey();
            DstNode new_dstNode = dstNode.clone();
            mp_node_im_export.put(new_dstNode, import_entry.getValue());
        }

        Iterator<Map.Entry<DstNode, Long>> export_iter = mp_node_export.entrySet().iterator();
        Map.Entry<DstNode, Long> export_entry;
        while (export_iter.hasNext()) {
            export_entry = export_iter.next();
            DstNode dstNode = export_entry.getKey();

            DstNode new_dstNode = dstNode.clone();

            if (mp_node_im_export.containsKey(new_dstNode))
                mp_node_im_export.put(new_dstNode, mp_node_im_export.get(new_dstNode)+export_entry.getValue());

            mp_node_im_export.put(new_dstNode, export_entry.getValue());
        }

        return getTopN(mp_node_im_export);
    }

    public String getStringRes(Query_Arg_TopNEdgesOfANode query_arg_topNEdgesOfANode)
    {
        List<DstNode> res = getRes(query_arg_topNEdgesOfANode);
        String res_string = "";
        int sz = res.size();
        res_string += sz + "~";
        for (int i = 0; i < sz; ++i)
        {
            DstNode dstNode = res.get(i);
            res_string += (dstNode.getName(query_arg_topNEdgesOfANode) + "~");
            res_string += (dstNode.getDeadWeight() + "~");
        }

        return res_string.substring(0, res_string.length()-1);
    }

    public List<DstNode> getRes(Query_Arg_TopNEdgesOfANode query_arg_topNEdgesOfANode)
    {
        //long startMili=System.currentTimeMillis();
        setQuery_arg_topNEdgesOfANode(query_arg_topNEdgesOfANode);
        List<DstNode> res = null;
        if (mp_res.containsKey(query_arg_topNEdgesOfANode))
        {
            System.out.println("TopNEdgesOfANode: Get the result from cache!");
            res = mp_res.get(query_arg_topNEdgesOfANode);
        }

        else
        {
            if (query_arg_topNEdgesOfANode.getThroughputType() == Query_Arg.IM_EXPORT)
            {
                Query_Arg_TopNEdgesOfANode import_query_arg_topNEdgesOfANode = query_arg_topNEdgesOfANode.clone();
                import_query_arg_topNEdgesOfANode.setThroughputType(Query_Arg.IMPORT);
                setQuery_arg_topNEdgesOfANode(import_query_arg_topNEdgesOfANode);
                List<DstNode> import_res = countDeadWeight(mp_node_import);
                mp_res.put(import_query_arg_topNEdgesOfANode, import_res);

                Query_Arg_TopNEdgesOfANode export_query_arg_topNEdgesOfANode = query_arg_topNEdgesOfANode.clone();
                export_query_arg_topNEdgesOfANode.setThroughputType(Query_Arg.EXPORT);
                setQuery_arg_topNEdgesOfANode(export_query_arg_topNEdgesOfANode);
                List<DstNode> export_res = countDeadWeight(mp_node_export);
                mp_res.put(export_query_arg_topNEdgesOfANode, export_res);

                res = countImExportDeadWeight();
                mp_res.put(query_arg_topNEdgesOfANode.clone(), res);

            }
            else
            {

                res = countDeadWeight(mp_node_export); //choose mp_node_import also OK!
                mp_res.put(query_arg_topNEdgesOfANode.clone(), res);
            }
        }


        //System.out.println("计算总耗时为："+(System.currentTimeMillis()-startMili)+"毫秒");
        return res;

    }

    public static void printDstNodeList(List<DstNode> dstNodes)
    {
        System.out.println("size: " + dstNodes.size());
        for (DstNode dstNode : dstNodes)
            System.out.println(dstNode.toString());
    }

}

abstract class DstNode implements Cloneable, Comparable<DstNode>
{
    long deadWeight;

    abstract String getName(Query_Arg query_arg);

    public long getDeadWeight() {
        return deadWeight;
    }

    public void setDeadWeight(long deadWeight) {
        this.deadWeight = deadWeight;
    }

    @Override
    public int compareTo(DstNode o) {
        if (this.deadWeight > o.deadWeight) return -1;
        else if (this.deadWeight < o.deadWeight) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "DstNode{" +
                "deadWeight=" + deadWeight +
                '}';
    }

    @Override
    protected DstNode clone()  {
        //return super.clone();
        DstNode new_dstNode = null;
        try {
            new_dstNode = (DstNode) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new_dstNode;
    }

}

class Region_DstNode extends DstNode
{
    int id;

    public Region_DstNode(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region_DstNode that = (Region_DstNode) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    String getName(Query_Arg query_arg) {
        return Port_Dictionary.getRegionInfo_Res(id);
    }

    @Override
    public String toString() {
        return "Region_DstNode{" +
                "id=" + id +
                '}';
    }


}

class Port_DstNode extends DstNode
{
    int id;

    public Port_DstNode(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Port_DstNode that = (Port_DstNode) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    String getName(Query_Arg query_arg) {
        return Port_Dictionary.getPortInfo_Res(id);
    }

    @Override
    public String toString() {
        return "Port_DstNode{" +
                "id=" + id +
                '}';
    }


}

class Country_DstNode extends DstNode
{
    String code;

    public Country_DstNode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country_DstNode that = (Country_DstNode) o;

        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    String getName(Query_Arg query_arg) {
        return code;
    }

    @Override
    public String toString() {
        return "Country_DstNode{" +
                "code='" + code + '\'' +
                '}';
    }
}

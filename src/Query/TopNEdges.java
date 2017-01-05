package Query;

import java.util.*;

/**
 * Created by Administrator on 16/12/26.
 */
public class TopNEdges {
    private ShareData shareData;
    private Query_Arg_TopNEdges query_arg_topNEdges;
    private WeakHashMap<Query_Arg_TopNEdges, List<Edge>> mp_res = new WeakHashMap<>();
    private HashMap<Edge, Long> mp_edge = new HashMap<>();

    public void setQuery_argTwoNodes(Query_Arg_TopNEdges query_arg_topNEdges) {
        this.query_arg_topNEdges = query_arg_topNEdges;
        query_arg_topNEdges.addMonths();
    }



    public TopNEdges(ShareData shareData) {
        this.shareData = shareData;
    }

    private boolean checkClassType(Port_Port_Journey port_port_journey)
    {
        if (port_port_journey.getClassType() != 0)
            return false;
        return true;
    }

    private void addMap(Port_Port_Journey port_port_journey)
    {
        if (query_arg_topNEdges.getThroughputType() == Query_Arg.IM_EXPORT)
        {
            Edge edge = query_arg_topNEdges.getEdge(port_port_journey);
            if (mp_edge.containsKey(edge))
            {
                mp_edge.put(edge, mp_edge.get(edge)+port_port_journey.getShip_Dead_Weight_Tonnage());
            }
            else
            {
                Edge reverseEdge = edge.reverse();
                if (mp_edge.containsKey(reverseEdge))
                {
                    mp_edge.put(reverseEdge, mp_edge.get(reverseEdge)+port_port_journey.getShip_Dead_Weight_Tonnage());
                }
                else
                {
                    mp_edge.put(edge, port_port_journey.getShip_Dead_Weight_Tonnage());
                }
            }
        }
        else
        {
            Edge edge = query_arg_topNEdges.getEdge(port_port_journey);
            if (!mp_edge.containsKey(edge))
                mp_edge.put(edge, port_port_journey.getShip_Dead_Weight_Tonnage());
            else
                mp_edge.put(edge, port_port_journey.getShip_Dead_Weight_Tonnage()+mp_edge.get(edge));
        }

    }

    private void countDeadWeight()
    {
        setQuery_argTwoNodes(query_arg_topNEdges);
        mp_edge.clear();

        List<Port_Port_Journey> port_port_journeys = shareData.getAll_port_port_journey();
        int sz = port_port_journeys.size();
        List<Edge> res = null;

        for (int i = 0; i < sz; ++i)
        {
            Port_Port_Journey port_port_journey = port_port_journeys.get(i);
            //if (!checkClassType(port_port_journey)) continue;
            if (!query_arg_topNEdges.checkTime(port_port_journey)) continue;
            if (!query_arg_topNEdges.checkShipTypeAIS(port_port_journey)) continue;
            //if (!query_arg_topNEdges.checkShipTypeIMO(port_port_journey)) continue;
            if (!query_arg_topNEdges.checkTradeType(port_port_journey)) continue;
            if (!query_arg_topNEdges.checkLowestDeadWeight(port_port_journey)) continue;
            addMap(port_port_journey);
        }
    }

    private List<Edge> getTopN(HashMap<Edge, Long> mp)
    {
        List<Edge> res = new ArrayList<>(query_arg_topNEdges.getN());
        Queue<Edge> q = new PriorityQueue<>(query_arg_topNEdges.getN());
        Iterator<Map.Entry<Edge, Long>> iter = mp.entrySet().iterator();
        Map.Entry<Edge, Long> entry;
        while (iter.hasNext()) {
            entry = iter.next();
            Edge edge = entry.getKey();
            edge.setTonnage(entry.getValue());
            q.add(edge);
        }

        for (int i = 0; i < query_arg_topNEdges.getN() && !q.isEmpty(); ++i)
            res.add(q.poll());
        return res;
    }

    public List<Edge> getRes(Query_Arg_TopNEdges query_arg_topNEdges)
    {
        //long startMili=System.currentTimeMillis();
        setQuery_argTwoNodes(query_arg_topNEdges);
        List<Edge> res = null;
        if (mp_res.containsKey(query_arg_topNEdges))
        {
            System.out.println("TopNEdges: Get the result from cache!");
            res = mp_res.get(query_arg_topNEdges);
        }
        else
        {
            countDeadWeight();
            res = getTopN(mp_edge);
            mp_res.put(query_arg_topNEdges.clone(), res);
        }

        //System.out.println("计算总耗时为："+(System.currentTimeMillis()-startMili)+"毫秒");
        return res;
    }

    public String getStringRes(Query_Arg_TopNEdges query_arg_topNEdges)
    {
        List<Edge> res = getRes(query_arg_topNEdges);
        String stringRes = "";
        int sz = res.size();
        stringRes += String.valueOf(sz);
        for (int i = 0; i < sz; ++i)
        {
            stringRes += "~";
            Edge edge = res.get(i);
            stringRes += edge.toString();
        }
        return stringRes;
    }
}

class Edge implements Comparable<Edge>
{
    private Node_Base src, dst;
    private long tonnage;

    private String getNodeNamePair()
    {
        return src.getName() + "~" + dst.getName();
    }

    @Override
    public String toString() {
        return getNodeNamePair() + "~" + String.valueOf(tonnage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;

        if (tonnage != edge.tonnage) return false;
        if (!src.equals(edge.src)) return false;
        return dst.equals(edge.dst);
    }

    @Override
    public int hashCode() {
        int result = src.hashCode();
        result = 31 * result + dst.hashCode();
        result = 31 * result + (int) (tonnage ^ (tonnage >>> 32));
        return result;
    }

    public Edge reverse()
    {
        return new Edge(dst, src);
    }

    public Edge(Node_Base src, Node_Base dst) {
        this.src = src;
        this.dst = dst;
    }

    public Node_Base getSrc() {
        return src;
    }

    public void setSrc(Node_Base src) {
        this.src = src;
    }

    public Node_Base getDst() {
        return dst;
    }

    public void setDst(Node_Base dst) {
        this.dst = dst;
    }

    public long getTonnage() {
        return tonnage;
    }

    public void setTonnage(long tonnage) {
        this.tonnage = tonnage;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.tonnage > o.tonnage) return -1;
        else if (this.tonnage < o.tonnage) return 1;
        else return 0;
    }

}

abstract class Node_Base
{
    abstract String getName();




}

/*class Port_Region_Node extends Node_Base
{
    int id;

    public Port_Region_Node(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Port_Region_Node)) return false;

        Port_Region_Node that = (Port_Region_Node) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Port_Region_Node{" +
                "id=" + id +
                '}';
    }

    //返回港口，港口代码，港口所属国家，国家代码，地区，地区代码，经纬度
    @Override
    String getName() {
        return String.valueOf(id);
    }
}*/

class Port_Node extends Node_Base
{
    int id;

    public Port_Node(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Port_Node)) return false;

        Port_Node that = (Port_Node) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Port_Node{" +
                "id=" + id +
                '}';
    }

    //返回港口，港口代码，港口所属国家，国家代码，地区，地区代码，经纬度
    @Override
    String getName() {
        return Port_Dictionary.getPortInfo_Res(id);
    }
}

class Region_Node extends Node_Base
{
    int id;

    public Region_Node(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region_Node)) return false;

        Region_Node that = (Region_Node) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Region_Node{" +
                "id=" + id +
                '}';
    }

    //返回港口，港口代码，港口所属国家，国家代码，地区，地区代码，经纬度
    @Override
    String getName() {
        return Port_Dictionary.getRegionInfo_Res(id);
    }
}

class Country_Node extends Node_Base
{
    String code;

    @Override
    public String toString() {
        return "Country_Node{" +
                "code='" + code + '\'' +
                '}';
    }

    public Country_Node(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country_Node)) return false;

        Country_Node that = (Country_Node) o;

        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    String getName() {
        return Port_Dictionary.getCountryInfo_Res(code);
    }
}

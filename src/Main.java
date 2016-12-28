import Query.*;

import java.util.*;

/**
 * Created by qcrao on 16/11/23.
 */
public class Main {

    public static Comparator<Student> idComparator = new Comparator<Student>(){

        @Override
        public int compare(Student c1, Student c2) {
            return (int) (c2.getId() - c1.getId());
        }
    };



    public static void main(String[] args) {
        /*Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); //0-11
        int time = 1479995894;
        calendar.setTimeInMillis((long)((long)time*1000));
//        System.out.println(calendar.get(Calendar.YEAR));
//        System.out.println(calendar.get(Calendar.MONTH));
//        System.out.println(calendar.get(Calendar.DATE));

        HashMap<Port_Dead_Weight, Integer> port_dead_weights = new HashMap<>();
        Port_Dead_Weight p = new Port_Dead_Weight(123, 123);
        port_dead_weights.put(p, 12);

        Port_Dead_Weight p1 = new Port_Dead_Weight(123, 123);
        System.out.println("YES");
        if (port_dead_weights.containsKey(p1))
        {
            System.out.println(port_dead_weights.get(p1));
            //port_dead_weights.put(p1,4);
            System.out.println(port_dead_weights.get(p1));
            System.out.println("YES");
        }*/

//        Queue<Student> studentsPriorityQueue = new PriorityQueue<>(1, idComparator);
//        Random rand = new Random();
//        for(int i=0;i<1;i++){
//            studentsPriorityQueue.add(new Student(rand.nextInt(100)));
//        }
//
//        for(int i=0;i<10;i++){
//            Student student = studentsPriorityQueue.poll();
//            System.out.println(student.getId());
//        }
//        List<Student> students = new ArrayList<>();
//        Student a = new Student(1);
//        students.add(a);
//        Student b = a.clone();
//        b.setId(2);
//
//        students.add(b);
//        for (Student student : students)
//            System.out.println(student.toString());

        Query_Arg_TwoNodes query_argTwoNodes = new Query_Arg_TwoNodes(2015, 3);

        query_argTwoNodes.setSrcCountryCode("");
        query_argTwoNodes.setDstCountryCode("");
        System.out.println(query_argTwoNodes.toString());

        ShareData shareData = new ShareData();
        ThroughputTonnageOfTwoNodes throughputTonnageOfTwoNodes = new ThroughputTonnageOfTwoNodes(shareData);
        System.out.println(throughputTonnageOfTwoNodes.getRes(query_argTwoNodes));


        query_argTwoNodes.setMonth(5);
        System.out.println(throughputTonnageOfTwoNodes.getRes(query_argTwoNodes));

        query_argTwoNodes.setMonth(3);
        System.out.println(throughputTonnageOfTwoNodes.getRes(query_argTwoNodes));

        query_argTwoNodes.setMonth(6);
        System.out.println(throughputTonnageOfTwoNodes.getRes(query_argTwoNodes));

        query_argTwoNodes.setMonth(22);
        System.out.println(throughputTonnageOfTwoNodes.getRes(query_argTwoNodes));

        query_argTwoNodes.setMonth(5);
        System.out.println(throughputTonnageOfTwoNodes.getRes(query_argTwoNodes));

        TopNEdgesOfANode topNEdgesOfANode = new TopNEdgesOfANode(shareData);
        Query_Arg_TopNEdgesOfANode query_arg_topNEdgesOfANode = new Query_Arg_TopNEdgesOfANode(2015, 0);
        query_arg_topNEdgesOfANode.setCountryCode("CN");
        query_arg_topNEdgesOfANode.setDstNodeType(Query_Arg.PORT);
        query_arg_topNEdgesOfANode.setThroughputType(Query_Arg.IM_EXPORT);
        query_arg_topNEdgesOfANode.setN(10);

        TopNEdgesOfANode.printDstNodeList(topNEdgesOfANode.getRes(query_arg_topNEdgesOfANode));

        query_arg_topNEdgesOfANode.setThroughputType(Query_Arg.IMPORT);
        TopNEdgesOfANode.printDstNodeList(topNEdgesOfANode.getRes(query_arg_topNEdgesOfANode));

        query_arg_topNEdgesOfANode.setThroughputType(Query_Arg.EXPORT);
        TopNEdgesOfANode.printDstNodeList(topNEdgesOfANode.getRes(query_arg_topNEdgesOfANode));

        Query_Arg_TopNEdges query_arg_topNEdges = new Query_Arg_TopNEdges();
        query_arg_topNEdges.setYear(2015);
        query_arg_topNEdges.setMonth(23);
        query_arg_topNEdges.setThroughputType(Query_Arg.IM_EXPORT);
        query_arg_topNEdges.setSrcNodeType(Query_Arg.COUNTRY);
        query_arg_topNEdges.setDstNodeType(Query_Arg.COUNTRY);
        query_arg_topNEdges.setN(5);
        query_arg_topNEdges.setTradeType(Query_Arg.TRADE_BETWEEN_COUNTYR);
        TopNEdges topNEdges = new TopNEdges(shareData);
        topNEdges.setQuery_argTwoNodes(query_arg_topNEdges);
        System.out.println(topNEdges.getRes(query_arg_topNEdges).toString());


    }



}

class Student implements Cloneable
{
    int id;
    int age;

    @Override
    protected Student clone()  {
        //return super.clone();
        Student newHead = null;
        try {
            newHead = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return newHead;
    }

        @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(int id) {
        this.id = id;
        age = id;

    }

    public Student(int id, int age) {
        this.id = id;
        this.age = age;
    }

    void fun()
    {
        System.out.println("I'm ok");
    }
    public int getId() {
        return id;
    }
}
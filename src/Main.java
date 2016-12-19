import java.util.*;
import java.util.Random;

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
        Calendar calendar = Calendar.getInstance();

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
        }

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



    }



}

class Student
{
    int id;
    int age;

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
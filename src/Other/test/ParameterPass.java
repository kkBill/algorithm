package Other.test;

import java.util.ArrayList;
import java.util.zip.CheckedOutputStream;

public class ParameterPass {
    static ArrayList<ArrayList<Integer>> container = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        System.out.println(array);
        change(array);
        System.out.println(array);
        System.out.println(container);
    }

    public static void change(ArrayList<Integer> array){
        array.add(233);
        container.add(array);//打印什么？ 是1，2，3，233 还是 1，2，3，233，666？ --> 是后者
        array.add(666);
    }
}

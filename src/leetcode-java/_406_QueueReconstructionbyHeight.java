package LeetCode;

import java.util.*;

public class _406_QueueReconstructionbyHeight {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine(); // "[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]"
        System.out.println("原始数组：" + input);

        // 处理输入
        input = input.substring(1, input.length()-1);
        System.out.println(input);
        String[] ss = input.split(", ");
        int m = ss.length, n = 2;
        int[][] people = new int[m][n];

        for(int i = 0; i < m; i++) {
            String s = ss[i];
            //System.out.print("{" + s + "}");
            s = s.substring(1, s.length()-1);
            String[] t = s.split(",");
            people[i][0] = Integer.parseInt(t[0]);
            people[i][1] = Integer.parseInt(t[1]);
        }

        int[][] res = solve(people);
        for(int i = 0; i < m; i++) {
            System.out.println(res[i][0] + " " + res[i][1]);
        }
    }

    private static int[][] solve(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            if(o1[0] != o2[0]) return o2[0] - o1[0];
            else return o1[1] - o2[1];
        });
        List<int[]> list = new LinkedList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        int[][] res = new int[people.length][2];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}

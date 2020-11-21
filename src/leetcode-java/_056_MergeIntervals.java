package LeetCode;

import java.util.*;

public class _056_MergeIntervals {

    /*
    public int[][] merge(int[][] intervals) {
        int rows = intervals.length;
        if(rows == 0) return new int[0][];

        // 预处理，排序
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] x, int[] y) {
                if(x[0] != y[0]){
                    return x[0] - y[0];
                }else {
                    return x[1] - y[1];
                }
            }
        });

        // lambda 表达式的写法
        Arrays.sort(intervals, (x, y) -> {
            if(x[0] != y[0]){
                return x[0] - y[0];
            }else {
                return x[1] - y[1];
            }
        });

        List<List<Integer>> temp = new ArrayList<>();

        int i = 0;
        while (i < rows) {
            int left = intervals[i][0], right = intervals[i][1];
            while (i + 1 < rows && right >= intervals[i+1][0]){
                right = Math.max(right, intervals[i+1][1]);
                i++;
            }
            temp.add(Arrays.asList(left,right));
            i++;
        }

        rows = temp.size();
        int[][] res = new int[rows][2];
        for(int r = 0; r < rows; r++) {
            res[r][0] = temp.get(r).get(0);
            res[r][1] = temp.get(r).get(1);
        }
        return res;
    }

     */


    // 无需手动从 List<List<Integer>> 转化成 int[][]
    // 学到了~~
    public int[][] merge(int[][] intervals) {
        int rows = intervals.length;
        if(rows == 0) return new int[0][];

        // lambda 表达式的写法
        Arrays.sort(intervals, (x, y) -> (x[0] - y[0]));

        List<int[]> res = new ArrayList<>(); // int[] 是一个对象，所以可以存在在容器中
        // List<int> res = new ArrayList<>(); // 而 int 是基本数据类型，所以必须用 Integer

        int i = 0;
        while (i < rows) {
            int left = intervals[i][0], right = intervals[i][1];
            while (i + 1 < rows && right >= intervals[i+1][0]){
                right = Math.max(right, intervals[i+1][1]);
                i++;
            }
            res.add(new int[]{left, right});
            i++;
        }
        return res.toArray(new int[0][]);
    }


    public static void main(String[] args) {
        _056_MergeIntervals obj = new _056_MergeIntervals();
        int[][] intervals = new int[][]{{1,4},{0,4}};
        int[][] res = obj.merge(intervals);
        for(int[] a : res) {
            System.out.println(a[0] + " " + a[1]);
        }

        intervals = new int[][]{{1,4},{2,3}};
        res = obj.merge(intervals);
        for(int[] a : res) {
            System.out.println(a[0] + " " + a[1]);
        }

        intervals = new int[][]{{2,3},{5,5},{2,2},{3,6},{3,4}};
        res = obj.merge(intervals);
        for(int[] a : res) {
            System.out.println(a[0] + " " + a[1]);
        }
    }
}

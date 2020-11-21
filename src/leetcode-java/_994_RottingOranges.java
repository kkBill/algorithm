package LeetCode;

import javafx.util.Pair;
import java.util.LinkedList;
import java.util.Queue;

public class _994_RottingOranges {

    private int[] x_axis = new int[]{0,0,-1,1};
    private int[] y_axis = new int[]{1,-1,0,0};

    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        if(rows == 0) return 0;
        int cols = grid[0].length;

        int countOf1 = 0;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        // 初始化，把所有已经腐烂的橘子放入队列中
        // 同时记录所有 1 的个数
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == 1) countOf1++;
                if(grid[i][j] == 2) {
                    Pair<Integer, Integer> point = new Pair<>(i,j);
                    queue.add(point);
                    grid[i][j] = -1; //
                }
            }
        }

        // 初始化后队列为空，说明不存在腐烂的句子，直接返回结果
        if(queue.isEmpty()) {
            if(countOf1 == 0) return 0;
            else return -1;
        }

        int minutes = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            // 遍历一层
            for(int k = 0; k < size; k++) {
                Pair<Integer, Integer> point = queue.poll();
                // 从该点出发，遍历4个相邻的方向
                for (int i = 0; i < 4; i++) {
                    int r = point.getKey() + y_axis[i];
                    int c = point.getValue() + x_axis[i];
                    boolean outOfBoundary = r < 0 || r >= rows || c < 0 || c >= cols;
                    if (!outOfBoundary && grid[r][c] == 1) {
                        countOf1--;
                        queue.add(new Pair<>(r, c));
                        grid[r][c] = -1;
                    }
                }
            }
            minutes++;
        }
        return countOf1 > 0 ? -1 : minutes;
    }

    public static void main(String[] args) {
        _994_RottingOranges obj = new _994_RottingOranges();
        int[][] grid = new int[][]{{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(obj.orangesRotting(grid)); // 4

        grid = new int[][]{{2,1,1},{1,1,0},{0,1,2}};
        System.out.println(obj.orangesRotting(grid)); // 2

        grid = new int[][]{{2,1,1},{0,1,1},{1,0,1}};
        System.out.println(obj.orangesRotting(grid)); // -1

        grid = new int[][]{{0, 2}};
        System.out.println(obj.orangesRotting(grid)); // 0

        grid = new int[][]{{0}};
        System.out.println(obj.orangesRotting(grid)); // 0

        grid = new int[][]{{1, 0}};
        System.out.println(obj.orangesRotting(grid)); // -1

        grid = new int[][]{{1, 1}};
        System.out.println(obj.orangesRotting(grid)); // -1
    }
}

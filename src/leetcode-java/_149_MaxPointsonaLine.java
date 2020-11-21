package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class _149_MaxPointsonaLine {
    public int maxPoints(int[][] points) {
        if (points.length < 3) return points.length;

        int maxPoints = 0;
        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> map = new HashMap<>();
            int duplicate = 0;
            int max = 0;
            for (int j = i + 1; j < points.length; j++) {
                // 计算两点的斜率
                int y = points[j][1] - points[i][1];
                int x = points[j][0] - points[i][0];
                if (y == 0 && x == 0) {
                    duplicate++;
                    continue;
                }

                int gcd = gcd(y, x);
                y /= gcd;
                x /= gcd;
                String key = y + "/" + x;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            maxPoints = Math.max(maxPoints, max + duplicate + 1);
        }
        return maxPoints;
    }

    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        _149_MaxPointsonaLine obj = new _149_MaxPointsonaLine();
        int[][] points = new int[][]{{1, 1}, {2, 2}, {3, 3}};
        System.out.println(obj.maxPoints(points));

        points = new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        System.out.println(obj.maxPoints(points));

        // 包含重复节点
        points = new int[][]{{0, 0}, {1, 1}, {0, 0}};
        System.out.println(obj.maxPoints(points)); // 结果为 3

        points = new int[][]{{1, 1}, {1, 1}, {1, 1}};
        System.out.println(obj.maxPoints(points)); // 结果为 3
    }
}
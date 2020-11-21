package LeetCode;

import java.util.*;

public class _210_CourseSchedule2 {
    /*
    // 时间复杂度：O(n) //所有节点都处理了一次
    // 空间复杂度：O(n) //维护入度数组需要n个空间
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 构建图
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // 统计节点的入度
        int[] indegree = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++) {
            List<Integer> adjacentList = graph.getOrDefault(prerequisites[i][1], new ArrayList<>());
            adjacentList.add(prerequisites[i][0]);
            graph.put(prerequisites[i][1], adjacentList);
            indegree[prerequisites[i][0]]++;
        }

        // 1.首先把入度为0的节点入队
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0) queue.add(i);
        }
        // 2.开始拓扑排序
        int count = 0; // 统计入队个数
        int[] topoSeq = new int[numCourses];
        while (!queue.isEmpty()) {
            int courseId = queue.poll();
            topoSeq[count] = courseId;
            count++;
            if(graph.get(courseId) != null) {
                for (int adjacentId : graph.get(courseId)) {
                    indegree[adjacentId]--;
                    if (indegree[adjacentId] == 0) queue.add(adjacentId);
                }
            }
        }

        if(count != numCourses) return new int[0]; // 不存在合法的拓扑序列
        return topoSeq;
    }

    */

    static int WHITE = 1;
    static int GRAY = 2;
    static int DARK = 3;
    private boolean existCycle = false;
    private int len;
    private int[] topoSeq;
    private Map<Integer, List<Integer>> graph = new HashMap<>();
    private Map<Integer, Integer> color = new HashMap<>();


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 初始化
        topoSeq = new int[numCourses];
        len = numCourses;
        for(int v = 0; v < numCourses; v++) {
            color.put(v, WHITE);
        }

        // 构建图
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> adjacentList = graph.getOrDefault(prerequisites[i][1], new ArrayList<>());
            adjacentList.add(prerequisites[i][0]);
            graph.put(prerequisites[i][1], adjacentList);
        }

        // DFS
        for(int v = 0; v < numCourses; v++) {
            if(color.get(v) == WHITE) {
                dfs(v);
                if(existCycle) break;
            }
        }

        if(existCycle) return new int[0];
        return topoSeq;
    }

    private void dfs(int v) {
        color.put(v, GRAY);
        List<Integer> adjacentList = graph.get(v);
        if(adjacentList == null) {
            color.put(v, DARK);
            topoSeq[--len] = v;
            return;
        }
        for(int w : adjacentList) {
            if(color.get(w) == WHITE) {
                dfs(w);
            }
            if(color.get(w) == GRAY) {
                existCycle = true;
                return;
            }
        }
        color.put(v, DARK);
        topoSeq[--len] = v;
    }

    public static void main(String[] args) {
        _210_CourseSchedule2 obj = new _210_CourseSchedule2();
        int numCourses = 2;
        int[][] prerequisites = new int[][]{{1, 0}};
        int[] topoSeq = obj.findOrder(numCourses, prerequisites);
        for (int id : topoSeq) {
            System.out.print(id + " ");
        }

        System.out.println();
        numCourses = 4;
        prerequisites = new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        topoSeq = obj.findOrder(numCourses, prerequisites);
        for (int id : topoSeq) {
            System.out.print(id + " ");
        }
    }
}

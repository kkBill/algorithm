package LeetCode;

import java.util.*;

public class _207_CourseSchedule {

    /* 通常一个图节点的定义如下，不过为了方便起见，也可以利用map来充当
    // Map<Integer, List<Integer>>
    class GraphNode {
        int val;
        List<GraphNode> adjacentList;
    }
    */

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构建图
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses]; // 统计节点的入度
        for(int i = 0; i < prerequisites.length; i++) {
            List<Integer> adjacentList = graph.getOrDefault(prerequisites[i][1], new ArrayList<>());
            adjacentList.add(prerequisites[i][0]);
            graph.put(prerequisites[i][1], adjacentList);
            indegree[prerequisites[i][0]]++;
        }

        // 拓扑排序
        // 1.首先把入度为0的节点入队
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0) queue.add(i);
        }
        // 2.开始BFS
        int count = 0;
        while (!queue.isEmpty()) {
            int courseId = queue.poll();
            count++;
            if(graph.get(courseId) != null) {
                for (int adjacentId : graph.get(courseId)) {
                    indegree[adjacentId]--;
                    if (indegree[adjacentId] == 0) queue.add(adjacentId);
                }
            }
        }
        return count == numCourses;
    }

    public static void main(String[] args) {
        _207_CourseSchedule obj = new _207_CourseSchedule();
        int numCourses = 2;
        int[][] prerequisites = new int[][] {{1,0}};
        System.out.println(obj.canFinish(numCourses, prerequisites));

        numCourses = 4;
        prerequisites = new int[][] {{1,0},{2,0},{3,1},{3,2}};
        System.out.println(obj.canFinish(numCourses, prerequisites));
    }
}

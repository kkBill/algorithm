package LeetCode;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class _133_CloneGraph {
    /*
    // DFS
    private Node[] map = new Node[101];

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        return dfs(node);
    }

    public Node dfs(Node node) {
        if (map[node.val] != null) {
            return map[node.val];
        }
        Node curr = new Node(node.val, new ArrayList<>());
        map[curr.val] = curr;
        for (Node n : node.neighbors) {
            curr.neighbors.add(dfs(n));
        }
        return curr;
    }
    */

    // BFS
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> map = new HashMap<>(); // 原节点和复制节点的映射
        queue.add(node);
        map.put(node, new Node(node.val, new ArrayList<>()));
        while (!queue.isEmpty()) {
            Node parent = queue.poll();
            for (Node child : parent.neighbors) {
                if(!map.containsKey(child)) {
                    queue.add(child);
                    map.put(child, new Node(child.val, new ArrayList<>()));
                }
                map.get(parent).neighbors.add(map.get(child));
            }
        }
        return map.get(node);
    }

    public static void main(String[] args) {
        _133_CloneGraph obj = new _133_CloneGraph();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node3);
        node4.neighbors.add(node1);

        //obj.dfs(node1);
        Node newRoot = obj.cloneGraph(node1);
    }
}

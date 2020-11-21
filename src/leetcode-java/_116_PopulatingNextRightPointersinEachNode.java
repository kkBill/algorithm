//package LeetCode;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class Node {
//    public int val;
//    public Node left;
//    public Node right;
//    public Node next;
//
//    public Node() {}
//
//    public Node(int _val) {
//        val = _val;
//    }
//
//    public Node(int _val, Node _left, Node _right, Node _next) {
//        val = _val;
//        left = _left;
//        right = _right;
//        next = _next;
//    }
//}
//
//public class _116_PopulatingNextRightPointersinEachNode {
//
//    /*
//    // 空间复杂度O(n)
//
//    public Node connect(Node root) {
//        if (root == null) return null;
//
//        List<Node> queue = new ArrayList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            Node pre = null;
//            for(int i=0; i<size; i++){
//                Node curr = queue.remove(0);
//                if(i > 0) pre.next = curr;
//                pre = curr;
//                if(curr.left != null) queue.add(curr.left);
//                if(curr.right != null) queue.add(curr.right);
//            }
//        }
//        return root;
//    }
//    */
//
//    // 空间复杂度O(1)
//    // 本题解对 116 和 117 题都是适用的
//    /*
//    public Node connect(Node root) {
//        Node dummy = new Node(0,null,null,null);
//        Node curr = dummy, head = root;
//        while (root != null) {
//            if(root.left != null) {
//                curr.next = root.left;
//                curr = curr.next;
//            }
//            if(root.right != null) {
//                curr.next = root.right;
//                curr = curr.next;
//            }
//            root = root.next;
//            // 切换至下一层
//            if(root == null) {
//                curr = dummy;
//                root = dummy.next;
//                dummy.next = null;
//            }
//        }
//        return head;
//    }
//     */
//
//    // 下面的解法是仅适用于 116 题，也就是满二叉树的情况
//    public Node connect(Node root) {
//        // start 指向每一层的第一个节点，curr 用于遍历一层的节点
//        Node start = root, curr = null;
//        while (start.left != null) {
//            curr = start;
//            while (curr != null) {
//                curr.left.next = curr.right;
//                if(curr.next != null) curr.right.next = curr.next.left;
//                curr = curr.next;
//            }
//            start = start.left; // 下一层
//        }
//        return root;
//    }
//
//
//    public static void main(String[] args) {
//        _116_PopulatingNextRightPointersinEachNode obj =
//                new _116_PopulatingNextRightPointersinEachNode();
//        Node root = null;
//        obj.connect(root);
//    }
//}

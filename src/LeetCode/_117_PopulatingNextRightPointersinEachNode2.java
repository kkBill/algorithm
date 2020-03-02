//package LeetCode;
//
//public class _117_PopulatingNextRightPointersinEachNode2 {
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
//
//    public static void main(String[] args) {
//
//    }
//}

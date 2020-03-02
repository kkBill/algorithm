package LeetCode;

public class _109_ConvertSortedListtoBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return new TreeNode(head.val);
        //
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        fast = slow.next;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(fast);
        return root;
    }

    public static void main(String[] args) {
        _109_ConvertSortedListtoBinarySearchTree obj = new _109_ConvertSortedListtoBinarySearchTree();
        ListNode head = ListNode.makeList(new int[]{1,2,3,4,5});
        TreeNode root = obj.sortedListToBST(head);
        System.out.println(TreeNode.serialize(root));
    }
}

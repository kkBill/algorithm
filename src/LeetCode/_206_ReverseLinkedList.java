package LeetCode;

public class _206_ReverseLinkedList {
    /**
     * 非递归版本
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head, next = null;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 递归版
     */
    /*
    public ListNode reverseList(ListNode head) {

    }
    */

    public static void main(String[] args) {

    }
}

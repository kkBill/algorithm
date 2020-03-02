package LeetCode;

public class _234_PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 节点个数为奇数个
        if(fast != null) slow = slow.next;
        // 翻转区间[slow, 尾节点]之间的链表
        ListNode prev = null, curr = slow;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        slow = prev; // 将反转后的链表头节点重新设为slow

        // 重置fast指针
        fast = head;
        while (slow != null) {
            if(slow.val != fast.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public static void main(String[] args) {
        _234_PalindromeLinkedList obj = new _234_PalindromeLinkedList();
        ListNode head = ListNode.makeList(new int[]{1,2,3,4,2,1});
        System.out.println(obj.isPalindrome(head));
    }
}

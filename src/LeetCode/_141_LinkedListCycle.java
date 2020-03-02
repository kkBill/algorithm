package LeetCode;

public class _141_LinkedListCycle {
    /**
     * 判断链表是否是环形链表
     * 知道了算法原理可就是写不出来...
     * 太精妙了...
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        _141_LinkedListCycle obj = new _141_LinkedListCycle();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        System.out.println(obj.hasCycle(node1)); // true

        ListNode head = ListNode.makeList(new int[]{1,2,3,4});
        System.out.println(obj.hasCycle(head)); // false
    }
}

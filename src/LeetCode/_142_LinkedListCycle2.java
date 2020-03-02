package LeetCode;

public class _142_LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        boolean hasCycle = false;
        // 判断是否有环（快慢指针）
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if(!hasCycle) return null;
        // 寻找环的入口节点
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        _142_LinkedListCycle2 obj = new _142_LinkedListCycle2();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        System.out.println(obj.detectCycle(node1));

        ListNode head = ListNode.makeList(new int[]{1,2,3,4});
        System.out.println(obj.detectCycle(head)); //null
    }
}

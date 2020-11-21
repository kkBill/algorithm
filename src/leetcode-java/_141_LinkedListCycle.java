package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class _141_LinkedListCycle {
    /**
     * 方法1：快慢指针法
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

    /**
     * 方法2：哈希表
     */
    /*
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if(set.contains(curr)) {
                return true;
            }else {
                set.add(curr);
            }
            curr = curr.next;
        }
        return false;
    }
    */

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

package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class _086_PartitionList {
    /**
     * 自己写的
     */
    /*
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;

        Queue<ListNode> queue1 = new LinkedList<>(); // node.val < x
        Queue<ListNode> queue2 = new LinkedList<>(); // node.val >= x
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr;
            curr = curr.next;

            temp.next = null; // !!!
            if (temp.val < x) queue1.add(temp);
            else queue2.add(temp);
        }

        head = null;
        while (!queue1.isEmpty()) {
            if (head == null) {
                head = queue1.poll();
                curr = head;
            } else {
                curr.next = queue1.poll();
                curr = curr.next;
            }
        }
        while (!queue2.isEmpty()) {
            if (head == null) {
                head = queue2.poll();
                curr = head;
            } else {
                curr.next = queue2.poll();
                curr = curr.next;
            }
        }
        return head;
    }

    */

    // 参考实现，更优雅~
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;

        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy, cur = head;
        pre.next = head;
        ListNode newDummy = new ListNode(-1);
        ListNode newCur = newDummy;
        while (cur != null) {
            if (cur.val < x) {
                pre.next = cur.next;
                cur.next = null;

                newCur.next = cur;
                newCur = newCur.next;

                cur = pre.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        newCur.next = dummy.next;
        return newDummy.next;
    }

    public static void main(String[] args) {
        _086_PartitionList obj = new _086_PartitionList();
        ListNode head = ListNode.makeList(new int[]{1, 2, 3, 3, 4, 2, 1});
        head = obj.partition(head, 3);
        ListNode.showList(head);
    }
}
package LeetCode;

public class _019_RemoveNthNodeFromEndofList {
    /**
     * 双指针法
     * 初始化指针p1和p2指向第一个节点，然后，p2率先移动(n-1)个节点，然后
     * p1、p2同时移动，当p2到达末节点时，p1指向的恰为倒数第n个节点
     * 由于题目中说明了这里给出的n总是有效的，因此不必处理
     * <p>
     * 时间复杂度：O(n) 因为只需要遍历一次节点
     * 空间复杂度：O(1) 没有额外节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head, p2 = head, pre = null;
        int cnt = 1;
        while (cnt < n) {
            p2 = p2.next;
            cnt++;
        }
        while (p2.next != null) {
            pre = p1;
            p1 = p1.next;
            p2 = p2.next;
        }
        // 删除p1指向的节点
        if(pre == null) head = head.next; // 删除第一个节点，特殊处理
        else pre.next = p1.next;

        return head;
    }

    public static void main(String[] args) {
        _019_RemoveNthNodeFromEndofList obj = new _019_RemoveNthNodeFromEndofList();
        ListNode head = ListNode.makeList(new int[]{1, 2, 3, 4, 5, 6, 7});
        ListNode.showList(head);
        System.out.println();
        head = obj.removeNthFromEnd(head, 7);
        ListNode.showList(head);
    }
}

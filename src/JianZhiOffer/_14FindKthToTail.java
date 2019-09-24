package JianZhiOffer;

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/

// 求链表中倒数第k个结点
public class _14FindKthToTail {
    public ListNode FindKthToTail(ListNode head,int k) {
        if(k <= 0 || head == null) return null; //边界条件

        ListNode first = head, second = head;
        while(k > 1 && second != null){
            second = second.next;
            k--;
        }
        if(second == null) return null; //边界条件

        while(second.next != null){
            first = first.next;
            second = second.next;
        }
        return first;
    }
}

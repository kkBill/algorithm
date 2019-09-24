package JianZhiOffer;

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/

public class _16Merge {
    // 递归实现
    public ListNode Merge(ListNode list1,ListNode list2) {
        // 有一个链表为空的情况
        if(list1 == null) return list2;
        else if(list2 == null) return list1;

        ListNode newHead = null;
        if(list1.val < list2.val){
            newHead = list1;
            newHead.next = Merge(list1.next, list2);
        }else{
            newHead = list2;
            newHead.next = Merge(list1, list2.next);
        }
        return newHead;
    }

    // 非递归实现

}

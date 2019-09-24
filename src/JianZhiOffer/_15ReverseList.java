package JianZhiOffer;

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/

public class _15ReverseList {
    // 非递归写法
    /*
    public ListNode ReverseList(ListNode head) {
        // 链表为空，或链表只有一个结点的情况
        if(head == null || head.next == null) return head;

        ListNode pPrev = null, pCurr = head, pNext = null;
        ListNode pNewHead = null;
        while(pCurr != null){
            // 1. before changing next of current, store next node
            pNext = pCurr.next;

            // if pNext is null, pCurr is the last node, which is new head of list
            if(pNext == null) pNewHead = pCurr;

            // 2. this is where actual reversing happens
            pCurr.next = pPrev;

            // 3. move pPrev and pCurr one step forward
            pPrev = pCurr;
            pCurr = pNext;
        }
        return pNewHead;
    }
    */

    // 递归写法
    /**
     *  1. divide the list into two parts: first node and rest of the linked list
     *  2. call reverse for the rest of the linked list
     *  3. link rest to first
     *  4. fix head pointer
     */

}

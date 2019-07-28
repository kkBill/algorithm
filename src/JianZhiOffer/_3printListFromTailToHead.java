package JianZhiOffer;

import java.util.ArrayList;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class _3printListFromTailToHead {

    public static ListNode reverse(ListNode head) {
        if(head == null) return null;
        ListNode pre = null, curr = head, tmp;
        while (curr.next != null) {
            tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        curr.next = pre;
        return curr;
    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) return new ArrayList<Integer>(); // 注意与 return null;的区别
        ListNode newHeadNode = reverse(listNode);
        ArrayList<Integer> result = new ArrayList<>();
        while (newHeadNode != null) {
            result.add(newHeadNode.val);
            newHeadNode = newHeadNode.next;
        }
        return result;
    }

    public static void main(String[] args) {

        //JianZhiOffer.ListNode first = null;

        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        first.next = second;
        second.next = third;
        third.next = null;

//        first = reverse(first);
//        while(first != null){
//            System.out.println(first.val);
//            first = first.next;
//        }

        System.out.println(printListFromTailToHead(first));
    }
}

package JianZhiOffer;

public class _25RandomListNodeClone2 {
    public static void main(String[] args) {
        // a random list for test
        RandomListNode head = new RandomListNode(1);
        head.next = new RandomListNode(2);
        head.next.next = new RandomListNode(3);
        head.next.next.next = new RandomListNode(4);
        head.next.next.next.next = new RandomListNode(5);

        head.random = head.next.next;
        head.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next;

        RandomListNode pNewHead = Clone(head);
        while (pNewHead!=null){
            System.out.println(pNewHead.label + " " + (pNewHead.random == null ? "-" : pNewHead.random.label));
            pNewHead = pNewHead.next;
        }
    }

    public static RandomListNode Clone(RandomListNode pHead) {
        // 1. 复制 next 结点，构成 A->A'->B->B'->C->C'
        RandomListNode pNode = pHead;
        while (pNode != null) {
            RandomListNode pClonedNode = new RandomListNode(pNode.label);
            pClonedNode.next = pNode.next;
            pNode.next = pClonedNode;
            pNode = pClonedNode.next;
        }

        // 2. 复制 random 结点
        /**
         * 思路：假设当前结点为 pNode
         * 如果结点 A 的 random 结点是 C，那么 A' 的 random 结点就是 C'
         * 如果 pNode.random 不为空，则把 pNode.random 的 clone 结点赋给 pNode 的 clone 结点
         */
        pNode = pHead;
        while(pNode != null){
            if(pNode.random != null){
                RandomListNode pClonedNode = pNode.next;
                pClonedNode.random = pNode.random.next;
            }
            pNode = pNode.next.next;
        }

        // 3. 拆分链表（要熟练掌握）
        pNode = pHead;
        RandomListNode pClonedHead = null, pCurr = null;
        if(pNode != null){
            pClonedHead = pCurr = pNode.next;
            pNode.next = pNode.next.next;
            pNode = pNode.next;
        }
        while (pNode != null){
            pCurr.next = pNode.next;
            pCurr = pCurr.next;

            pNode.next = pCurr.next;
            pNode = pNode.next;
        }

        return pClonedHead;
    }
}

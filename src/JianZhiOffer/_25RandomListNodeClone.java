package JianZhiOffer;

import java.util.HashMap;

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

/**
 * 借助 HashMap
 *
 * 1. 按照next顺序复制表，对结点pNode新建结点tmpNode，并建立HashMap<pNode, tmpNode> map的映射关系
 * 2. 再次遍历表，如果结点pNode的random结点不为空，那么令 pRandomNode = pNode.random
 * 3. pNode 在新链表中对应为map.get(pNode)，pRandomNode 在新链表中对应为map.get(pRandomNode)
 * 4. 即 pNode.random = pRandomNode <==> map.get(pNode).random = map.get(pRandomNode)
 */

public class _25RandomListNodeClone {
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

    public static RandomListNode Clone(RandomListNode pHead)
    {
        RandomListNode newHead = null, curr = null, pNode = pHead;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        // 第1次遍历，复制next结点
        while(pNode != null){
            RandomListNode tmpNode = new RandomListNode(pNode.label);
            map.put(pNode, tmpNode);//关键
            if(newHead == null){
                newHead = tmpNode;
                curr = tmpNode;
            }else{
                curr.next = tmpNode;
                curr = curr.next;
            }
            pNode = pNode.next;
        }

        // 第2次遍历，复制random结点
        pNode = pHead; // 重置
        while(pNode != null){
            RandomListNode pRandomNode = null;
            if((pRandomNode = pNode.random) != null){
                map.get(pNode).random = map.get(pRandomNode);
            }
            pNode = pNode.next;
        }
        return newHead;
    }
}

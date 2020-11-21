package LeetCode;

import javax.sound.midi.Soundbank;

public class _138_CopyListwithRandomPointer {

    private class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    private Node makeListwithRandomPointer() {
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);
        node1.next = node2;
        node1.random = null;
        node2.next = node3;
        node2.random = node1;
        node3.next = node4;
        node3.random = node5;
        node4.next = node5;
        node4.random = node3;
        node5.next = null;
        node5.random = node1;
        return node1;
    }

    private void showRandomList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.println("[" + curr.val + ", " + (curr.random != null ? curr.random.val : "null") + "]");
            curr = curr.next;
        }
        System.out.println();
    }

    public Node copyRandomList(Node head) {
        if(head == null) return null;

        // 1.复制节点
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = curr.next.next;
        }

        // 2.连接random指针
        curr = head;
        while (curr != null) {
            curr.next.random = (curr.random == null ? null : curr.random.next);
            curr = curr.next.next;
        }

        // 3.抽取链表
        Node newHead = head.next, newTail =head.next;
        curr = head;
        while (curr != null) {
            curr.next = newTail.next;
            curr = curr.next;
            newTail.next = curr != null ? curr.next : null;
            newTail = newTail.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        _138_CopyListwithRandomPointer obj = new _138_CopyListwithRandomPointer();
        Node head = obj.makeListwithRandomPointer();
        //obj.showRandomList(head);
        Node copyHead = obj.copyRandomList(head);
        //obj.showRandomList(copyHead);
    }
}

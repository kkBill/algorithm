package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 哈希 + 双向链表
 */
public class _146_LRUCache {

    class Node {
        private int key;
        private int value;
        private Node prev;
        private Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    class DoubleLinkedList {
        private Node dummyHead;
        private Node dummyTail;
        private int size;

        DoubleLinkedList() {
            dummyHead = new Node(-1, -1);
            dummyTail = new Node(-1, -1);
            dummyHead.next = dummyTail;
            dummyTail.next = dummyHead;
            dummyTail.prev = dummyHead;
            dummyHead.prev = dummyTail;

            size = 0;
        }

        // 向双向链表中加入节点（头插法）
        void addNode(Node node) {
            size++;
            node.next = dummyHead.next;
            dummyHead.next = node;
            node.next.prev = node;
            node.prev = dummyHead;
        }

        // 删除指定节点
        void deleteNode(Node node) {
            size--;
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // 把指定节点移至链表头部
        void moveNodeToHead(Node node) {
            // 首先删除node节点
            deleteNode(node);
            // 然后在首部插入该节点
            addNode(node);
        }

        int size() {
            return size;
        }
    }

    private Map<Integer, Node> cache;
    private DoubleLinkedList list;
    private int CAP;
    public _146_LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.list = new DoubleLinkedList();
        this.CAP = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) return -1;
        // 由于key值被访问，调整相应的节点至链表头部，表示该节点最近被访问了
        list.moveNodeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        // 如果新插入的key原本不在cache中，则将该值存入chache
        // 另外如果cache中的元素个数超过最大容量，则移除最近最少使用的那个，也就是链表尾的元素
        if(node == null) {
            Node newNode = new Node(key,value);
            list.addNode(newNode);
            cache.put(key,newNode);
            if(list.size() > CAP){
                Node tailNode = list.dummyTail.prev;
                list.deleteNode(tailNode);
                cache.remove(tailNode.key);
            }
        }else {
            // 如果新插入的key原来就在cache中存在，则只需要更新cache中对应的value，
            // 并将该节点移至头部
            node.value = value;
            list.moveNodeToHead(node);
        }
    }

    public static void main(String[] args) {
        _146_LRUCache cache = new _146_LRUCache(2);
        System.out.println(cache.get(2)); // -1
        cache.put(2, 6);
        System.out.println(cache.get(1)); // -1
        cache.put(1, 5);
        cache.put(1, 2); // 覆盖
        System.out.println(cache.get(1)); // 2
        System.out.println(cache.get(2)); // 6

//        cache.put(2,1);
//        cache.put(1,1);
//        cache.put(2,3);
//        cache.put(4,1);
//        System.out.println(cache.get(1)); // -1
//        System.out.println(cache.get(2)); // 3
    }
}

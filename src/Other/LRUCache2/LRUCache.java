package Other.LRUCache2;

import java.util.HashMap;

public class LRUCache<K, V> {

    // 链表结点
    private class Node {
        K key;
        V value;
        Node next;
        Node pre;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
            pre = null;
        }
    }

    private HashMap<K, Node> cache; // 标记结点是否被缓存
    private int maxCapacity; // 缓存可存放的最大容量
    private int size; // 缓存当前存放的容量
    private Node head; // 双向链表的头结点
    private Node tail; // 双向链表的尾结点


    LRUCache(int maxCapacity) {
        this.cache = new HashMap<>(maxCapacity);
        this.maxCapacity = maxCapacity;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == maxCapacity;
    }

    // 插入一个条目
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            System.out.println("already exists!");
            return;
        }

        Node tmp = new Node(key, value);
        cache.put(key, tmp); // 把新增结点加入缓存

        // 起始为空，则初始化第一个结点
        if (isEmpty()) {
            head = tail = tmp;
            size++;
            return;
        }

        // 如果缓存已满，则根据 LRU 策略来置换最近最久为使用的结点，即删除链表末尾的结点
        if (isFull()) {
            // 删除尾结点
            cache.remove(tail.key);
            remove(tail);
            size--;
        }
        // 新增结点插入头部
        tmp.next = head;
        head.pre = tmp;
        head = tmp;
        size++;
    }

    // 删除结点node
    private void remove(Node node) {
        if (node.equals(head)) { // 删除头节点
            Node next = head.next;
            head.next = null;
            next.pre = null;
            head = next; // 更新头节点
        } else if (node.equals(tail)) { // 删除尾结点
            Node pre = tail.pre;
            tail.pre = null;
            pre.next = null;
            tail = pre; // 更新尾结点
        } else {
            Node pre = node.pre;
            Node next = node.next;
            node.pre = node.next = null;
            pre.next = next;
            next.pre = pre;
        }
    }

    // 取值
    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        } else {
            // 1. 把命中的结点在链表移动至链表首部（先删除结点，再把该结点插入头部）
            Node p = cache.get(key);
            remove(p);

            // 把结点 p 插在头部
            p.next = head;
            head.pre = p;
            head = p;

            // 2. 返回对应的值
            return p.value;
        }
    }

    public void clearAll(){
        cache.clear();
        head = null;
        tail = null;
        size = 0;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        Node node = head;
        while (node != null) {
            result.append(String.format("%s=%s ", node.key, node.value));
            node = node.next;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        LRUCache<String, String> lruCache = new LRUCache<>(3);
        lruCache.put("1", "A");
        lruCache.put("2", "B");
        lruCache.put("3", "C");
        lruCache.get("2");
        lruCache.put("4", "D");
        System.out.println(lruCache);
        lruCache.get("3");
        lruCache.put("5", "E");
        System.out.println(lruCache);
//        System.out.println(lruCache.size());
//        lruCache.clearAll();
//        System.out.println(lruCache);
//        System.out.println(lruCache.size());

    }
}

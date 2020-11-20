# 数据结构设计题

[toc]

## [146. LRU缓存机制](https://leetcode-cn.com/problems/lru-cache/) [五星]

设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

* 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
* 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。（要求在 O(1) 时间复杂度完成这两种操作）

```java
LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得密钥 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得密钥 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4
```

分析：本题使用 “**哈希表+双向链表**” 来设计 LRU缓存 结构。哈希表保证get()操作为O(1)复杂度，而使用双向链表则保证向缓存中添加元素和删除元素为O(1)复杂度。

双向链表需要实现哪些接口？

- size()：当cache中的个数大于capacity之后，就需要删除链表尾节点，这个时候就用到了size
- deleteNode(Node node)：删除指定节点。有两个地方用到了删除节点，一个是删除尾节点，也就是上面说的那种情况；一个是删除刚刚被访问到的节点，因为要把最近一次被访问到的节点移至链表头部。
- addNode(Node node)：添加节点。根据LRU的场景，往链表中添加节点采用头插法。



```java
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
```



## [208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/) [五星,对前缀树不熟]

实现一个 Trie (前缀树)，包含 `insert`, `search`, 和 `startsWith` 这三个操作。

说明:

- 你可以假设所有的输入都是由小写字母 `a-z` 构成的。
- 保证所有输入均为非空字符串。

```
Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");   
trie.search("app");     // 返回 true
```

分析：参考[官方题解](https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode/)，没什么好说的，必须做到五分钟手撕字典树/前缀树~思维上需要注意的是，**字典树的节点本身并不存储字符，而是连接一个节点到另一个节点的边来存储字符**。具体到数据结构的实现中，就是`TrieNode`中并没有一个`private char c = ...`这样的数据域来存放字符，而是通过节点的边（也就是数组links的下标）来存储字符。

```java
class Trie {
    // 定前字典树的节点结构
    class TrieNode {
        private final int SIZE = 26; //本题限定仅存储26个小写字母 
        private TrieNode[] links;
        private boolean isEnd;       //标记节点是否为一个单词的尾节点
        
        // common API
        TrieNode() {
            links = new TrieNode[SIZE];
            isEnd = false;
        }
        
        public boolean containsKey(char c) {
            return links[c-'a'] != null;
        }
        
        public void put(char c) {
            links[c-'a'] = new TrieNode();
        }
        
        public TrieNode get(char c) {
            return links[c-'a'];
        }
        
        public boolean isEnd() {
            return isEnd;
        }
        
        public void setEnd() {
            this.isEnd = true;
        }
    }
    
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            if(!node.containsKey(c)) {
                node.put(c);
            }
            node = node.get(c);
        }
        node.setEnd(); // 在最后一个节点标记
    }
    
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for(char c : prefix.toCharArray()) {
            if(node.containsKey(c)) {
                node = node.get(c);
            }else {
                return null;
            }
        }
        return node;
    }
    
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}
```



## [211. 添加与搜索单词 - 数据结构设计](https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/) [五星]

设计一个支持以下两种操作的数据结构：

```
void addWord(word)
bool search(word)
```

`search(word)` 可以搜索**文字**或**正则表达式**字符串，字符串只包含字母` .` 或` a-z` 。` . `可以表示任何一个字母。

```
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
```

分析：本题是前缀树的直接应用，多了一点点深度搜索的结合。

```java
class WordDictionary {
    // 前缀树节点定义
    class TrieNode {
        private TrieNode[] links;
        private boolean isEnd;
        private final int SIZE = 26;

        TrieNode() {
            links = new TrieNode[SIZE];
            this.isEnd = false;
        }

        private boolean containsKey(char c) {
            return links[c-'a'] != null;
        }

        private void put(char c) {
            links[c-'a'] = new TrieNode();
        }

        private TrieNode get(char c) {
            return links[c-'a'];
        }

        private boolean isEnd() {
            return isEnd;
        }

        private void setEnd() {
            this.isEnd = true;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
	// 和 208题 实现的一样
    public void addWord(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            if(!node.containsKey(c)){
                node.put(c);
            }
            node = node.get(c);
        }
        node.setEnd();
    }
	// 由于需要匹配通配符，则通过深度遍历来处理
    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }

    private boolean searchHelper(String word, TrieNode node, int depth) {
        if(depth == word.length()) {
            return node.isEnd();
        }
        char c = word.charAt(depth);
        if(c == '.') { // 如果遇见通配符，则遍历所有可能的取值
            for(int i = 0; i < node.SIZE;i++) {
                TrieNode next = node.get((char)(i + 'a'));
                if(next != null && searchHelper(word, next, depth+1)){
                    return true;
                }
            }
            return false;
        }else {
            TrieNode next = node.get(c);
            if(next == null) return false;
            else return searchHelper(word, next,depth+1);
        }
    }
}
```



## [284. 顶端迭代器](https://leetcode-cn.com/problems/peeking-iterator/) [四星, 缓存思想]

Java中的迭代器有以下几个方法，这里只关注hashNext()和next()。其中hashNext()判断迭代器中是否仍有元素，next()返回当前迭代器的首个元素并删除它。

```java
public interface Iterator<E> {
	boolean hasNext();
	E next();
	default void remove() {
        throw new UnsupportedOperationException("remove");
    }
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```

要求增加实现一个peek()方法，该方法只返回迭代器的首个元素但不删除它。

分析：不能使用O(n)的额外空间。**运用cache缓存的思想**。

```java
// 空间复杂度：O(1)
class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer cache;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.cache = null;
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if(cache == null) {
            cache = iterator.next();
        }
        return cache;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if(cache == null) {
            return iterator.next();
        }
        // 如果cache非空，说明之前调用过peek()
        Integer temp = cache;
        cache = null;
        return temp;
    }

    @Override
    public boolean hasNext() {
        return cache != null || iterator.hasNext();
    }
}
```


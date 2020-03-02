package LeetCode;

public class _211_AddandSearchWord {

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

    public _211_AddandSearchWord() {
        root = new TrieNode();
    }

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

    public boolean search(String word) {
        return searchHelper(word,root,0);
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

    public static void main(String[] args) {
        _211_AddandSearchWord obj = new _211_AddandSearchWord();
        obj.addWord("bad");
        obj.addWord("dad");
        obj.addWord("mad");
        System.out.println(obj.search("pad")); // false
        System.out.println(obj.search("bad")); // true
        System.out.println(obj.search(".ad")); // true
        System.out.println(obj.search("b..")); // true
    }
}

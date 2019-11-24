package LeetCode;

class Trie {
    // 字典树节点
    class TrieNode {
        private TrieNode[] child;
        private final int SIZE = 26;
        private boolean isEnd = false;

        public TrieNode() {
            child = new TrieNode[SIZE];
        }

        public boolean containsKey(char ch) {
            return child[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return child[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            child[ch - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (!node.containsKey(currChar)) {
                node.put(currChar, new TrieNode());
            }
            node = node.get(currChar);
        }
        node.setEnd();
    }

    /**
     * return TrieNode of prefix of word or whole word
     */
    private TrieNode searchPrefix(String word){
        TrieNode node = root;
        for(int i=0;i<word.length();i++){
            char currChar = word.charAt(i);
            if(node.containsKey(currChar)){
                node = node.get(currChar);
            }else{
                return null;
            }
        }
        return node;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}

public class _208_Trie {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app"));       // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }
}

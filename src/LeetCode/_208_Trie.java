package LeetCode;

//class Trie {
//    class TrieNode {
//        private TrieNode[] child;
//        private final int SIZE = 26;
//        private boolean isEnd = false;
//        public TrieNode() {
//            child = new TrieNode[SIZE];
//        }
//    }
//    private TrieNode root;
//
//    public Trie() {
//        root = new TrieNode();
//    }
//
//    public void insert(String word) {
//        TrieNode node = root;
//        for (int i = 0; i < word.length(); i++) {
//            int idx = word.charAt(i) - 'a';
//            if (node.child[idx] == null) {
//                node.child[idx] = new TrieNode();
//            }
//            node = node.child[idx];
//        }
//        node.isEnd = true;
//    }
//
//    private TrieNode searchPrefix(String word) {
//        TrieNode node = root;
//        for (int i = 0; i < word.length(); i++) {
//            int idx = word.charAt(i) - 'a';
//            if (node.child[idx] != null) {
//                node = node.child[idx];
//            } else {
//                return null;
//            }
//        }
//        return node;
//    }
//
//    public boolean search(String word) {
//        TrieNode node = searchPrefix(word);
//        return node != null && node.isEnd;
//    }
//
//    public boolean startsWith(String prefix) {
//        TrieNode node = searchPrefix(prefix);
//        return node != null;
//    }
//}


class Trie {
    class TrieNode{
        TrieNode[] child;
        boolean isEnd;
        TrieNode(){
            child = new TrieNode[26];
            isEnd = false;
        }
    }
    private TrieNode root;
    Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode node = root;
        for(int i=0; i<word.length(); i++){
            int idx = word.charAt(i) - 'a';
            if(node.child[idx] == null){
                node.child[idx] = new TrieNode();
            }
            node = node.child[idx];
        }
        node.isEnd = true;
    }

    public TrieNode startsPre(String word){
        TrieNode node = root;
        for(int i=0; i<word.length(); i++){
            int idx = word.charAt(i) - 'a';
            if(node.child[idx] != null){
                node = node.child[idx];
            }else{
                return null;
            }
        }
        return node;
    }

    public boolean startsWith(String word){
        TrieNode node = startsPre(word);
        return node != null;
    }

    public boolean search(String word){
        TrieNode node = startsPre(word);
        return node != null && node.isEnd;
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

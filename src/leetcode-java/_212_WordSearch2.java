package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _212_WordSearch2 {
    private int[] R = {1, -1, 0, 0};
    private int[] C = {0, 0, 1, -1};
    private int ROW_SIZE;
    private int COL_SIZE;
    private boolean[][] visited;
    private Trie dict;

    public List<String> findWords(char[][] board, String[] words) {
        ROW_SIZE = board.length;
        COL_SIZE = board[0].length;
        visited = new boolean[ROW_SIZE][COL_SIZE];

        Set<String> result = new HashSet<>(); //用 HashSet 是为了去重
        // Trie has already been implemented at #208
        dict = new Trie();
        for (String word : words)
            dict.insert(word);

        for (int r = 0; r < ROW_SIZE; r++) {
            for (int c = 0; c < COL_SIZE; c++) {
                dfs(board, "", result, r, c);
            }
        }
        return new ArrayList<>(result);
    }

    private void dfs(char[][] board, String word, Set<String> result, int row, int col) {
        word += board[row][col];

        if (!dict.startsWith(word))
            return;
        if (dict.search(word)) {
            result.add(word);
            // return; // 注意，这里不能加return，否则 case 1 过不去
        }

        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + R[i];
            int nextCol = col + C[i];
            boolean isValid = (nextRow >= 0 && nextRow < ROW_SIZE) && (nextCol >= 0 && nextCol < COL_SIZE);
            if (isValid && !visited[nextRow][nextCol]) {
                dfs(board, word, result, nextRow, nextCol);
            }
        }
        visited[row][col] = false;
    }

    public static void main(String[] args) {
        _212_WordSearch2 obj = new _212_WordSearch2();
        // case 1
        String[] words = {"aba", "baa", "bab", "aaab", "aaa", "aaaa", "aaba"};
        char[][] board = {
                {'a', 'b'},
                {'a', 'a'}};
        System.out.println(obj.findWords(board, words));

        // case 2
        words = new String[]{"oath", "pea", "eat", "rain"};
        board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}};
        System.out.println(obj.findWords(board, words));
    }
}

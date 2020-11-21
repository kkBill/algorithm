package LeetCode;

public class _695_MaxAreaofIsland {
    private int[] x_axis = new int[]{1, -1, 0, 0};
    private int[] y_axis = new int[]{0, 0, 1, -1};
    private int ROWS;
    private int COLS;
    private int[][] grid;
    private boolean[][] visited;

    public int maxAreaOfIsland(int[][] grid) {
        ROWS = grid.length;
        if (ROWS == 0) return 0;
        COLS = grid[0].length;
        this.grid = grid;
        visited = new boolean[ROWS][COLS];

        int maxArea = 0;
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                if (grid[x][y] == 1 && !visited[x][y]) {
                    int area = dfs(x, y);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private int dfs(int x, int y) {
        visited[x][y] = true;
        int area = 1;

        // 分别向上、下、左、右遍历
        for (int i = 0; i < 4; i++) {
            int nextX = x + x_axis[i];
            int nextY = y + y_axis[i];
            // 验证位置(nextX, nextY)是否在边界内
            boolean outOfBoundary = nextX < 0 || nextX >= ROWS || nextY < 0 || nextY >= COLS;
            if (!outOfBoundary && grid[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                area += dfs(nextX, nextY);
            }
        }

        return area;
    }

    public static void main(String[] args) {
        _695_MaxAreaofIsland obj = new _695_MaxAreaofIsland();
        int[][] grid = new int[][]{
                {1, 1, 0, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1}};
        System.out.println(obj.maxAreaOfIsland(grid));
    }
}

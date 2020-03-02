package LeetCode;

public class _200_NumberofIslands {
    private int[] x_axis = new int[]{1, -1, 0, 0};
    private int[] y_axis = new int[]{0, 0, 1, -1};

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;

        int numberOfIsland = 0;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (grid[x][y] == 1) {
                    dfs(grid, x, y);
                    numberOfIsland++;
                }
            }
        }
        return numberOfIsland;
    }

    private void dfs(char[][] grid, int x, int y) {
        grid[x][y] = 0; // 将1标记为0，表示已经访问过了
        for (int i = 0; i < 4; i++) { // 分别向上、下、左、右4个位置遍历
            int nextX = x + x_axis[i];
            int nextY = y + y_axis[i];
            boolean outOfBoundary = nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length;
            if (!outOfBoundary && grid[nextX][nextY] == 1) {
                dfs(grid, nextX, nextY);
            }
        }
    }

    public static void main(String[] args) {
        _200_NumberofIslands obj = new _200_NumberofIslands();
        char[][] grid = new char[][]{
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0}};
        System.out.println(obj.numIslands(grid));
    }
}

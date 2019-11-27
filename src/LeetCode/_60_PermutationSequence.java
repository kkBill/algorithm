package LeetCode;

public class _60_PermutationSequence {
    private int[] factorial = new int[10];

    public String getPermutation(int n, int k) {
        // 初始化阶乘表
        factorial[0] = 1;
        for (int i = 1; i < 10; i++)
            factorial[i] = i * factorial[i - 1];

        StringBuilder sb = new StringBuilder();
        dfs(n, n, k, sb);
        return sb.toString();
    }

    private void dfs(int n, int height, int k, StringBuilder path) {
        if (path.length() == n) {
            return;
        }
        int count = factorial[height - 1];
        for (int i = 1; i <= n; i++) {
            String num = Integer.toString(i);
            if (!path.toString().contains(num)) {
                // 剪枝（本题最关键之处！）
                if (k > count) {
                    k -= count;
                    continue;
                }
                path.append(num);
                dfs(n, height - 1, k, path);
            }
        }
    }

    public static void main(String[] args) {
        _60_PermutationSequence obj = new _60_PermutationSequence();
        System.out.println(obj.getPermutation(4, 9));
    }
}

package LeetCode;

public class _134_GasStation {
    /**
     * 时间复杂度：O(n^2)
     * 这种方法比较暴力，就是先找到一个合法的站点作为起始站，然后开始模拟
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            if (gas[i] < cost[i]) continue;
            int next = i;
            int currGas = gas[i], currCost = cost[i];
            // 模拟
            while (currGas >= currCost) {
                next = (next + 1) % n;
                currGas = currGas - currCost + gas[next];
                currCost = cost[next];
                if (next == i) break;
            }
            if (next == i) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        _134_GasStation obj = new _134_GasStation();
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        System.out.println(obj.canCompleteCircuit(gas, cost));

        gas = new int[]{2, 3, 4};
        cost = new int[]{3, 4, 3};
        System.out.println(obj.canCompleteCircuit(gas, cost));
    }
}

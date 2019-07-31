package JianZhiOffer;

/**
 * 和前面“斐波那契数列”的思路一摸一样！只需要改动一处代码就可以了（因为边界条件稍有不同）
 * 其他的都不用改
 */
public class _8JumpFloor {
    public int JumpFloor(int target) {
        int[] res = new int[]{1,1};
        if(target<2) return res[target];

        int pre_i_1 = 1, pre_i_2 = 1;
        int ans = 0;
        for(int i=2;i<=target;i++){
            ans = pre_i_1 + pre_i_2;
            pre_i_2 = pre_i_1;
            pre_i_1 = ans;
        }
        return ans;
    }
}

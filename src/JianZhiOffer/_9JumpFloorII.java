package JianZhiOffer;

public class _9JumpFloorII {
    public int JumpFloorII(int target) {
        //return (int)Math.pow(2,target-1);
        return 1 << --target; // 利用位运算求2的n次幂，自己没想到！
    }
}

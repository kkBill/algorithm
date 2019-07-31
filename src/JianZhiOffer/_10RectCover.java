package JianZhiOffer;

public class _10RectCover {
    public static int RectCover(int target) {
        int[] res = new int[]{0, 1, 2};
        if (target < 3) return res[target];

        int pre_i_1 = 2, pre_i_2 = 1;
        int ans = 0;
        for(int i=3;i<=target;i++){
            ans = pre_i_1 + pre_i_2;
            pre_i_2 = pre_i_1;
            pre_i_1 = ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(RectCover(4));
    }
}

package JianZhiOffer;

public class _23VerifySquenceOfBST {
    public static void main(String[] args) {
        //int[] seq = {1,2,3};
        //int[] seq = {5, 7, 6, 9, 11, 10, 8};
        int[] seq = {0, 3, 1, 2};
        System.out.println(VerifySquenceOfBST(seq));
    }

    public static boolean VerifySquenceOfBST(int[] sequence) {
        if(sequence.length == 0) return false;
        else return func(sequence,0,sequence.length-1);
    }

    public static boolean func(int[] seq, int left, int right) {
        if (left >= right) return true;
        int root = seq[right];
        int leftLow = left, rightLow = left, rightHigh = right-1;
        while(seq[rightLow] < root) rightLow++;

        int leftHigh = rightLow-1;
        for(int i=rightLow; i<=rightHigh; i++){
            if(seq[i]<root) return false;
        }
        return func(seq, leftLow, leftHigh) && func(seq, rightLow, rightHigh);
    }
}

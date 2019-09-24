package JianZhiOffer;

public class _11NumberOf1 {
    public int NumberOf1(int n) {
        int count = 0;
        while(n != 0){
            count++;
            n = (n-1) & n;
        }
        return count;
    }
}

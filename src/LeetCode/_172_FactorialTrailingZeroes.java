package LeetCode;

public class _172_FactorialTrailingZeroes {
    public long trailingZeroes(int n) {
        long factorial = 1;
        while(n > 0){
            factorial = factorial * n;
            n--;
        }
        return factorial;
    }

    public static void main(String[] args) {
        _172_FactorialTrailingZeroes obj = new _172_FactorialTrailingZeroes();
        for(int i = 1; i<=20;i++){
            System.out.println(i + " " + obj.trailingZeroes(i));
        }
    }
}

package LeetCode;

public class _772_BasicCalculator3 {
    public int calculate(String s) {
        int result = 0, operand = 0;



        return result;
    }
    public static void main(String[] args) {
        _772_BasicCalculator3 obj = new _772_BasicCalculator3();
        System.out.println(obj.calculate(" 3+5 / 2"));//5
        System.out.println(obj.calculate("4+5/2+3*5/4-2"));//7
        System.out.println(obj.calculate("(4+5)/2+3*5/(4-2)"));//11
    }
}

package LeetCode;

import java.util.Stack;

public class _224_BasicCalculator {

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0, operand = 0, sign = 1;

        for(char c : s.toCharArray()){
            if(Character.isDigit(c)){
                operand = operand*10 + c - '0';
            }else if(c == '-'){
                result += sign * operand;
                operand = 0;
                sign = -1; // 这是下一个数字前的符号
            }else if(c == '+'){
                result += sign * operand;
                operand = 0;
                sign = 1; // 这是下一个数字前的符号
            }else if(c == '('){
                // 如果遇到左括号，则先把之前的计算结果放入栈中保存
                stack.push(result);
                stack.push(sign);
                // 括号内的表达式作为一个整体，因此重置result, operand和sign
                result = 0;
                operand = 0;
                sign = 1;
            }else if(c == ')'){
                // 首先把括号内的表达式计算完
                result += sign * operand;
                // 此时result表示括号内表达式的值，但是还要加上括号前的符号
                result *= stack.pop();
                // 再加上之前求出的结果
                result += stack.pop();
                operand = 0;
                sign = 1;
            }
        }

        return result + (sign * operand);
    }

    public static void main(String[] args) {
        _224_BasicCalculator obj = new _224_BasicCalculator();
        System.out.println(obj.calculate("(1+1)"));
        System.out.println(obj.calculate("-2+((((1+1))))"));
        System.out.println(obj.calculate("  -  2-1  + 2 "));
        System.out.println(obj.calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}

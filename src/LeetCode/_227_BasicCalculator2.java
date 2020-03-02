package LeetCode;

import java.util.Stack;

public class _227_BasicCalculator2 {

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0, operand = 0;
        char op = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                operand = operand * 10 + (c - '0');
            }
            // 注意这里不是else if，因此走完上一个if判断仍然会进入这个if判断
            // 这么处理是为了方便处理表达式的最后一个数值
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) { // c 等于+,-,*,/，或已经到达最后一个位置
                if (op == '+') stack.push(operand);
                else if (op == '-') stack.push(-operand);
                else if (op == '*' || op == '/') {
                    // 更严谨的话这里应该要防止溢出，但貌似不处理也能通过测试，但凡两个int相乘就留个心眼
                    int temp = op == '*' ? stack.pop() * operand : stack.pop() / operand;
                    stack.push(temp);
                }
                op = c;
                operand = 0;
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        _227_BasicCalculator2 obj = new _227_BasicCalculator2();
        System.out.println(obj.calculate(" 3+5 / 2"));//5
        System.out.println(obj.calculate("4+5/2+3*5/4-2"));//7
    }
}
package LeetCode;

import java.util.Stack;

public class _020_ValidParentheses {
    /**
     * 简单题代码还写的这么冗杂！
     * 时间复杂度：O(n) 一次遍历
     * 空间复杂度：O(n) 栈空间
     */
    /*
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        boolean flag = true;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' ||s.charAt(i) == '{'){
                stack.push(s.charAt(i));
                continue;
            }
            if(s.charAt(i) == ')' || s.charAt(i) == ']' ||s.charAt(i) == '}'){
                if(stack.empty()){
                    flag = false;
                    break;
                }else{
                    if((s.charAt(i) == ')' && stack.peek() == '(')
                            || (s.charAt(i) == ']' && stack.peek() == '[')
                            || (s.charAt(i) == '}' && stack.peek() == '{')) {
                        stack.pop();
                    }
                    else{
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag && stack.empty();
    }*/

    /**
     * 参考版本~
     * 还不理解~~
     */
    public boolean isValid(String s) {
        if (s.isEmpty())
            return true;
        if (s.length() % 2 == 1) // 长度为奇数时必不可能成对
            return false;

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.empty() || c != stack.pop())
                return false;
        }

        return stack.empty();
    }

    public static void main(String[] args) {
        _020_ValidParentheses obj = new _020_ValidParentheses();
        System.out.println(obj.isValid("()"));
        System.out.println(obj.isValid(")("));
        System.out.println(obj.isValid("("));
        System.out.println(obj.isValid("()[]{}"));
        System.out.println(obj.isValid("(]"));
        System.out.println(obj.isValid("([)]"));
        System.out.println(obj.isValid("[{()}]"));
    }
}

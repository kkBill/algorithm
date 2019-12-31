package LeetCode;

import java.util.Stack;

public class _020_ValidParentheses {
    /**
     * 简单题代码还写的这么冗杂！
     * 时间复杂度：O(n) 一次遍历
     * 空间复杂度：O(n) 栈空间
     */
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

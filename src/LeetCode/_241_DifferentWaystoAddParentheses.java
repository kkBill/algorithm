package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _241_DifferentWaystoAddParentheses {
    /*
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for(int j = 0; j < left.size(); j++) {
                    for(int k = 0; k < right.size(); k++) {
                        if(c == '+') res.add(left.get(j) + right.get(k));
                        if(c == '-') res.add(left.get(j) - right.get(k));
                        if(c == '*') res.add(left.get(j) * right.get(k));
                    }
                }
            }
        }
        if(res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }
    */

    Map<String, List<Integer>> memo = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        if(memo.containsKey(input)) return memo.get(input);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (Integer lvalue : left) {
                    for (Integer rvalue : right) {
                        if (c == '+') res.add(lvalue + rvalue);
                        if (c == '-') res.add(lvalue - rvalue);
                        if (c == '*') res.add(lvalue * rvalue);
                    }
                }
            }
        }
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        memo.put(input, res);
        return res;
    }

    public static void main(String[] args) {
        _241_DifferentWaystoAddParentheses obj = new _241_DifferentWaystoAddParentheses();
        String input = "2*3-4*5";
        System.out.println(obj.diffWaysToCompute(input));
    }
}

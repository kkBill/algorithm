package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _017_LetterCombinations {

    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) return result;
        backtrack("", 0, digits, result);
        return result;
    }

    private void backtrack(String combination, int index, String digits, List<String> result) {
        if (index == digits.length()) {
            result.add(combination);
            return;
        }
        String digit = digits.substring(index, index + 1);
        String charSet = phone.get(digit);
        for (int i = 0; i < charSet.length(); i++) {
            backtrack(combination + charSet.substring(i, i + 1), index + 1, digits, result);
        }
    }

    public static void main(String[] args) {
        _017_LetterCombinations obj = new _017_LetterCombinations();
        String digits = "2";
        System.out.println(obj.letterCombinations(digits));
    }
}

package LeetCode;

public class _125_ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (Character.isLetterOrDigit(s.charAt(left)) && Character.isLetterOrDigit(s.charAt(right))) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                } else {
                    left++;
                    right--;
                }
            }

            if (!Character.isLetterOrDigit(s.charAt(left))) left++;
            if (!Character.isLetterOrDigit(s.charAt(right))) right--;
        }
        return true;
    }

    public static void main(String[] args) {
        _125_ValidPalindrome obj = new _125_ValidPalindrome();
        System.out.println(obj.isPalindrome("race a car"));
        System.out.println(obj.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(obj.isPalindrome("0P"));
    }
}

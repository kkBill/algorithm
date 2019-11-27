package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _93_RestoreIPAddresses {
    /*
    // 暴力法
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        int len = s.length();
        for (int i = 1; i <= 3; i++) {
            for (int j = i + 1; j <= i + 4; j++) {
                for (int k = j + 1; k <= j + 4; k++) {
                    if (k < len) {
                        String tmp1 = s.substring(0, i);
                        String tmp2 = s.substring(i, j);
                        String tmp3 = s.substring(j, k);
                        String tmp4 = s.substring(k);
                        if (check(tmp1) && check(tmp2) && check(tmp3) && check(tmp4)) {
                            result.add(tmp1 + "." + tmp2 + "." + tmp3 + "." + tmp4);
                        }
                    }
                }
            }
        }
        return result;
    }
    */

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() > 12) return result;
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int curPos, List<String> segs, List<String> result) {
        if (segs.size() == 4) {
            if(curPos == s.length()) // 这个判断条件不能放在外面，否则速度很慢，思考！
                result.add(String.join(".", segs));
            return;
        }

        for (int k = 1; k <= 3; k++) {
            if (curPos + k > s.length()) break;
            String seg = s.substring(curPos, curPos + k);
            //System.out.println(seg);
            if (check(seg)) {
                segs.add(seg);
                backtrack(s, curPos + k, segs, result);
                segs.remove(segs.size() - 1);
            }
        }
    }

    private boolean check(String s) {
        if (s.length() > 3 || (s.length() != 1 && s.charAt(0) == '0') || Integer.parseInt(s) > 255)
            return false;
        return true;

        /*
        // 自己写的不够简洁
        if(s.length() > 3) return false;
        boolean flag = true;
        int num = Integer.parseInt(s);
        if (s.length() > 1) {
            flag = s.charAt(0) != '0';
        }
        return flag && (num >= 0 && num <= 255);
         */
    }

    public static void main(String[] args) {
        _93_RestoreIPAddresses obj = new _93_RestoreIPAddresses();
        String ip = "1111111111111111111111111111111";
        System.out.println(obj.restoreIpAddresses(ip));
    }
}

package LeetCode;

public class _165_CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        for(int i = 0; i < s1.length || i < s2.length; i++) {
            int a1 = i < s1.length ? Integer.parseInt(s1[i]) : 0;
            int a2 = i < s2.length ? Integer.parseInt(s2[i]) : 0;
            if(a1 != a2) return a1 > a2 ? 1 : -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        _165_CompareVersionNumbers obj = new _165_CompareVersionNumbers();
        System.out.println(obj.compareVersion("0.1","1.1"));//-1
        System.out.println(obj.compareVersion("1.0.1","1"));//1
        System.out.println(obj.compareVersion("7.5.2.4","7.5.3"));//-1
        System.out.println(obj.compareVersion("1.01","1.001"));//0
        System.out.println(obj.compareVersion("1.0","1.0.0"));//0
    }
}

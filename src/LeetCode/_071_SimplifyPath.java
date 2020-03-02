package LeetCode;

import java.util.Stack;

public class _071_SimplifyPath {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] s = path.split("/");

        for (String value : s) {
            System.out.print("[" + value + "]");

            if (value.equals("") || value.equals(".")) {
                continue;
            }
            if (value.equals("..")) {
                if (!stack.empty()) {
                    stack.pop();
                }
            } else {
                stack.push("/" + value);
            }
        }

        if(stack.empty()){
            return "/";
        }

        StringBuilder result = new StringBuilder();
        while (!stack.empty()){
            result.insert(0,stack.pop());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        _071_SimplifyPath obj = new _071_SimplifyPath();
        //String path = "/a//b////c/d//././/..";
        String path = "/..";
        System.out.println(obj.simplifyPath(path));

//        String path2 = "/a/../../b/../c//.//"
//        System.out.println(obj.simplifyPath(path2));
//
//        String path3 = "/a/./b/../../c/";
//        System.out.println(obj.simplifyPath(path3));
//
//        String path4 = "/home//foo/";
//        System.out.println(obj.simplifyPath(path4));
//
//        String path5 = "/../";
//        System.out.println(obj.simplifyPath(path5));
//
//        String path6 = "/home/";
//        System.out.println(obj.simplifyPath(path6));
    }
}

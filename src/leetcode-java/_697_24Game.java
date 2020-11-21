package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _697_24Game {
    /*
    private final double eps = 0.0001;
    public boolean judgePoint24(int[] nums) {
        List<Double> arrs = new ArrayList<>();
        for(int num : nums) {
            arrs.add((double)num);
        }
        return dfs(arrs);
    }

    public boolean dfs(List<Double> nums) {
        if(nums.size() == 1) {
            return Math.abs(nums.get(0) - 24) < eps;
        }

        for(int i = 0; i < nums.size(); i++) {
            for(int j = i+1; j < nums.size(); j++) {
                List<Double> copy = new ArrayList<>(nums);
//                 注意，这里必须要先remove j，再remove i，因为 j > i
//                 假设此时数组的长度为2，i = 0, j = 1,
//                 如果先执行copy.remove(i)，那么执行完这一句之后，数组的长度就变成了1，
//                 此时如果再执行remove(j)，就会发生越界的情况
//
                double b = copy.remove(j);
                double a = copy.remove(i);

                boolean isValid = false;
                // "+"
                copy.add(a+b);
                isValid = isValid || dfs(copy);

                // "-"
                copy.set(copy.size()-1, a-b);
                isValid = isValid || dfs(copy);
                copy.set(copy.size()-1, b-a);
                isValid = isValid || dfs(copy);

                // "*"
                copy.set(copy.size()-1, a*b);
                isValid = isValid || dfs(copy);

                // "/"
                if(a > eps) {
                    copy.set(copy.size()-1, b / a);
                    isValid = isValid || dfs(copy);
                }
                if(b > eps) {
                    copy.set(copy.size()-1, a / b);
                    isValid = isValid || dfs(copy);
                }

                // 如果有满足条件的，则返回true
                if(isValid) return true;
            }
        }
        return false;
    }
    */

    private static int TARGET = 24;
    private static double eps = 1e-6; // 精度
    private static int ADD = 0, MUL = 1, SUB = 2, DIV = 3;

    public void judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        List<String> path = new ArrayList<>();
        for(int num : nums) {
            list.add((double)num);
            path.add(Integer.toString(num));
        }
        List<List<String>> res = new ArrayList<>();
        dfs(list, path, res);
        for(List<String> line : res) {
            System.out.println(line.toString());
        }
    }

    public void dfs(List<Double> nums, List<String> path, List<List<String>> res) {
        if(nums.size() == 1) {
            if(Math.abs(nums.get(0) - TARGET) <= eps) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        int size = nums.size();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(i == j) continue;
                // 初始化辅助数组tmp为非i和非j的两个元素，然后再对nums[i],nums[j]进行考虑
                List<Double> tmp_nums = new ArrayList<>();
                List<String> tmp_path = new ArrayList<>();
                for(int k = 0; k < size; k++) {
                    if(k != i && k != j) {
                        tmp_nums.add(nums.get(k));
                        tmp_path.add(path.get(k));
                    }
                }
                double a = nums.get(i), b = nums.get(j);
                String as = path.get(i), bs = path.get(j);

                // 分别考虑+,*,-,/
                for(int op = 0; op < 4; op++) {
                    // 对于"+"和"*"，避免重复
                    if(i > j && (op == ADD || op == MUL) ) continue;

                    if(op == ADD) {
                        tmp_nums.add(a+b);
                        tmp_path.add("("+as+"+"+bs+")");
                    }else if(op == MUL) {
                        tmp_nums.add(a*b);
                        tmp_path.add("("+as+"*"+bs+")");
                    }else if(op == SUB) {
                        tmp_nums.add(a-b);
                        tmp_path.add("("+as+"-"+bs+")");
                    }else if(op == DIV) {
                        if(Math.abs(b) <= eps) continue; // 当b小于eps时，认为b==0，因此不能作为被除数
                        tmp_nums.add(a/b);
                        tmp_path.add("("+as+"/"+bs+")");
                    }
                    dfs(tmp_nums, tmp_path, res);
                    tmp_nums.remove(tmp_nums.size()-1);
                    tmp_path.remove(tmp_path.size()-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        _697_24Game obj = new _697_24Game();
        int[] nums = new int[]{4, 1, 8, 7};
        obj.judgePoint24(nums);
    }
}

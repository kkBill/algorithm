package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _022_GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack("",0,0,n,result);
        return result;
    }

    // openCnt 是当前组合中"("的个数
    // closeCnt 是当前组合中")"的个数
    private void backtrack(String combination, int openCnt, int closeCnt, int n, List<String> result){
        if(combination.length() == 2*n){
            result.add(combination);
            return;
        }
        if(closeCnt < openCnt) // 理解这一条件是本题重点
            backtrack(combination+")",openCnt,closeCnt+1,n,result);
        if(openCnt < n)
            backtrack(combination+"(",openCnt+1,closeCnt,n,result);
    }

    public static void main(String[] args) {
        _022_GenerateParentheses obj = new _022_GenerateParentheses();
        System.out.println(obj.generateParenthesis(3));
    }
}

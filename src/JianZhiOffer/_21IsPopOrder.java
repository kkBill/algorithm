package JianZhiOffer;

import java.util.Stack;

public class _21IsPopOrder {
    public static void main(String[] args) {
        int[] pushA = {1, 2, 3, 4, 5};
        //int[] popA = {4, 3, 5, 1, 2};
        int[] popA = {4, 5, 3, 2, 1};
        //int[] popA = {1,2,3,4,5};
        System.out.println(IsPopOrder(pushA, popA));

    }

    public static boolean IsPopOrder(int[] pushA, int[] popA) {

        Stack<Integer> stack = new Stack<>();
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.empty() && stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.empty();
    }
}

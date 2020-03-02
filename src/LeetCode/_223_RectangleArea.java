package LeetCode;

public class _223_RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // 确保第一个矩形一定在右侧，这样便于处理
        if (E < A) {
            return computeArea(E, F, G, H, A, B, C, D);
        }
        int totalArea = (C - A) * (D - B) + (G - E) * (H - F);
        // 如果没有交集，直接返回结果。
        if(B>=H || F>=D || E>=C) return totalArea;
        // 有交集，计算覆盖的面积（关键）
        int up = Math.min(D,H);
        int down = Math.max(B,F);
        int left = Math.max(A,E);
        int right = Math.min(C,G);
        int duplicatedArea = (right - left) * (up - down);

        return totalArea - duplicatedArea;
    }

    public static void main(String[] args) {
        _223_RectangleArea obj = new _223_RectangleArea();
        System.out.println(obj.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }
}

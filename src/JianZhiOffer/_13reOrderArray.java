package JianZhiOffer;

public class _13reOrderArray {
    public static void reOrderArray(int[] array) {
        if (array.length <= 1) return;

        int pEven = 0, pOdd = 1;
        while (pEven < array.length && pOdd < array.length) {
            while (pEven < array.length && array[pEven] % 2 == 1) pEven++;
            while (pOdd < array.length && array[pOdd] % 2 == 0) pOdd++;
            if (pEven < pOdd && pOdd < array.length) {
                int tmp = array[pOdd];
                for (int i = pOdd; i > pEven; i--) {
                    array[i] = array[i - 1];
                }
                array[pEven] = tmp;
            }
            pEven++;
            pOdd++;
        }
    }

    // test
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 6, 5, 7, 9, 4, 5, 4, 1};
        reOrderArray(array);
        for (int elem : array) {
            System.out.print(elem + " ");
        }
    }
}

package JianZhiOffer;

public class _6minNumberInRotateArray {
    public int minNumberInOrder(int[] array, int left, int right) {
        int minVal = array[left];
        for(int i=left+1; i<=right; i++){
            if(array[i] < minVal)
                minVal = array[i];
        }
        return minVal;
    }

    public int minNumberInRotateArray(int [] array) {
        if(array.length == 0) return 0;
        int i = 0, j = array.length-1;
        int mid;
        while(j - i > 1){
            mid = i + (j - i)/2;
            // 特殊情况，无法再使用二分查找，故只能遍历处理
            if(array[i] == array[j] && array[i] == array[mid])
                return minNumberInOrder(array, i, j);
            if(array[mid] >= array[i]) i = mid;
            else if(array[mid] <= array[j]) j = mid;
        }
        return array[j];
    }
}
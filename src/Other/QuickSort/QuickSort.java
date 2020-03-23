package Other.QuickSort;

public class QuickSort {

    public void quickSort(int[] nums) {
        qSort(nums, 0, nums.length - 1);
    }

    private void qSort(int[] nums, int left, int right) {
        if (left >= right) return;
        if (right - left == 1) {
            if (nums[left] > nums[right]) {
                swap(nums, left, right);
            }
            return;
        }

        // 选取主元
        int pivot = selectPivot(nums, left, right);

        // 根据主元进行划分
        int i = left + 1, j = right - 2;
        while (i < j) {
            while (i < j && nums[i] <= pivot) i++;
            while (i < j && nums[j] > pivot) j--;
            if (i < j) swap(nums, i, j);
            else break;
        }
        swap(nums, i, right - 1); // nums[i]为主元的最终位置
        //print(nums);

        // 递归处理左右子集
        qSort(nums, left, i-1);
        qSort(nums, i+1, right);
    }

    // 3数取中法，选取主元
    private int selectPivot(int[] nums, int left, int right) {
        int mid = (right - left) / 2 + left;
        if (nums[left] > nums[mid]) swap(nums, left, mid);
        if (nums[left] > nums[right]) swap(nums, left, right);
        if (nums[mid] > nums[right]) swap(nums, mid, right);

        // 此时，nums[mid]即为中位数
        // 为了后续处理方便，把主元交换到'right-1'处
        swap(nums, mid, right - 1);
        return nums[right - 1]; // 返回主元
    }

    private void swap(int[] nums, int left, int right) {
        int t = nums[left];
        nums[left] = nums[right];
        nums[right] = t;
    }


    private static void print(int[] nums) {
        for (int val : nums) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QuickSort obj = new QuickSort();
        int[] nums = new int[]{2, 4, 6, 5, 7, 1, 3};
        obj.quickSort(nums);
        print(nums);

        nums = new int[]{1, 1, 1, 1, 1};
        obj.quickSort(nums);
        print(nums);

        nums = new int[]{1};
        obj.quickSort(nums);
        print(nums);

        nums = new int[0];
        obj.quickSort(nums);
        print(nums);
    }
}

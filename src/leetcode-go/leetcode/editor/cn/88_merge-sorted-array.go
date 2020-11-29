//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 
//
// 说明： 
//
// 
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。 
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出：[1,2,2,3,5,6] 
//
// 
//
// 提示： 
//
// 
// -10^9 <= nums1[i], nums2[i] <= 10^9 
// nums1.length == m + n 
// nums2.length == n 
// 
// Related Topics 数组 双指针 
// 👍 693 👎 0

package main

import "fmt"

// 从后向前
//leetcode submit region begin(Prohibit modification and deletion)
func merge(nums1 []int, m int, nums2 []int, n int) {
	k := m + n - 1
	i := m - 1
	j := n - 1
	for i >= 0 && j >= 0 {
		if nums1[i] >= nums2[j] {
			nums1[k] = nums1[i]
			i--
		} else {
			nums1[k] = nums2[j]
			j--
		}
		k--
	}
	for i >= 0 {
		nums1[k] = nums1[i]
		i--
		k--
	}
	for j >= 0 {
		nums1[k] = nums2[j]
		j--
		k--
	}
}

//leetcode submit region end(Prohibit modification and deletion)

func main() {
	nums1 := []int{1,4,7,0,0,0}
	nums2 := []int{2,3,6}
	merge(nums1, len(nums1)-len(nums2), nums2, len(nums2))
	fmt.Println(nums1)
}

// 題目：Find First and Last Position of Element in Sorted Array
// 在非遞減排序的陣列 nums 中，找出目標值 target 的起始與結束索引；若不存在回傳 [-1, -1]。
// 解法：兩次二分（lower_bound / upper_bound）

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int left = lowerBound(nums, target);        // 第一個 >= target 的位置
        if (left == n || nums[left] != target) {
            return new int[] {-1, -1};              // 沒找到
        }
        int right = upperBound(nums, target) - 1;   // 最後一個 <= target 的位置
        return new int[] {left, right};
    }

    // 回傳第一個 >= x 的索引；若不存在回傳 n
    private int lowerBound(int[] a, int x) {
        int l = 0, r = a.length;                    // [l, r)
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] >= x) r = m;
            else l = m + 1;
        }
        return l;
    }

    // 回傳第一個 > x 的索引；若不存在回傳 n
    private int upperBound(int[] a, int x) {
        int l = 0, r = a.length;                    // [l, r)
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] > x) r = m;
            else l = m + 1;
        }
        return l;
    }
}

/*
解題思路：
1) 用 lowerBound 找第一個 >= target 的索引 left。
2) 若 left 越界或 a[left] != target，代表不存在。
3) 用 upperBound 找第一個 > target 的索引，right = 該索引 - 1。
   兩次二分即能得到 [left, right]。

複雜度：
- 時間：O(log n)（兩次二分）
- 空間：O(1)
*/

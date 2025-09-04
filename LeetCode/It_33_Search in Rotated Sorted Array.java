// 題目：Search in Rotated Sorted Array
// 給定「不重複」升序陣列在未知點旋轉後的結果 nums 與 target，找 target 的索引（不存在回傳 -1）。
// 要求 O(log n)。

class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) return m;

            // 判斷哪側是「有序」的一半
            if (nums[l] <= nums[m]) { // 左半有序
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;        // 目標在左半
                } else {
                    l = m + 1;        // 否則在右半
                }
            } else {                   // 右半有序
                if (nums[m] < target && target <= nums[r]) {
                    l = m + 1;        // 目標在右半
                } else {
                    r = m - 1;        // 否則在左半
                }
            }
        }
        return -1;
    }
}

/*
思路：
- 每次以 mid 劃分兩半，至少有一半是有序的（因為只旋轉一次且元素互異）。
- 若 target 落在有序半部的範圍就往那邊縮；否則往另一半縮。

複雜度：
- 時間 O(log n)
- 空間 O(1)
*/

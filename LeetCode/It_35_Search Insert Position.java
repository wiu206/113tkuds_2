// 題目：Search Insert Position
// 在已排序且不重複的整數陣列 nums 中，若存在 target 回傳其索引；否則回傳按序插入時的索引。
// 解法：lower_bound 二分搜尋（第一個 >= target 的位置）

class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;   // 搜尋區間 [l, r)
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) r = m;  // 縮到左半，尋找第一個 >= target
            else l = m + 1;                // target 在右半
        }
        return l; // l 即第一個 >= target 的位置（若都小於 target，l == nums.length）
    }
}

/*
解題思路：
- 典型 lower_bound，維護半開區間 [l, r)，迴圈結束時 l == r。
- 若 target 存在，回傳其最左出現位置；若不存在，回傳應插入的位置。

複雜度：
- 時間 O(log n)
- 空間 O(1)
*/

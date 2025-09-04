// 題目：Remove Duplicates from Sorted Array
// 已排序（非遞減）整數陣列 nums，原地刪除重複，使每個元素只出現一次，並回傳唯一元素數量 k。
// 要求：使得前 k 個位置放入去重後的元素，且相對順序不變。

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int k = 1; // 慢指針：下一個要寫入的位置（保留第 0 個）
        for (int i = 1; i < nums.length; i++) { // 快指針：掃描整個陣列
            if (nums[i] != nums[k - 1]) {      // 發現新數值（與最後寫入的不相同）
                nums[k] = nums[i];             // 寫到前段
                k++;
            }
        }
        return k;
    }
}

/*
解題思路（雙指針）：
- 陣列已排序，重複值一定相鄰。用 k 指向「去重後最後一個元素的下一格」，
  i 從左到右掃描；當 nums[i] != nums[k-1] 時把 nums[i] 複製到 nums[k]，k++。
- 完成後，前 k 個元素即為去重結果。

複雜度：
- 時間：O(n)
- 空間：O(1)
*/

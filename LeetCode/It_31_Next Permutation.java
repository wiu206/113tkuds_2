// 題目：Next Permutation
// 將陣列就地改為「字典序中的下一個排列」。若不存在（已是最大），則改為最小（遞增排序）。

class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        // 1) 從右往左找第一個「下降」的位置 i，使得 nums[i] < nums[i+1]
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        if (i >= 0) {
            // 2) 從右側找第一個 > nums[i] 的元素 j，交換 i 與 j
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        // 3) 將 i 右側整段反轉，使其成為最小遞增序
        reverse(nums, i + 1, n - 1);
    }

    private void reverse(int[] a, int l, int r) {
        while (l < r) swap(a, l++, r--);
    }
    private void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
}

/*
解題思路（經典三步）：
1) 從右找第一個下降點 i（nums[i] < nums[i+1]），這是需要變動的「樞紐」。
2) 再從右找第一個大於 nums[i] 的 j，交換兩者，可使字典序變大且增幅最小。
3) 將 i 右側反轉為遞增，得到最小後綴，保證是「下一個」排列。

複雜度：
- 時間 O(n)
- 空間 O(1)
*/

// 題目：3Sum
// 給定整數陣列 nums，找出所有和為 0、且三元組 (i, j, k) 互不相同、內容不重覆的組合。

import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);                 // 先排序，方便雙指針與去重
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 跳過相同起點，避免重覆三元組
            if (nums[i] > 0) break;                         // 最小的都 > 0，後面不可能湊成 0

            int l = i + 1, r = n - 1;
            int target = -nums[i];                          // 轉為 two-sum 目標

            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum == target) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++; r--;
                    // 跳過重覆值，確保不產生相同三元組
                    while (l < r && nums[l] == nums[l - 1]) l++;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return ans;
    }
}

/*
解題思路（排序 + 雙指針）：
1) 將陣列排序。
2) 針對每個 i，目標轉為 two-sum：在 (i+1 .. n-1) 內找 nums[l] + nums[r] = -nums[i]。
3) 為避免重覆：
   - i 與 i-1 相同時跳過；
   - 找到一組後，l 與 r 分別略過與前一個相同的值。
4) 若 nums[i] > 0，可提前結束，因排序後三數相加不可能為 0。

複雜度：
- 時間：O(n^2)（外層枚舉 i × 內層雙指針）
- 空間：O(1)（不含輸出）
*/

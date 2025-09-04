// 題目：4Sum
// 給定整數陣列 nums 與目標值 target，找出所有不重覆的四元組 [a,b,c,d] 使得 a+b+c+d == target。

import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        if (n < 4) return res;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重：固定 i

            // 早停剪枝（用 long 防溢位）
            long min1 = (long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (min1 > target) break; // i 之後的最小和都大於 target，直接結束
            long max1 = (long) nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3];
            if (max1 < target) continue; // i 太小，換下一個 i

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 去重：固定 j

                long min2 = (long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                if (min2 > target) break; // j 後面最小和超過 target，換下一個 i
                long max2 = (long) nums[i] + nums[j] + nums[n - 1] + nums[n - 2];
                if (max2 < target) continue; // j 太小，換下一個 j

                int l = j + 1, r = n - 1;
                while (l < r) {
                    long sum = (long) nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        l++; r--;
                        while (l < r && nums[l] == nums[l - 1]) l++; // 去重：移動 l
                        while (l < r && nums[r] == nums[r + 1]) r--; // 去重：移動 r
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return res;
    }
}

/*
解題思路（排序 + 雙指針）：
1. 先排序；外層固定 i、j，內層對區間 [j+1, n-1] 用雙指針 l、r 夾逼。
2. 透過跳過相同的 i、j、l、r 來避免重覆四元組。
3. 以 long 計算與剪枝，避免整數溢位並提早排除不可能情況。

複雜度：
- 時間：O(n^3)（兩層循環 × 內層雙指針 O(n)）
- 空間：O(1)（輸出除外）
*/

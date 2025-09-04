// 題目：3Sum Closest
// 給定整數陣列 nums 與目標值 target，找出三數之和最接近 target 的總和（保證唯一答案）。

import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = nums[0] + nums[1] + nums[2];   // 初始答案（任取前三個）

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 可選：略過重複起點

            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                // 若更接近 target，更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }

                if (sum == target) return target;     // 已達最接近，直接回傳
                if (sum < target) {
                    l++;
                    while (l < r && nums[l] == nums[l - 1]) l++; // 可選去重
                } else {
                    r--;
                    while (l < r && nums[r] == nums[r + 1]) r--; // 可選去重
                }
            }
        }
        return best;
    }
}

/*
解題思路：
1) 先排序，固定一個 i，對區間 [i+1, n-1] 用雙指針找最接近 target - nums[i] 的兩數和。
2) 每次比較 |sum - target| 是否更小來更新 best；sum 與 target 比較後移動指針。
3) 若 sum == target，答案即為 target。

複雜度：
- 時間：O(n^2)
- 空間：O(1)（不含排序就地）
*/

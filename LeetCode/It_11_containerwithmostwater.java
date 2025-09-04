// 題目：Container With Most Water
// 給定整數陣列 height，代表 x 軸上多條直線的高度。
// 選擇兩條線與 x 軸構成容器，使其能盛最多的水，回傳最大容量。

class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;

        // 兩端雙指針：每次計算容量後，移動「較矮」的那一側
        while (l < r) {
            int h = Math.min(height[l], height[r]);
            ans = Math.max(ans, h * (r - l));
            if (height[l] < height[r]) {
                l++;            // 嘗試找更高的左邊
            } else {
                r--;            // 嘗試找更高的右邊
            }
        }
        return ans;
    }
}

/*
解題思路：
1. 容量 = min(height[l], height[r]) * (r - l)。
2. 從左右兩端開始；每次移動較矮的一側，因為容量受較矮者限制，
   只有把較矮者往內移動才有機會讓 min(...) 變大。
3. 如此每個指針最多各走一遍，即可找出最大值。

複雜度：
- 時間複雜度：O(n)
- 空間複雜度：O(1)

備註（可選優化思路）：
- 在一次計算後，可略過所有 ≤ 當前 h 的連續桿子，因為它們不可能帶來更大的 min 高度。
*/
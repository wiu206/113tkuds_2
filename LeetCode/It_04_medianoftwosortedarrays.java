class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 確保對較短的陣列二分
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length, n = nums2.length;

        int left = 0, right = m;
        int half = (m + n + 1) / 2;

        while (left <= right) {
            int i = (left + right) >>> 1;   // nums1 的切點
            int j = half - i;               // nums2 的切點

            int Aleft  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int Aright = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int Bleft  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int Bright = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (Aleft <= Bright && Bleft <= Aright) {           // 命中正確切割
                if (((m + n) & 1) == 1) {                       // 總長奇數
                    return Math.max(Aleft, Bleft);
                } else {                                         // 總長偶數
                    int leftMax = Math.max(Aleft, Bleft);
                    int rightMin = Math.min(Aright, Bright);
                    return (leftMax + rightMin) / 2.0;
                }
            } else if (Aleft > Bright) {
                right = i - 1;                                   // i 太大，往左
            } else {
                left = i + 1;                                    // i 太小，往右
            }
        }
        // 理論上不會到這裡
        return 0.0;
    }
}

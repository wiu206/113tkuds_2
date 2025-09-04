
// 題目：Divide Two Integers
// 不可使用乘除與取餘，執行整數相除並朝 0 取整。
// 需處理 32 位元整數溢位：INT_MIN / -1 -> 回傳 INT_MAX。

class Solution {
    public int divide(int dividend, int divisor) {
        // 特判唯一會溢位的情況
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 轉為 long 並取絕對值，避免溢位
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        // 結果正負號
        int sign = ((dividend ^ divisor) < 0) ? -1 : 1;

        int res = 0;
        // 倍增 + 位移：每次用最大的 2^k * b 去減 a
        while (a >= b) {
            long temp = b;
            int multiple = 1;
            // 盡量把除數左移（×2）直到超過被除數
            while ((temp << 1) <= a) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;        // 扣掉這一大塊
            res += multiple;  // 商加上對應 2^k
        }

        return sign > 0 ? res : -res;
    }
}

/*
解題思路（倍增 + 位移）：
1) 以 long 存放並取絕對值，避免溢位；先處理 INT_MIN / -1 的特例。
2) 透過左移把除數倍增到不超過被除數的最大值，減去後累加商的倍數，重複直到 a < b。
3) 依 dividend 與 divisor 的異同號決定結果正負。

複雜度：
- 時間：O(log |dividend|)（每輪把 temp 倍增）
- 空間：O(1)
*/

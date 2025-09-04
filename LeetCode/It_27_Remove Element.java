// 題目：Remove Element
// 給定整數陣列 nums 與數值 val，原地刪除所有等於 val 的元素，回傳刪除後的長度 k。
// 需求：前 k 個位置存放不等於 val 的元素；其餘內容不重要，元素順序可改變。

class Solution {
    // 解法一：穩定雙指針（保留原相對順序）
    public int removeElement(int[] nums, int val) {
        int k = 0;                          // 下一個寫入位置
        for (int x : nums) {
            if (x != val) nums[k++] = x;    // 只把保留元素往前覆寫
        }
        return k;
    }
}

/*
解題思路：
- 陣列單向掃描，k 指向「已保留區」的尾端。
- 遇到不等於 val 的值就覆寫到 nums[k]，並遞增 k。
- 掃描完成後，k 即為結果長度，nums[0..k-1] 為保留元素。

複雜度：
- 時間 O(n)
- 空間 O(1)

（可選）若不在意順序、想減少寫入次數，可用「尾端交換」法：
int i = 0, n = nums.length;
while (i < n) {
    if (nums[i] == val) nums[i] = nums[--n];  // 尾端覆寫，長度縮小
    else i++;
}
return n;   // n 為刪除後長度
*/

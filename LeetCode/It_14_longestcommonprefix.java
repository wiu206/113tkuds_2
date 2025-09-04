// 題目：Longest Common Prefix
// 給定字串陣列 strs，找出最長的共同前綴（若無則回傳空字串）。

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // 先找出最短字串長度，前綴不可能比它更長
        int minLen = Integer.MAX_VALUE;
        for (String s : strs) minLen = Math.min(minLen, s.length());

        // 垂直掃描：逐字比較每一欄位
        for (int j = 0; j < minLen; j++) {
            char c = strs[0].charAt(j);
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(j) != c) {
                    return strs[0].substring(0, j);
                }
            }
        }
        return strs[0].substring(0, minLen);
    }
}

/*
解題思路：
1. 共同前綴長度不會超過最短字串，因此先取得 minLen。
2. 以「垂直掃描」方式，從第 0 個字元開始逐欄比較：
   - 若某一欄有字元不同，答案即為到上一欄為止的前綴。
   - 若走完 minLen 都一致，回傳最短字串長度的前綴。

複雜度：
- 時間：O(總字元數)（最壞情況比較到最短字串長度 × 字串數量）
- 空間：O(1)

備註（另一常見寫法）：
- 也可用「不斷縮短前綴」法：prefix = strs[0]，對每個字串
  while(!s.startsWith(prefix)) prefix = prefix.substring(0, prefix.length()-1)；
  直觀但在某些實作下可能有較多 substring 建立。
*/

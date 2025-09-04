// 題目：Count and Say
// 定義：countAndSay(1) = "1"
//      countAndSay(n) 是 countAndSay(n-1) 的「連續字元長度編碼」（RLE），以“次數+字元”表示。

class Solution {
    public String countAndSay(int n) {
        String s = "1";
        for (int k = 2; k <= n; k++) {
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < s.length(); ) {
                char c = s.charAt(i);
                int j = i;
                // 數出從 i 開始連續相同的字元個數
                while (j < s.length() && s.charAt(j) == c) j++;
                t.append(j - i).append(c); // 次數 + 該字元
                i = j;
            }
            s = t.toString();
        }
        return s;
    }
}

/*
解題思路（逐層生成 / RLE）：
1) 從 "1" 出發，重複 n-1 次：
   - 掃描上一層字串 s，對每段連續相同字元做 run-length encoding：
     計數 len 與字元 ch，拼接為 "len"+"ch"。
2) 新字串成為下一層的輸入，直到第 n 層。

複雜度：
- 時間：O(生成字串總長度)；每層線性掃描上一層。
- 空間：O(當層字串長度) 用於建構下一層的 StringBuilder。
*/

// 題目：Longest Valid Parentheses
// 給定只包含 '('、')' 的字串 s，回傳最長「有效括號」子字串的長度。

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    // 解法：堆疊 + 基準索引，O(n) 時間 / O(n) 空間
    public int longestValidParentheses(String s) {
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);                       // 基準：最後一個不匹配位置

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(i);                // 記錄 '(' 的位置
            } else {                       // c == ')'
                st.pop();                  // 嘗試匹配一個 '('
                if (st.isEmpty()) {
                    st.push(i);            // 沒得匹配，更新基準
                } else {
                    ans = Math.max(ans, i - st.peek()); // 以當前 i 為末端的最長長度
                }
            }
        }
        return ans;
    }
}

/*
解題思路（Stack）：
1) 用堆疊存放「尚未匹配的 '(' 的索引」，並先丟入 -1 當基準（最後一個不合法位置）。
2) 遇到 '('：把索引推入堆疊。
3) 遇到 ')'：先彈出一個索引（匹配一個 '('）。
   - 若堆疊空了，代表目前這個 ')' 也不匹配，把它的索引當新的基準 push 進去；
   - 否則，以 i - stack.peek() 更新答案（peek 是當前合法段落的左邊界）。
4) 走完整串即可得到最長長度。

複雜度：
- 時間：O(n)
- 空間：O(n)

補充（DP 另一常見寫法，僅示意）：
dp[i] 表示以 i 結尾的最長合法長度（i 上是 ')' 才可能 >0）：
- 若 s[i-1]=='('，dp[i] = dp[i-2] + 2
- 若 s[i-1]==')' 且 s[i - dp[i-1] - 1]=='('，dp[i] = dp[i-1] + 2 + dp[i - dp[i-1] - 2]
最終答案為 max(dp)；時間 O(n)、空間 O(n)。
*/

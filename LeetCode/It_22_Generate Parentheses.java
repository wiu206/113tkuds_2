// 題目：Generate Parentheses
// 給定 n 對括號，產生所有「合法」括號組合。

import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder(2 * n);
        dfs(0, 0, n, path, res);
        return res;
    }

    // open: 已放入 '(' 的數量；close: 已放入 ')' 的數量
    private void dfs(int open, int close, int n, StringBuilder path, List<String> res) {
        if (path.length() == 2 * n) {           // 長度滿了即為一種合法組合
            res.add(path.toString());
            return;
        }
        if (open < n) {                          // 還能放 '('
            path.append('(');
            dfs(open + 1, close, n, path, res);
            path.deleteCharAt(path.length() - 1);
        }
        if (close < open) {                      // 右括號只能在不違規時加入
            path.append(')');
            dfs(open, close + 1, n, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }
}

/*
解題思路（回溯 + 剪枝）：
1) 任何時刻都滿足：0 ≤ open ≤ n，且 close ≤ open（避免出現 "())(" 這類不合法前綴）。
2) 當字串長度達 2n 時收錄結果。

複雜度：
- 生成的組合數為第 n 個 Catalan 數 Cn，時間 ≈ O(Cn)，空間 O(n)（遞迴深度與路徑）。
*/

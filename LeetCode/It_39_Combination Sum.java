// 題目：Combination Sum
// 給定「互不相同」的 candidates，找出所有和為 target 的組合。
// 每個數可被使用「無限次」，組合內元素順序不影響唯一性。

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);                 // 先排序，方便剪枝
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, target, candidates, new ArrayList<>(), res);
        return res;
    }

    // 從 index 開始選數；remain 為剩餘目標
    private void dfs(int index, int remain, int[] a, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {                       // 剛好湊滿
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < a.length; i++) {
            if (a[i] > remain) break;            // 剪枝：後面更大，不必再試
            path.add(a[i]);                      // 可重複使用，所以遞迴仍從 i 開始
            dfs(i, remain - a[i], a, path, res);
            path.remove(path.size() - 1);        // 回溯
        }
    }
}

/*
解題思路（回溯 + 剪枝）：
1) 將 candidates 排序，迴圈時若 a[i] > remain 即可停止（剪枝）。
2) 用 index 控制「可選起點」，確保組合不重複（如 [2,3] 與 [3,2] 只取一個）。
3) 因為每個數可重複取，遞迴時仍傳 i（不是 i+1）。

複雜度：
- 時間：取決於解的數量，最壞情況接近指數；排序 O(n log n)。
- 空間：O(target/最小元素) 的遞迴深度與路徑儲存。
*/

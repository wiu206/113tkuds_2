// 題目：Combination Sum II
// candidates 可能含重複元素，但每個元素最多使用一次。
// 回傳所有和為 target 的「不重複」組合（順序不影響唯一性）。

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);                 // 先排序，方便去重與剪枝
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, target, candidates, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int start, int remain, int[] a, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {                       // 找到一組
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < a.length; i++) {
            if (a[i] > remain) break;            // 剪枝：後面更大，不必再試

            // 去重關鍵：同一層迭代中，若當前數與前一個相同則跳過
            if (i > start && a[i] == a[i - 1]) continue;

            path.add(a[i]);
            dfs(i + 1, remain - a[i], a, path, res); // 每個數最多用一次 → 下一層從 i+1
            path.remove(path.size() - 1);            // 回溯
        }
    }
}

/*
解題思路（回溯 + 排序去重）：
1) 先排序，之後同一層迭代時若 a[i] == a[i-1] 就跳過，避免重複組合。
2) 每個數只能用一次，所以遞迴呼叫用 i+1 當下一層起點。
3) 若 a[i] > remain 可提前停止（剪枝）。

複雜度：
- 時間：取決於解的數量，最壞接近指數；排序 O(n log n)。
- 空間：O(k)（遞迴深度與暫存路徑，k ≤ candidates.length）
*/

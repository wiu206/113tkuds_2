class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        Boolean[][] memo = new Boolean[m + 1][n + 1];
        return dfs(0, 0, s, p, memo);
    }

    private boolean dfs(int i, int j, String s, String p, Boolean[][] memo) {
        if (memo[i][j] != null) return memo[i][j];

        if (j == p.length()) {                 // pattern 用完：必須字串也用完
            return memo[i][j] = (i == s.length());
        }

        boolean first = (i < s.length() &&
                         (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

        boolean ans;
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // 0 次 或 ≥1 次
            ans = dfs(i, j + 2, s, p, memo) || (first && dfs(i + 1, j, s, p, memo));
        } else {
            ans = first && dfs(i + 1, j + 1, s, p, memo);
        }
        return memo[i][j] = ans;
    }
}

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

        int r = 0, step = 1; // step=1 向下, step=-1 向上
        for (int i = 0; i < s.length(); i++) {
            rows[r].append(s.charAt(i));
            if (r == 0) step = 1;
            else if (r == numRows - 1) step = -1;
            r += step;
        }

        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) ans.append(row);
        return ans.toString();
    }
}

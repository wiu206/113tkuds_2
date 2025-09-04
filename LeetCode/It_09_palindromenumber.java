class Solution {
    public boolean isPalindrome(int x) {
        // 負數或尾數為 0（但非 0 本身）直接不是回文
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int rev = 0; // 反轉的「右半部」
        while (x > rev) {              // 只反轉到一半
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return x == rev || x == rev / 10;
    }
}

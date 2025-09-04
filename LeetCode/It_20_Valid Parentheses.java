// 題目：Valid Parentheses
// 給定只包含 ()[]{} 的字串 s，判斷是否為有效括號序列。

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean isValid(String s) {
        // 奇數長度一定不可能完全配對
        if ((s.length() & 1) == 1) return false;

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 開括號：把「預期的對應閉括號」推入堆疊
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else {
                // 閉括號：堆疊為空或不相同即非法
                if (stack.isEmpty() || stack.pop() != c) return false;
            }
        }
        // 全部匹配後堆疊須為空
        return stack.isEmpty();
    }
}

/*
解題思路：
1) 採用堆疊。遇到開括號就把「對應的閉括號」推入堆疊；
2) 遇到閉括號時，必須與堆疊頂端相同才合法；
3) 最後堆疊須為空。

複雜度：
- 時間：O(n)
- 空間：O(n)
*/

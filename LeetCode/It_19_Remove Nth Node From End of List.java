// 題目：Remove Nth Node From End of List
// 給定單向鏈結串列 head，刪除「從尾端數第 n 個」節點並回傳新表頭。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 加入虛擬頭節點，統一處理刪除 head 的情況
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy, slow = dummy;

        // 讓 fast 先走 n 步，建立相距 n 的雙指針
        for (int i = 0; i < n; i++) fast = fast.next;

        // 一起前進直到 fast 到尾端，slow 停在目標前一個
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除目標節點
        slow.next = slow.next.next;
        return dummy.next;
    }
}

/*
解題思路（雙指針 + 虛擬頭）：
1. 用 dummy 指向 head，處理刪除第一個節點的邊界。
2. 先讓 fast 前進 n 步，保持 fast 與 slow 相距 n。
3. 同步移動兩指針直到 fast 到最後一個節點，此時 slow 正好在目標的前一格。
4. 將 slow.next 指向 slow.next.next 完成刪除。

複雜度：
- 時間：O(L)（L 為鏈長）
- 空間：O(1)

備註：
- 題目保證 1 ≤ n ≤ 鏈長，上述寫法不會越界。
*/

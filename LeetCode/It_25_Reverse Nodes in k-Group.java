// 題目：Reverse Nodes in k-Group
// 給定鏈結串列 head，每 k 個節點為一組反轉；不足 k 的最後一組保持原樣。

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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;

        while (true) {
            // 1) 取得當前區間的第 k 個節點（區間尾）
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) break;               // 不足 k，結束
            ListNode groupNext = kth.next;        // 下一段的起點

            // 2) 反轉 [groupPrev.next, kth] 這段（尾端接回 groupNext）
            ListNode prev = groupNext;
            ListNode cur = groupPrev.next;
            while (cur != groupNext) {            // 迭代反轉直到碰到 groupNext（不含）
                ListNode tmp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = tmp;
            }

            // 3) 連回前後：prev 會是新的段首(原 kth)，groupPrev.next 是原段首(現在段尾)
            ListNode newGroupHead = prev;
            ListNode newGroupTail = groupPrev.next;

            groupPrev.next = newGroupHead;
            groupPrev = newGroupTail;             // 移動到下一段前一個
        }
        return dummy.next;
    }

    // 從 node 之後往前走 k 步，回傳第 k 個節點；若不足 k 則回傳 null
    private ListNode getKth(ListNode node, int k) {
        while (node != null && k > 0) {
            node = node.next;
            k--;
        }
        return node;
    }
}

/*
解題思路（迭代 + 就地反轉）：
1) 用 dummy 串在最前面；groupPrev 指向每組的前一個節點。
2) 找到該組的第 k 個節點 kth（若找不到代表剩餘不足 k，直接結束）。
3) 將 [groupPrev.next, kth] 這段就地反轉，尾端指向 groupNext（kth.next）。
4) 接回前後並把 groupPrev 移到新段尾，繼續處理下一組。

時間複雜度：O(n)（每節點被反轉一次）
空間複雜度：O(1)
*/```

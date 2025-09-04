// 題目：Swap Nodes in Pairs
// 給定單向鏈結串列，兩兩交換相鄰節點並回傳新表頭（不可只改 val，需改指標）。

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
    // 迭代解：O(n) 時間、O(1) 額外空間
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head); // 虛擬頭，統一處理
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;       // 第一個
            ListNode b = a.next;          // 第二個

            // 交換：prev -> b -> a -> next
            a.next = b.next;
            b.next = a;
            prev.next = b;

            prev = a;                     // 移動到下一組的前一個
        }
        return dummy.next;
    }
}

/*
解題思路：
- 用 dummy 連到 head，prev 指向每一組的前一個節點。
- 令 a=prev.next，b=a.next，調整三條邊完成 prev->b->a->next 的結構，再把 prev 移到 a。
- 不改節點值，只改指標，符合題意。

複雜度：
- 時間 O(n)
- 空間 O(1)

（可選）遞迴寫法：
if (head == null || head.next == null) return head;
ListNode nxt = head.next;
head.next = swapPairs(nxt.next);
nxt.next = head;
return nxt;
*/

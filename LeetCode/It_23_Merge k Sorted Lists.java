// 題目：Merge k Sorted Lists
// 將 k 條已排序的單向鏈結串列合併為一條升序串列並回傳表頭。

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
import java.util.PriorityQueue;

class Solution {
    // 解法一：最小堆 (PriorityQueue)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq =
            new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0), cur = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) pq.offer(node.next);
        }
        return dummy.next;
    }
}

/*
解題思路（最小堆）：
1) 將每條鏈的表頭（非空）放入最小堆，堆頂永遠是目前最小值的節點。
2) 取出堆頂接到結果串列後，若該節點仍有下一個，將下一個再放回堆中。
3) 直到堆空為止。

複雜度：
- 時間：O(N log k)，N 為所有節點總數，k 為鏈條數（每個節點入堆/出堆一次）。
- 空間：O(k) 來自最小堆。

（可選）解法二：分治兩兩合併（時間 O(N log k)，空間 O(1) 就地合併）
- 觀念：像歸併排序那樣，把 k 條列表兩兩合併，層數是 log k。
- 需要一個 mergeTwo(ListNode a, ListNode b) 幫手函式。
*/

// 題目：Merge Two Sorted Lists
// 將兩個已排序的單向鏈結串列合併為一條升序串列並回傳表頭。

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 使用虛擬頭節點，簡化邊界處理
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        // 其中一條還有剩，直接接在尾端
        cur.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }
}

/*
解題思路：
- 兩指針逐步比較兩串列當前節點的值，將較小者接到結果串列後面並前進。
- 用 dummy 節點可避免處理 head 變動的麻煩。

複雜度：
- 時間：O(m+n)
- 空間：O(1)（就地串接，未額外分配新節點）

（可選）遞迴版本：
ListNode mergeTwoLists(ListNode a, ListNode b){
    if (a == null) return b;
    if (b == null) return a;
    if (a.val <= b.val) { a.next = mergeTwoLists(a.next, b); return a; }
    else { b.next = mergeTwoLists(a, b.next); return b; }
}
*/

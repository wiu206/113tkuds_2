import java.io.*;
import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    static class ListNode { int val; ListNode next; ListNode(int v){val=v;} }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        if (s1 == null || s1.trim().isEmpty()) { System.out.print(""); return; }
        int k = Integer.parseInt(s1.trim());

        String s2 = br.readLine();
        if (s2 == null || s2.trim().isEmpty()) { System.out.print(""); return; }

        StringTokenizer st = new StringTokenizer(s2);
        ListNode head = null, tail = null;
        while (st.hasMoreTokens()) {
            int x = Integer.parseInt(st.nextToken());
            ListNode node = new ListNode(x);
            if (head == null) head = tail = node; else { tail.next = node; tail = node; }
        }

        if (k > 1) head = reverseKGroup(head, k);

        StringBuilder sb = new StringBuilder();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(cur.val);
        }
        System.out.print(sb.toString());
    }

    static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0); dummy.next = head;
        ListNode gp = dummy;
        while (true) {
            ListNode kth = getKth(gp, k);
            if (kth == null) break;
            ListNode gn = kth.next;
            ListNode prev = gn, cur = gp.next;
            while (cur != gn) {
                ListNode nxt = cur.next;
                cur.next = prev;
                prev = cur;
                cur = nxt;
            }
            ListNode tmp = gp.next;
            gp.next = kth;
            gp = tmp;
        }
        return dummy.next;
    }

    static ListNode getKth(ListNode start, int k) {
        while (start != null && k > 0) { start = start.next; k--; }
        return start;
    }
}

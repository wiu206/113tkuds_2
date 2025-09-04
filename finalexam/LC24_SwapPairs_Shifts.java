import java.io.*;
import java.util.*;

public class LC24_SwapPairs_Shifts {
    static class ListNode {
        int val; ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) { System.out.print(""); return; }
        StringTokenizer st = new StringTokenizer(line);
        ListNode head = null, tail = null;
        while (st.hasMoreTokens()) {
            int x = Integer.parseInt(st.nextToken());
            ListNode node = new ListNode(x);
            if (head == null) head = tail = node;
            else { tail.next = node; tail = node; }
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next, b = a.next;
            a.next = b.next;
            b.next = a;
            prev.next = b;
            prev = a;
        }

        StringBuilder sb = new StringBuilder();
        for (ListNode cur = dummy.next; cur != null; cur = cur.next) sb.append(cur.val).append(' ');
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        System.out.print(sb.toString());
    }
}

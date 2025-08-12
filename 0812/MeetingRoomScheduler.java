import java.util.*;

public class MeetingRoomScheduler {
    public static int minMeetingRooms(int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int[] m : meetings) {
            if (!minHeap.isEmpty() && minHeap.peek() <= m[0]) {
                minHeap.poll();
            }
            minHeap.offer(m[1]);
        }
        return minHeap.size();
    }

    public static int maxTotalMeetingTime(int[][] meetings, int rooms) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[1]));
        return scheduleMaxTime(meetings, rooms);
    }

    private static int scheduleMaxTime(int[][] meetings, int rooms) {
        if (rooms == 1) {
            int lastEnd = -1;
            int total = 0;
            for (int[] m : meetings) {
                if (m[0] >= lastEnd) {
                    total += m[1] - m[0];
                    lastEnd = m[1];
                }
            }
            return total;
        }
        int[] dp = new int[meetings.length + 1];
        int[] start = new int[meetings.length];
        int[] end = new int[meetings.length];
        for (int i = 0; i < meetings.length; i++) {
            start[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }
        for (int i = 1; i <= meetings.length; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
            int j = i - 1;
            while (j >= 0 && end[j] > start[i - 1]) j--;
            dp[i] = Math.max(dp[i], dp[j + 1] + (end[i - 1] - start[i - 1]));
        }
        return dp[meetings.length];
    }

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
        System.out.println(minMeetingRooms(new int[][]{{9,10},{4,9},{4,17}}));
        System.out.println(minMeetingRooms(new int[][]{{1,5},{8,9},{8,9}}));
        System.out.println(maxTotalMeetingTime(new int[][]{{1,4},{2,3},{4,6}}, 1));
    }
}

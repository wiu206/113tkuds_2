import java.util.*;

class Task implements Comparable<Task> {
    String name;
    int priority;
    long timestamp;

    Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority);
        }
        return Long.compare(this.timestamp, other.timestamp);
    }

    @Override
    public String toString() {
        return name + " (優先級 " + priority + ")";
    }
}

public class PriorityQueueWithHeap {
    private PriorityQueue<Task> pq;
    private long counter;

    public PriorityQueueWithHeap() {
        pq = new PriorityQueue<>();
        counter = 0;
    }

    public void addTask(String name, int priority) {
        pq.offer(new Task(name, priority, counter++));
    }

    public Task executeNext() {
        return pq.poll();
    }

    public Task peek() {
        return pq.peek();
    }

    public void changePriority(String name, int newPriority) {
        List<Task> temp = new ArrayList<>();
        while (!pq.isEmpty()) {
            Task t = pq.poll();
            if (t.name.equals(name)) {
                temp.add(new Task(t.name, newPriority, counter++));
            } else {
                temp.add(t);
            }
        }
        pq.addAll(temp);
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap queue = new PriorityQueueWithHeap();
        queue.addTask("備份", 1);
        queue.addTask("緊急修復", 5);
        queue.addTask("更新", 3);

        System.out.println(queue.executeNext());
        System.out.println(queue.executeNext());
        System.out.println(queue.executeNext());
    }
}

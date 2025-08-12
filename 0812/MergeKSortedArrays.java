import java.util.*;

class ArrayNode implements Comparable<ArrayNode> {
    int value;
    int arrayIndex;
    int elementIndex;

    ArrayNode(int value, int arrayIndex, int elementIndex) {
        this.value = value;
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }

    @Override
    public int compareTo(ArrayNode other) {
        return Integer.compare(this.value, other.value);
    }
}

public class MergeKSortedArrays {
    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<ArrayNode> minHeap = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new ArrayNode(arrays[i][0], i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            ArrayNode node = minHeap.poll();
            result.add(node.value);
            int nextIndex = node.elementIndex + 1;
            if (nextIndex < arrays[node.arrayIndex].length) {
                minHeap.offer(new ArrayNode(arrays[node.arrayIndex][nextIndex], node.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] input1 = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        int[][] input2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] input3 = {{1}, {0}};

        System.out.println(mergeKSortedArrays(input1));
        System.out.println(mergeKSortedArrays(input2));
        System.out.println(mergeKSortedArrays(input3));
    }
}

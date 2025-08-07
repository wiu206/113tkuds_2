import java.util.*;

public class NumberArrayProcessor {

    public static void main(String[] args) {
        int[] array1 = {3, 5, 1, 3, 9, 1, 7, 5};
        int[] sortedA = {1, 3, 5, 7};
        int[] sortedB = {2, 4, 6, 8};

        System.out.println("原始陣列（含重複）：" + Arrays.toString(array1));
        int[] noDuplicates = removeDuplicates(array1);
        System.out.println("移除重複後：" + Arrays.toString(noDuplicates));

        System.out.println("合併兩個已排序陣列：");
        int[] merged = mergeSortedArrays(sortedA, sortedB);
        System.out.println("結果：" + Arrays.toString(merged));

        int mostFrequent = findMostFrequentElement(array1);
        System.out.println("最常出現的元素：" + mostFrequent);

        System.out.println("將陣列分割為兩組和相近的子陣列：");
        List<List<Integer>> split = splitArrayEqually(array1);
        System.out.println("子陣列 1：" + split.get(0));
        System.out.println("子陣列 2：" + split.get(1));
    }

    public static int[] removeDuplicates(int[] array) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : array) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int idx = 0;
        for (int num : set) {
            result[idx++] = num;
        }
        return result;
    }

    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];
        return result;
    }

    public static int findMostFrequentElement(int[] array) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : array) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0, mostFrequent = array[0];
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }

    public static List<List<Integer>> splitArrayEqually(int[] array) {
        Arrays.sort(array);
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();
        int sum1 = 0, sum2 = 0;

        for (int i = array.length - 1; i >= 0; i--) {
            if (sum1 <= sum2) {
                group1.add(array[i]);
                sum1 += array[i];
            } else {
                group2.add(array[i]);
                sum2 += array[i];
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(group1);
        result.add(group2);
        return result;
    }
}

import java.util.*;

public class AdvancedArrayRecursion {

    public static void main(String[] args) {
        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序結果：" + Arrays.toString(arr));

        int[] a = {1, 3, 5, 7};
        int[] b = {2, 4, 6};
        int[] merged = mergeSorted(a, b, 0, 0);
        System.out.println("合併排序陣列：" + Arrays.toString(merged));

        int[] arr2 = {9, 3, 2, 6, 1};
        int k = 3;
        int kthSmallest = findKthSmallest(arr2, 0, arr2.length - 1, k);
        System.out.println("第 " + k + " 小的元素：" + kthSmallest);

        int[] arr3 = {3, 2, 7, 1};
        int target = 6;
        boolean hasSubset = subsetSum(arr3, 0, target);
        System.out.println("是否存在子序列總和為 " + target + "：" + hasSubset);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pi = partition(arr, left, right);
            quickSort(arr, left, pi - 1);
            quickSort(arr, pi + 1, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[right], i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
            }
        }
        int tmp = arr[i + 1]; arr[i + 1] = arr[right]; arr[right] = tmp;
        return i + 1;
    }

    public static int[] mergeSorted(int[] a, int[] b, int i, int j) {
        if (i == a.length) return Arrays.copyOfRange(b, j, b.length);
        if (j == b.length) return Arrays.copyOfRange(a, i, a.length);

        if (a[i] < b[j]) {
            int[] rest = mergeSorted(a, b, i + 1, j);
            int[] result = new int[rest.length + 1];
            result[0] = a[i];
            System.arraycopy(rest, 0, result, 1, rest.length);
            return result;
        } else {
            int[] rest = mergeSorted(a, b, i, j + 1);
            int[] result = new int[rest.length + 1];
            result[0] = b[j];
            System.arraycopy(rest, 0, result, 1, rest.length);
            return result;
        }
    }

    public static int findKthSmallest(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        int pi = partition(arr, left, right);
        int count = pi - left + 1;
        if (k == count) return arr[pi];
        else if (k < count) return findKthSmallest(arr, left, pi - 1, k);
        else return findKthSmallest(arr, pi + 1, right, k - count);
    }

    public static boolean subsetSum(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index == arr.length || target < 0) return false;
        return subsetSum(arr, index + 1, target) || subsetSum(arr, index + 1, target - arr[index]);
    }
}

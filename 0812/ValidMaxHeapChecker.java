public class ValidMaxHeapChecker {
    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < n && arr[i] < arr[left]) {
                System.out.println("false (索引" + left + "的" + arr[left] + "大於父節點" + arr[i] + ")");
                return false;
            }
            if (right < n && arr[i] < arr[right]) {
                System.out.println("false (索引" + right + "的" + arr[right] + "大於父節點" + arr[i] + ")");
                return false;
            }
        }
        System.out.println("true");
        return true;
    }

    public static void main(String[] args) {
        isValidMaxHeap(new int[]{100, 90, 80, 70, 60, 75, 65});
        isValidMaxHeap(new int[]{100, 90, 80, 95, 60, 75, 65});
        isValidMaxHeap(new int[]{50});
        isValidMaxHeap(new int[]{});
    }
}

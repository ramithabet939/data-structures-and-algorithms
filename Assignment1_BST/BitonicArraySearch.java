

public class Q5 {

    private static int findMaxIndex(int[] arr, int lo, int hi) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] > arr[mid + 1]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private static int searchInAscending(int[] arr, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    private static int searchInDescending(int[] arr, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] > target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    public static int findElementInBitonicArray(int[] arr, int target) {
        int peakIndex = findMaxIndex(arr, 0, arr.length - 1);

        if (arr[peakIndex] == target) return peakIndex;

        int result = searchInAscending(arr, 0, peakIndex - 1, target);
        if (result != -1) return result;

        return searchInDescending(arr, peakIndex + 1, arr.length - 1, target);
    }

    public static void main(String[] args) {
        int[] bitonicArray = {1, 3, 7, 8, 12, 9, 5, 2};
        int target = 7;

        int index = findElementInBitonicArray(bitonicArray, target);
        if (index != -1) {
            System.out.println("Element found at index: " + index);
        } else {
            System.out.println("Element not found in the array.");
        }
    }
}

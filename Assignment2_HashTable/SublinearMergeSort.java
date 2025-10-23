import java.util.Arrays;

public class Q2 {
    public static void main(String[] args) {
        
        int N = 8;
        int M = 2; 

        // Initialize array with predefined values
        int[] arr = {9, 4, 7, 2, 8, 1, 5, 6};

        System.out.println("Original array:");
        System.out.println(Arrays.toString(arr));

        // Perform Sublinear Merge Sort
        sublinearMergeSort(arr, N, M);

        // Print sorted array
        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(arr));
    }

    // Sublinear Merge Sort
    public static void sublinearMergeSort(int[] arr, int N, int M) {
        int numBlocks = N / M;  

       
        for (int i = 0; i < numBlocks; i++) {
            selectionSort(arr, i * M, (i + 1) * M - 1);
        }

        
        for (int i = 0; i < numBlocks - 1; i++) {
            inPlaceMerge(arr, 0, (i + 1) * M - 1, (i + 2) * M - 1);
        }
    }

    // Selection Sort 
    private static void selectionSort(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            int minIndex = i;
            for (int j = i + 1; j <= end; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap min element with current position
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    //  merging of two adjacent sorted subarrays
    private static void inPlaceMerge(int[] arr, int left, int mid, int right) {
        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                i++;
            } else {
                // Rotate the smaller element to the correct position
                int temp = arr[j];
                int k = j;

                while (k > i) {
                    arr[k] = arr[k - 1];
                    k--;
                }
                arr[i] = temp;

                i++;
                mid++;
                j++;
            }
        }
    }
}


            

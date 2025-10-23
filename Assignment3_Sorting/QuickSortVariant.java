

public class Q2 {
    
    private static void sortArray(Comparable[] arr) {
        sortArray(arr, 0, arr.length - 1);
    }
    
    private static void sortArray(Comparable[] arr, int start, int finish) {
        if (finish <= start) return;
        
        Comparable key = arr[start];
        int leftEqual = start, rightEqual = finish;
        int scanLeft = start, scanRight = finish;
        
        while (scanLeft <= scanRight) {
            while (scanLeft <= scanRight && arr[scanLeft].compareTo(key) < 0)
                scanLeft++;
            while (scanLeft <= scanRight && arr[scanRight].compareTo(key) > 0)
                scanRight--;
            if (scanLeft <= scanRight) {
                swapElements(arr, scanLeft, scanRight);
                if (arr[scanLeft].compareTo(key) == 0)
                    swapElements(arr, leftEqual++, scanLeft);
                if (arr[scanRight].compareTo(key) == 0)
                    swapElements(arr, rightEqual--, scanRight);
                scanLeft++;
                scanRight--;
            }
            printArr(arr);
            System.out.println();
        }
        
        for (int k = start; k < leftEqual && scanRight >= start; k++, scanRight--) {
            swapElements(arr, k, scanRight);
        }
        for (int k = finish; k > rightEqual && scanLeft <= finish; k--, scanLeft++) {
            swapElements(arr, k, scanLeft);
        }
        
        sortArray(arr, start, scanRight);
        sortArray(arr, scanLeft, finish);
    }
    
    private static void swapElements(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private static void printArr(Comparable[] arr) {
        for (Comparable element : arr) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        String[] array = { "B", "A", "B", "A", "B", "A", "B", "A",
                           "C", "A", "D", "A", "B", "R", "A" };
        
        System.out.println("Before Sorting:");
        printArr(array);
        
        sortArray(array);
        
        System.out.println("\nAfter Sorting:");
        printArr(array);
    }
}


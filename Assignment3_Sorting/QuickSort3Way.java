

public class Q1 {
    private static int stepCounter = 0;
    private static int passCounter = 1;
    
    private static void quickSort(Comparable[] items) {
        quickSort(items, 0, items.length - 1);
    }
    
    private static void quickSort(Comparable[] items, int low, int high) {
        if (high <= low) return;
        int left = low, right = high;
        Comparable pivot = items[low];
        int index = low;
        
        System.out.println("Pass number " + passCounter);
        System.out.println("Stp  Lft  Cur  Rgt   Array");
        System.out.println("-----------------------------");
        
        while (index <= right) {
            int cmp = items[index].compareTo(pivot);
            if (cmp < 0) {
                swap(items, left++, index++);
            } else if (cmp > 0) {
                swap(items, index, right--);
            } else {
                index++;
            }
            
            printState(items, left, stepCounter++, index, right);
        }
        System.out.println();
        passCounter++;
        
        quickSort(items, low, left - 1);
        quickSort(items, right + 1, high);
    }
    
    private static void swap(Comparable[] items, int i, int j) {
        Comparable temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
    
    private static void printState(Comparable[] items, int left, int step, int current, int right) {
        System.out.printf("%2d %3d %3d %4d  ", step, left, current, right);
        for (Comparable element : items) {
            System.out.printf("%3s", element);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        String[] arr = {"B", "A", "B", "A", "B", "A", "B", "A",
                        "C", "A", "D", "A", "B", "R", "A"};
        quickSort(arr);
    }
}

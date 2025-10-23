import java.util.Arrays;

public class Q3 {

    
    public static int[] getRankings(int[] scores) {
        int n = scores.length;
        Employee[] employees = new Employee[n];

        
        for (int i = 0; i < n; i++) {
            employees[i] = new Employee(i, scores[i]);
        }

        
        mergeSort(employees, 0, n - 1);

       
        int[] rankings = new int[n];
        for (int i = 0; i < n; i++) {
            rankings[employees[i].id] = i;
        }

        return rankings;
    }

    // MergeSort algorithm to sort Employee array by scores 
    private static void mergeSort(Employee[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    // Merge function to merge two sorted halves
    private static void merge(Employee[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Employee[] leftArr = new Employee[n1];
        Employee[] rightArr = new Employee[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            // Sort in ascending order (worst score first)
            if (leftArr[i].score <= rightArr[j].score) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

       
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

   
    static class Employee {
        int id;
        int score;

        Employee(int id, int score) {
            this.id = id;
            this.score = score;
        }
    }

 
    public static void main(String[] args) {
        int[] scores = {9, 10, 5};

       
        int[] rankings = getRankings(scores);

        
        
        for (int i = 0; i < scores.length; i++) {
            System.out.print("Scores[" + i + "] = " + scores[i] + "    |    ");
        }
        System.out.println();

        for (int i = 0; i < rankings.length; i++) {
            System.out.print("Rankings[" + i + "] = " + rankings[i] + "    |    ");
        }
        System.out.println();
    }
}


            

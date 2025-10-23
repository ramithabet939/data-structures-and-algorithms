import java.util.Arrays;
import java.util.Scanner;

class Time implements Comparable<Time> {
    int hour, minute, second;

    // Constructor
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // Compare method for sorting
    @Override
    public int compareTo(Time other) {
        if (this.hour != other.hour)
            return this.hour - other.hour;
        if (this.minute != other.minute)
            return this.minute - other.minute;
        return this.second - other.second;
    }

    
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Time other = (Time) obj;
        return this.hour == other.hour &&
               this.minute == other.minute &&
               this.second == other.second;
    }
}

public class Q5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the size of the array
        System.out.print("Enter the number of time objects: ");
        int n = scanner.nextInt();

        // Create array of Time objects
        Time[] times = new Time[n];

        
        System.out.println("Enter times in format HH MM SS:");
        for (int i = 0; i < n; i++) {
            int h = scanner.nextInt();
            int m = scanner.nextInt();
            int s = scanner.nextInt();
            times[i] = new Time(h, m, s);
        }

   
        Arrays.sort(times);

        
        System.out.println("\nSorted Times:");
        for (Time t : times) {
            System.out.println(t);
        }

        // Check if all times are distinct
        boolean allDistinct = true;
        for (int i = 1; i < n; i++) {
            if (times[i].equals(times[i - 1])) {
                allDistinct = false;
                break;
            }
        }

      
        if (allDistinct) {
            System.out.println("\n There are no duplicate itmes.");
        } else {
            System.out.println("\nThere are duplicate times.");
        }

        scanner.close();
    }
}



            

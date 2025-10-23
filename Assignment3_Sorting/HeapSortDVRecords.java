
import java.io.*;
import java.util.*;

public class Q4 {
    public static void main(String[] args) {
        File sourceFile = new File("DandV.txt");
        File sortedFile = new File("SortedDandV.txt");

        try {
            if (sourceFile.createNewFile()) {
                System.out.println("New file created: " + sourceFile.getName());
            } else {
                System.out.println("Existing file found.");
            }
        } catch (IOException e) {
            System.out.println("Error generating the file.");
            e.printStackTrace();
        }

        DVRecord[] records = {
            new DVRecord(15, "Feb", 21, 4200000),
            new DVRecord(12, "Mar", 22, 580680000),
            new DVRecord(7, "Apr", 20, 3950000),
            new DVRecord(22, "May", 21, 4450000),
            new DVRecord(9, "Jun", 19, 1019000000),
            new DVRecord(30, "Jul", 20, 4370000),
            new DVRecord(5, "Aug", 22, 4120000),
            new DVRecord(18, "Sep", 21, 370049984),
            new DVRecord(2, "Oct", 21, 931800000),
            new DVRecord(27, "Nov", 20, 1085500032)
        };

        try (FileWriter writer = new FileWriter(sourceFile)) {
            for (DVRecord rec : records) {
                writer.write(rec + "\n");
            }
            System.out.println("Original records saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing unsorted records.");
            e.printStackTrace();
        }
        
        try (Scanner scanner = new Scanner(sourceFile)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading source file.");
            e.printStackTrace();
        }
        System.out.println();

        BinaryHeapSort.heapSort(records);

        try (FileWriter writer = new FileWriter(sortedFile)) {
            for (DVRecord rec : records) {
                writer.write(rec + "\n");
            }
            System.out.println("Sorted records saved to " + sortedFile.getName());
        } catch (IOException e) {
            System.out.println("Error writing sorted records.");
            e.printStackTrace();
        }
        
        System.out.println("\nContents of Sorted File:");
        try (Scanner scanner = new Scanner(sortedFile)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading sorted file.");
            e.printStackTrace();
        }
    }
}

class BinaryHeapSort {
    public static void heapSort(Comparable[] array) {
        int len = array.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            percolateDown(array, i, len);
        }
        while (len > 1) {
            exchange(array, 0, --len);
            percolateDown(array, 0, len);
        }
    }

    private static void percolateDown(Comparable[] array, int pos, int len) {
        while (2 * pos + 1 < len) {
            int child = 2 * pos + 1;
            if (child + 1 < len && isLess(array, child, child + 1))
                child++;
            if (!isLess(array, pos, child)) break;
            exchange(array, pos, child);
            pos = child;
        }
    }

    private static boolean isLess(Comparable[] array, int i, int j) {
        return array[i].compareTo(array[j]) < 0;
    }

    private static void exchange(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

class DVRecord implements Comparable<DVRecord> {
    private int day;
    private String month;
    private int year;
    private int volume;

    public DVRecord(int day, String month, int year, int volume) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.volume = volume;
    }

    public String formatDate() {
        return day + "-" + month + "-" + String.format("%02d", year);
    }

    @Override
    public int compareTo(DVRecord other) {
        return Integer.compare(this.volume, other.volume);
    }

    @Override
    public String toString() {
        return String.format("%-12s %d", formatDate(), volume);
    }
}

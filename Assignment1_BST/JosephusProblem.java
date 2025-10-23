

import java.util.*;

public class Josephus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter N:  ");
        int N = scanner.nextInt();

        System.out.print("Enter M:  ");
        int M = scanner.nextInt();

        scanner.close();

        Queue<Integer> people = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            people.add(i);  
        }

        System.out.print("Order of Elimination : ");
        
        while (people.size() > 1) {
            for (int i = 1; i < M; i++) {
                people.add(people.poll());  
            }
            System.out.print(people.poll() + " ");  
        }

        System.out.println("\nJosephus is at postion : " + people.peek()); 
}
}

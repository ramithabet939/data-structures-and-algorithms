import java.util.Random;

class ListNode {
    int val;
    ListNode next;
    
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class Q4 {
    private static final Random rand = new Random();

    // Function to shuffle a linked list
    public static ListNode shuffle(ListNode head) {
        if (head == null || head.next == null) return head; 

        // Split the list into two halves
        ListNode mid = getMiddle(head);
        ListNode secondHalf = mid.next;
        mid.next = null; // Break the list

        // Recursively shuffle both halves
        ListNode left = shuffle(head);
        ListNode right = shuffle(secondHalf);

        // Merge both halves randomly
        return mergeRandom(left, right);
    }

    // Function to find the middle of the linked list 
    private static ListNode getMiddle(ListNode head) {
        if (head == null) return head;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Merge two lists randomly
    private static ListNode mergeRandom(ListNode a, ListNode b) {
        if (a == null) return b;
        if (b == null) return a;

        // Randomly pick a node from a or b
        if (rand.nextBoolean()) { 
            a.next = mergeRandom(a.next, b);
            return a;
        } else { 
            b.next = mergeRandom(a, b.next);
            return b;
        }
    }

    // Helper function to print the linked list
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original List:");
        printList(head);

        // Shuffle the linked list
        head = shuffle(head);

        System.out.println("Shuffled List:");
        printList(head);
    }
}


            

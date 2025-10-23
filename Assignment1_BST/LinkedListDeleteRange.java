
import java.util.*;
import java.io.*;

public class Q2 {
    Node head;

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void insert(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void printList() {
        Node current = head;
        System.out.print("LinkedList: ");

        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void DeleteRange(int start, int end) {
        int length = getLength();
        if (start < 0 || start >= length) {
            System.out.println("Start index out of bounds");
            return;
        } else if (end < 0 || end >= length) {
            System.out.println("End index out of bounds");
            return;
        } else if (end < start) {
            System.out.println("Invalid range: end is before start");
            return;
        } else if (head == null) {
            return;
        }

        Node current = head;
        Node previous = null;

        for (int i = 0; i < start && current != null; i++) {
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            for (int i = start; i <= end && current != null; i++) {
                current = current.next;
            }
            head = current;
        } else {
            for (int i = start; i <= end && current != null; i++) {
                current = current.next;
            }
            previous.next = current;
        }
    }

    public int getLength() {
        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public static void main(String[] args) {
        Q2 list = new Q2();

        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(6);

        System.out.println("Original List Length: " + list.getLength());
        list.printList();

        list.DeleteRange(1, 3);
        list.printList();
    }
}

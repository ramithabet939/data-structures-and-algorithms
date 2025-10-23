

public class Q3 {
    
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
            Node last = head;  
            while (last.next != null) {  
                last = last.next;  
            }
            last.next = newNode;  
        }  
    }

    public void printList() {  
        Node currNode = head;  
        System.out.print("LinkedList: ");  

        while (currNode != null) {  
            System.out.print(currNode.data + " ");  
            currNode = currNode.next;  
        }  
        System.out.println();
    }
    
    public int max() {
        if (head == null) {
            return 0;
        }

        Node current = head;
        int maxValue = current.data;

        while (current != null) {
            if (current.data > maxValue) {
                maxValue = current.data;
            }
            current = current.next;
        }

        return maxValue;
    }

    public static void main(String[] args) {
        Q3 list = new Q3();
        list.insert(70);  
        list.insert(700);  
        list.insert(200); 
        
        System.out.println("Maximum value: " + list.max());  
    }
}

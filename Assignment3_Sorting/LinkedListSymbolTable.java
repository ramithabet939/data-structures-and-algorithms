
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Q6<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private Node head;
    private int total;

    private class Node {
        Key id;
        Value data;
        Node next;

        Node(Key id, Value data, Node next) {
            this.id = id;
            this.data = data;
            this.next = next;
        }
    }

    public Q6() {
        head = null;
        total = 0;
    }

    public int size() {
        return total;
    }

    public boolean isEmpty() {
        return total == 0;
    }

    public Value search(Key id) {
        Node current = head;
        while (current != null) {
            int cmp = id.compareTo(current.id);
            if (cmp == 0) return current.data;
            if (cmp < 0) break;
            current = current.next;
        }
        return null;
    }

    public void add(Key id, Value data) {
        if (head == null || id.compareTo(head.id) < 0) {
            head = new Node(id, data, head);
            total++;
            return;
        }
        Node prev = null;
        Node current = head;
        while (current != null && id.compareTo(current.id) > 0) {
            prev = current;
            current = current.next;
        }
        if (current != null && id.compareTo(current.id) == 0) {
            current.data = data;
        } else {
            prev.next = new Node(id, data, current);
            total++;
        }
    }

    public void remove(Key id) {
        if (head == null) return;
        if (id.compareTo(head.id) == 0) {
            head = head.next;
            total--;
            return;
        }
        Node prev = head;
        Node current = head.next;
        while (current != null) {
            if (id.compareTo(current.id) == 0) {
                prev.next = current.next;
                total--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public boolean contains(Key id) {
        return search(id) != null;
    }

    public Iterable<Key> keys() {
        return this;
    }

    @Override
    public Iterator<Key> iterator() {
        return new Iterator<Key>() {
            private Node current = head;
            public boolean hasNext() {
                return current != null;
            }
            public Key next() {
                if (!hasNext()) throw new NoSuchElementException();
                Key temp = current.id;
                current = current.next;
                return temp;
            }
        };
    }

    public static void main(String[] args) {
        Q6<String, Integer> st = new Q6<>();
        st.add("A", 1);
        st.add("B", 2);
        st.add("C", 3);

        System.out.println("Value for B: " + st.search("B"));
        System.out.println("Contains A? " + st.contains("A"));
        st.remove("A");
        System.out.println("Contains A after removal? " + st.contains("A"));
        
        System.out.println("Keys in the symbol table:");
        for (String key : st.keys()) {
            System.out.println(key);
        }
    }
}


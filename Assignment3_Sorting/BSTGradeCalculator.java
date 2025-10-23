import java.util.Scanner;

public class Q5 {

    public static void main(String[] args) {
        BST<String, Double> st = new BST<>();
        st.put("A+", 4.33);
        st.put("A", 4.00);
        st.put("A-", 3.67);
        st.put("B+", 3.33);
        st.put("B", 3.00);
        st.put("B-", 2.67);
        st.put("C+", 2.33);
        st.put("C", 2.00);
        st.put("C-", 1.67);
        st.put("D", 1.00);
        st.put("F", 0.00);

        Scanner scanner = new Scanner(System.in);
        double total = 0.0;
        int count = 0;
        
        System.out.println("Enter letter grades: ");
       
        
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            String[] tokens = line.split("\\s+");
            for (String token : tokens) {
                Double score = st.get(token);
                if (score != null) {
                    total += score;
                    count++;
                } else {
                    System.out.println("Invalid grade: " + token);
                }
            }
        }
        scanner.close();

        if (count > 0) {
            double gpa = total / count;
            System.out.println("Your GPA is: " + gpa);
        } else {
            System.out.println("No valid grades entered.");
        }
    }

    // Simple BST-based symbol table
    private static class BST<Key extends Comparable<Key>, Value> {
        private Node root;
        
        private class Node {
            Key key;
            Value val;
            Node left, right;
            int size;
            Node(Key key, Value val, int size) {
                this.key = key;
                this.val = val;
                this.size = size;
            }
        }
        
        public int size() {
            return size(root);
        }
        
        private int size(Node x) {
            return x == null ? 0 : x.size;
        }
        
        public Value get(Key key) {
            return get(root, key);
        }
        
        private Value get(Node x, Key key) {
            if (x == null) return null;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) return get(x.left, key);
            else if (cmp > 0) return get(x.right, key);
            else return x.val;
        }
        
        public void put(Key key, Value val) {
            root = put(root, key, val);
        }
        
        private Node put(Node x, Key key, Value val) {
            if (x == null) return new Node(key, val, 1);
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x.left = put(x.left, key, val);
            else if (cmp > 0) x.right = put(x.right, key, val);
            else x.val = val;
            x.size = 1 + size(x.left) + size(x.right);
            return x;
        }
    }
}

public class Q3 {
    
    public static class DualHeapPQ<Key extends Comparable<Key>> {
        private Key[] lowHeap;
        private Key[] highHeap;
        private int count;
        
        public DualHeapPQ(int capacity) {
            lowHeap = (Key[]) new Comparable[capacity + 1];
            highHeap = (Key[]) new Comparable[capacity + 1];
            count = 0;
        }
        
        public boolean isEmpty() {
            return count == 0;
        }
        
        public void add(Key element) {
            lowHeap[++count] = element;
            highHeap[count] = element;
            bubbleUpLow(count);
            bubbleUpHigh(count);
        }
        
        public Key min() {
            return lowHeap[1];
        }
        
        public Key max() {
            return highHeap[1];
        }
        
        public Key removeMin() {
            Key minVal = lowHeap[1];
            swap(lowHeap, 1, count--);
            bubbleDownLow(1);
            return minVal;
        }
        
        public Key removeMax() {
            Key maxVal = highHeap[1];
            swap(highHeap, 1, count--);
            bubbleDownHigh(1);
            return maxVal;
        }
        
        private void bubbleUpLow(int pos) {
            while (pos > 1 && isGreater(pos / 2, pos, lowHeap)) {
                swap(lowHeap, pos, pos / 2);
                pos = pos / 2;
            }
        }
        
        private void bubbleUpHigh(int pos) {
            while (pos > 1 && isLess(pos / 2, pos, highHeap)) {
                swap(highHeap, pos, pos / 2);
                pos = pos / 2;
            }
        }
        
        private void bubbleDownLow(int pos) {
            while (2 * pos <= count) {
                int child = 2 * pos;
                if (child < count && isGreater(child, child + 1, lowHeap)) child++;
                if (!isGreater(pos, child, lowHeap)) break;
                swap(lowHeap, pos, child);
                pos = child;
            }
        }
        
        private void bubbleDownHigh(int pos) {
            while (2 * pos <= count) {
                int child = 2 * pos;
                if (child < count && isLess(child, child + 1, highHeap)) child++;
                if (!isLess(pos, child, highHeap)) break;
                swap(highHeap, pos, child);
                pos = child;
            }
        }
        
        private boolean isGreater(int i, int j, Key[] heap) {
            return heap[i].compareTo(heap[j]) > 0;
        }
        
        private boolean isLess(int i, int j, Key[] heap) {
            return heap[i].compareTo(heap[j]) < 0;
        }
        
        private void swap(Key[] heap, int i, int j) {
            Key temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }
    
    public static void main(String[] args) {
        DualHeapPQ<Integer> pq = new DualHeapPQ<>(10);
        pq.add(10);
        pq.add(20);
        pq.add(5);
        pq.add(7);
        pq.add(2);
        pq.add(9);
        pq.add(3);
        
        System.out.println("Minimum: " + pq.min());     
        System.out.println("Maximum: " + pq.max());      
        
        pq.removeMin();
        System.out.println("Minimum after removal: " + pq.min());  
        
        pq.removeMax();
        System.out.println("Maximum after removal: " + pq.max());  
    }
}


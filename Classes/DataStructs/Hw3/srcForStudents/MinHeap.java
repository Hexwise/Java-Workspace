import java.util.Arrays;

public class MinHeap<T extends Comparable<? super T>> {

    private T[] heap;
    private int capacity;
    private int size = 0;
    
    public MinHeap(T[] heap) {  
        this.heap = heap;
        capacity = heap.length;
    } 

    public void insert(T element) {
        ensureExtraCapacity();
        heap[size] = element;
        size++;
        heapifyUp();
    }

    public T remove() {
        if (isEmpty())
            throw new IllegalArgumentException("Heap is empty, cannot remove a node.");
        T min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return min;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public boolean isFull() {
        return size >= capacity;
    }

    public String toString() {
        String s = new String();
        for (T element: heap) {
            s.concat(element + " ");
        }
        return s.trim();
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >=0 ;
    }

    private T leftChild(int index) {
        return heap[getLeftChildIndex(index)];
    }

    private T rightChild(int index) {
        return heap[getRightChildIndex(index)];
    }

    private T parent(int index) {
        return heap[getParentIndex(index)];
    }

    private void swap(int firstIndex, int secondIndex) {
        T temp = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = temp;
    }

    private void ensureExtraCapacity() {
        if (isFull()) {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
    }

    private void heapifyUp() {
        int index =  size - 1;
        while (hasParent(index) && parent(index).compareTo(heap[index]) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int minChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) < 0)
                minChildIndex = getRightChildIndex(index);
            if(heap[index].compareTo(heap[minChildIndex]) < 0)
                break;
            else
                swap(index, minChildIndex);
            index = minChildIndex;
        }
    }

}
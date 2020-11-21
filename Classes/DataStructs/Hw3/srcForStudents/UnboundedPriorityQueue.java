public class UnboundedPriorityQueue<T extends Comparable<? super T>>  {

    MinHeap<T> heap;
    @SuppressWarnings("unchecked")
    public UnboundedPriorityQueue() {
        Comparable<T>[] array = new Comparable[5];
        heap = new MinHeap<T>((T[]) array);
    }

    public void enqueue(T element) {
        heap.insert(element);
    }

    public T dequeue() {
        return heap.remove();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
package Hw1;

import java.util.*;

public class SinglyLinkedList<T> implements Iterable<T> {

    // generic inner node class
    private class Node<T> {
        private T data;
        private Node<T> next;
        public Node(T data) {
            this.data = data;
        }
        public T getData() { 
            return this.data;
        }
        public void setNext(Node<T> next) {
            this.next = next;
        }
        public Node<T> getNext() {
            return this.next;
        }
    }

    // generic inner iterator class
    private class SinglyLinkedListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public SinglyLinkedListIterator(Node<T> head) {
            this.current = head;
        }
    
        @Override
        public boolean hasNext() {
            return current != null;
        }
    
        @Override
        public T next() {
            if(this.current.next == null) {
                return this.current.getData();
            }
            Node<T> current = this.current;
            this.current = this.current.getNext();
            return current.getData();            
        }
        
        public void setNext(Node<T> next) {
            this.current.setNext(next);
        }

        public Node<T> getCurrent() {
            return this.current;
        }

        public Node<T> getNext() {
            return this.current.getNext();
        }

        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Remove operation is not supported by this iterator.");
        }
    }

    // head node
    private Node<T> head;

    // number of elements in the list
    private int size;

    // Constructor
    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // return an iterator of the list.
    public SinglyLinkedListIterator<T> iterator() {
        return new SinglyLinkedListIterator<T>(this.head);
    }

    // add the given element to the end of the list.
    public void add(T element) {
        if(head == null)
            head = new Node<T>(element);
        SinglyLinkedListIterator<T> i = iterator();
        while(i.hasNext()) {
            i.next();
        }
        i.setNext(new Node<T>(element));
    }

    // inserts a given element at a given index of the list. Counting starts with zero from the first.
    public void insertAt(T element, int index) throws IndexOutOfBoundsException{
        if(index >= size) 
            throw new IndexOutOfBoundsException("Index out of bounds.");
        SinglyLinkedListIterator<T> i = iterator();
        for(int j = 0; j < index-1; j++) {
            i.next();
        }
        Node<T> previousNext = i.getNext();
        i.setNext(new Node<T>(element));
        i.next();
        i.setNext(previousNext);
    }

    // remove the first occurrence of the given element from the list.
    public void remove(T element) throws NoSuchElementException {
        SinglyLinkedListIterator<T> i = iterator();
        while(i.hasNext()) {
            if(i.getCurrent().getData().equals(element)) {
                i.getCurrent().setNext(i.getCurrent().getNext().getNext());
                return;
            }
        }
        throw new NoSuchElementException("No such element.");
    }

    // remove all elements from the list.
    public void clear() {
        this.head = null;
    }

    // return true if the list is empty, false otherwise.
    public boolean isEmpty() {
        return this.head == null;
    }

    // return the number of elements currently in the list.
    public int size() {
        return size;
    }

    // return the nth value from the first (count starts with zero).
    public T getNthFromFirst(int n) throws IndexOutOfBoundsException {
        if(n >= size-1)
            throw new IndexOutOfBoundsException("Index out of bounds.");
        SinglyLinkedListIterator<T> i = iterator();
        int count = n;
        while(i.hasNext() && count > 1) {
            i.next();
            count--;
        }
        return i.next();
    }

    // return the nth value from the last (count starts with zero).
    public T getNthFromLast(int n) throws IndexOutOfBoundsException {
        if(n >= size-1)
            throw new IndexOutOfBoundsException("Index out of bounds.");
        SinglyLinkedListIterator<T> i = iterator();
        int count = size - n - 1;
        while(i.hasNext() && count > 1) {
            i.next();
            count--;
        }
        return i.next();
    }

    // return a String representing the content of the list.
    public String toString() {
        SinglyLinkedListIterator<T> i = iterator();
        String s = "";
        while(i.hasNext()) {
            s += i.next();
        }
        return s;
    }

}
package Hw1;

public class Startup {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        for(int i = 0; i < 20; i++) {
            list.add(new Integer(i));
        }
        System.out.println(list);
    }
}
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class BigDecimalDescendingOrder {

    private class BinarySearchTree {
        private class Node {
            private class Duplicate {

                private final BigDecimal key;
                private final boolean hasLeadingDecimal;

                public Duplicate(Node node){
                    this.key = node.getKey();
                    this.hasLeadingDecimal = node.hasLeadingDecimal();
                }

                public boolean hasLeadingDecimal() {
                    return hasLeadingDecimal;
                }

                public String toString() {
                    if(hasLeadingDecimal())
                        return key.toString().substring(1);
                    return key.toString();
                }

            }

            private final BigDecimal key;
            private final boolean hasLeadingDecimal;
            private ArrayList<Duplicate> duplicates;
            private Node left;
            private Node right;
            
            public Node(BigDecimal key, boolean hasLeadingDecimal){
                this.key = key;
                this.hasLeadingDecimal = hasLeadingDecimal;
                duplicates = new ArrayList<Duplicate>();
            }

            public BigDecimal getKey() {
                return key;
            }

            public boolean hasLeadingDecimal() {
                return hasLeadingDecimal;
            }

            public void setLeft(Node left) {
                this.left = left;
            }

            public void setRight(Node right) {
                this.right = right;
            }

            public Node getLeft() {
                return left;
            }

            public Node getRight() {
                return right;
            }

            public int getDuplicateQuantity() {
                return duplicates.size();
            }

            public void addDuplicate(Node node) {
                duplicates.add(new Duplicate(node));
            }

            public Duplicate popNextDuplicate() {
                if(duplicates.isEmpty()) {
                    return null;
                }
                Duplicate d = duplicates.get(0);
                duplicates.remove(0);
                return d;
            }

            public String toString() {
                if(hasLeadingDecimal())
                    return key.toString().substring(1);
                return key.toString();
            }
        }

        private Node root;

        public BinarySearchTree(){};

        public void add(BigDecimal key, boolean hasLeadingDecimal) {
            if(root == null)
                root = new Node(key, hasLeadingDecimal);
            else
                add(new Node(key, hasLeadingDecimal), root);
        }

        private void add(Node node, Node root) {
            int comparison = node.getKey().compareTo(root.getKey());
            if(comparison == 0)
                root.addDuplicate(node);
            else if(comparison < 0) {
                if(root.getLeft() == null)
                    root.setLeft(node);
                else
                    add(node, root.getLeft());
            }
            else {
                if(root.getRight() == null)
                    root.setRight(node);
                else
                    add(node, root.getRight());
            }
        }

        public void reverseInOrderTraverse() {
            if(root != null)
                reverseInOrderTraverse(root);
        }

        private void reverseInOrderTraverse(Node root) {
            if(root.getRight() != null)
                reverseInOrderTraverse(root.getRight());

            System.out.println(root);
            for(int i=0; i<root.getDuplicateQuantity(); i++)
                System.out.println(root.popNextDuplicate());

            if(root.getLeft() != null)
                reverseInOrderTraverse(root.getLeft());
        }
    }

    public BinarySearchTree getBST(){
        return new BinarySearchTree();
    }

    public static boolean hasLeadingDecimal(String number) {
        if(number.charAt(0) == '.')
            return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = Integer.parseInt(scanner.nextLine());
        BigDecimalDescendingOrder b = new BigDecimalDescendingOrder();
        BinarySearchTree bst =  b.getBST();
        while(cases-- > 0) {
            String line = scanner.nextLine();
            bst.add(new BigDecimal(line), hasLeadingDecimal(line));
        }
        scanner.close();
        bst.reverseInOrderTraverse();
    }
    
}

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> {

    /**
     * Default constructor
     */
    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(T[] seq) {
        initFromBfsSequence(seq, null);
    }

    public BinarySearchTree(T[] seq, T nullSymbol) {
        initFromBfsSequence(seq, nullSymbol);
    }

	private void initFromBfsSequence(T[] seq, T nullSymbol){
		if(seq.length == 0)
			throw new IllegalArgumentException("Cannot build tree from empty sequence");
		
		if(seq[0].equals(nullSymbol))
			throw new IllegalArgumentException("Symbol for root cannot be nullSymbol");
        
        root = new BinaryNode<T>(seq[0]);

        for(int i=1; i<seq.length; i++) {
            insert(seq[i]);
        }
	}


    // Add the given element to the binary search tree (does nothing for duplicate entries)
    public void insert(T value) {
        insert(root, value);
    }

    // Helper method for insert() to allow recursion
    private void insert(BinaryNode<T> node, T value) {
        if(node == null || value == null)
            return;
        int comparison = node.getData().compareTo(value);
        if(comparison == 0) {
            return;
        }
        else if(comparison > 0) {
            if(node.getLeftNode() == null) {
                node.setLeftNode(new BinaryNode<T>(value));
                return;
            }
            insert(node.getLeftNode(), value);
        }
        else {
            if(node.getRightNode() == null) {
                node.setRightNode(new BinaryNode<T>(value));
                return;
            }
            insert(node.getRightNode(), value);
        }
    }

    // Remove the given value from the binary search tree
    public void remove(T value) {
        remove(root, value);
    }

    // Helper method for remove() to allow recursion
    private void remove(BinaryNode<T> node, T value) {
        if(node != null) {
            int comparison = node.getData().compareTo(value);
            if(comparison == 0) {
                node.setData(findMin(node.getRightNode()));
            }
            else if(comparison > 0) {
                remove(node.getLeftNode(), value);
            }
            else {
                remove(node.getRightNode(), value);
            }
        }
    }

    // Finds and returns the minimum element in a tree
    private T findMin(BinaryNode<T> node) {
        if(node.getLeftNode().getLeftNode() == null) {
            if(node.getLeftNode().getRightNode() == null) {
                T min = node.getLeftNode().getData();
                node.setLeftNode(null);
                return min;
            }
            T min = node.getLeftNode().getData();
            node.setLeftNode(node.getLeftNode().getRightNode());
            return min;
        }
        return findMin(node.getLeftNode());
    }

    // Returns true if the given value is found in the binary search tree, and false otherwise
    public boolean contains(T value) {
        return contains(root, value);
    }

    // Helper method for contains() to allow recursion
    private boolean contains(BinaryNode<T> node, T value) {
        if(node == null)
            return false;
        if(node.getData().equals(value))
            return true;
        return contains(node.getLeftNode(), value) || contains(node.getRightNode(), value);
    }
}

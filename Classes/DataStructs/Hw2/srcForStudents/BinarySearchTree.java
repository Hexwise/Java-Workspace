public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> {

    /**
     * Default constructor
     */
    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(T[] seq) {
        this.initFromBfsSequence(seq, null);
    }

    public BinarySearchTree(T[] seq, T nullSymbol) {
        this.initFromBfsSequence(seq, nullSymbol);
    }

	private void initFromBfsSequence(T[] seq, T nullSymbol){
		if(seq.length == 0)
			throw new IllegalArgumentException("Cannot build tree from empty sequence");
		
		if(seq[0].equals(nullSymbol))
			throw new IllegalArgumentException("Symbol for root cannot be nullSymbol");
        
        root = new BinaryNode<T>(seq[0]);

        for(int i=1; i<seq.length; i++) {
            if(seq[i] != nullSymbol)
                insert(seq[i]);
        }
	}


    // Add the given element to the binary search tree (does nothing for duplicate entries)
    public void insert(T value) {
        if(value == null)
            return;
        else if(root == null)
            root = new BinaryNode<T>(value);
        else
            insert(root, value);
    }

    // Helper method for insert() to allow recursion
    private void insert(BinaryNode<T> node, T value) {
        if(node == null)
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
        if(root.isLeaf()) {
            if(root.getData().equals(value)) {
                root = null;
            }
            return;
        }
        remove(root, value);
    }

    // Helper method for remove() to allow recursion
    private void remove(BinaryNode<T> node, T value) {
		if(node != null) {
           int comparison = node.getData().compareTo(value);
            if(comparison == 0) {
                if(node.getRightNode() != null)
                    if(node.getRightNode().isLeaf()) {
                        node.setData(node.getRightNode().getData());
                        node.setRightNode(null);
                    }
                    else
                        node.setData(findMin(node.getRightNode()));
                else {
                    if(node.getLeftNode().isLeaf()) {
                        node.setData(node.getLeftNode().getData());
                        node.setLeftNode(null);
                    }
                    else
                        node.setData(findMax(node.getLeftNode()));
                }
            }
            else if(comparison > 0) {
                if(node.getLeftNode().isLeaf() && node.getLeftNode().getData().equals(value)) {
                    node.setLeftNode(null);
                    return;
                }
                remove(node.getLeftNode(), value);
            }
            else {
                if(node.getRightNode().isLeaf() && node.getRightNode().getData().equals(value)) {
                    node.setRightNode(null);
                    return;
                }
                remove(node.getRightNode(), value);
            }
        }
    }

    // Finds and returns the minimum element in a tree
    private T findMin(BinaryNode<T> node) {
        if(node.getLeftNode() == null)
            return node.getData();

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

    // Finds and returns the maximum element in a tree
    private T findMax(BinaryNode<T> node) {
        if(node.getRightNode() == null)
            return node.getData();

        if(node.getRightNode().getRightNode() == null) {
            if(node.getRightNode().getLeftNode() == null) {
                T max = node.getRightNode().getData();
                node.setRightNode(null);
                return max;
            }
            T max = node.getRightNode().getData();
            node.setRightNode(node.getRightNode().getLeftNode());
            return max;
        }
        return findMax(node.getRightNode());
    }

    // Returns true if the given value is found in the binary search tree, and false otherwise
    public boolean contains(T value) {
        return contains(root, value);
    }

    // Helper method for contains() to allow recursion
    private boolean contains(BinaryNode<T> node, T value) {
        if(node == null)
            return false;
        int comparison = node.getData().compareTo(value);
        if(comparison == 0)
            return true;
        if(comparison > 0)
            return contains(node.getLeftNode(), value);
        return contains(node.getRightNode(), value);
    }
}

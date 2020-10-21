import java.util.List;
import java.util.Queue;
import java.util.ArrayList;

public class BinaryTree<T>{
	BinaryNode<T> root = null;	
	
	private T nullSymbol = null;

	/**
	 * Default constructor
	 */
	public BinaryTree(){

	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence.
	 *  @param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 */
	public BinaryTree(T[] seq){
		initFromBfsSequence(seq);
	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence. 
	 *	A given special symbol (nullSymbol) in the sequence is interpreted as absence of node. 
	 *	During construction of the tree, when such a special symbol is encountered, 
	 *	that is considered to be an absent node, and thus no corresponding node is added to the tree.
	 * 	
	 * 	@param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 * 	@param nullSymbol is a special symbol in the given sequence that denotes absence of a node.
	 */
	public BinaryTree(T[] seq, T nullSymbol){
		this.nullSymbol = nullSymbol;
		initFromBfsSequence(seq);
	}

	private void initFromBfsSequence(T[] seq){
		if(seq.length == 0)
			throw new IllegalArgumentException("Cannot build tree from empty sequence");
		
		if(seq[0].equals(nullSymbol))
			throw new IllegalArgumentException("Symbol for root cannot be nullSymbol");
				
		List<BinaryNode<T>> nodes = new ArrayList<BinaryNode<T>>(seq.length);
		this.root = new BinaryNode<T>(seq[0]);
		nodes.add(root);

		for(int i = 0; i < seq.length; i++){			
			if(nodes.get(i) == null){ 				
				handelNullParentNode(nodes, i, seq.length);				
			}else{				
				handleNonNullParentNode(nodes, i, seq);				
			}
		}		
	}

	// This method will handle the null nodes in the iteration of nodes.get(i) in initFromBfsSequence method.
	private void handelNullParentNode(List<BinaryNode<T>> nodes, 
						int nullNodeIndex, int seqLength){
		int leftIndex = (nullNodeIndex * 2) + 1; // finding the left child index from formula 
				
		if(leftIndex < seqLength){
			nodes.add(null);

			int rightIndex = (nullNodeIndex * 2) + 2; // finding the right child index from formula
			if(rightIndex < seqLength){
				nodes.add(null);
			}
		}
	}

	// This method will handle the non-null nodes in the iteration of nodes.get(i) in initFromBfsSequence method.
	private void handleNonNullParentNode(List<BinaryNode<T>> nodes, 
								int parentIndex, T[] seq){
		int leftIndex = (parentIndex * 2) + 1;			
		if(leftIndex < seq.length){ //need to check if the index falls outdise of the list index
			BinaryNode<T> leftNode = null;
			if(!seq[leftIndex].equals(nullSymbol)){
				leftNode = new BinaryNode<T>(seq[leftIndex]);
			}
			nodes.get(parentIndex).leftNode = leftNode;
			nodes.add(leftNode);

			int rightIndex = (parentIndex * 2) + 2;				
			if(rightIndex < seq.length){
				BinaryNode<T> rightNode = null;
				if(!seq[rightIndex].equals(nullSymbol)){
					rightNode = new BinaryNode<T>(seq[rightIndex]);
				}
				nodes.get(parentIndex).rightNode = rightNode;
				nodes.add(rightNode);			
			}
		}
	}

	public int height(){
		if (root == null) return 0;	
		return root.height();
	}

	public int width(){
		Queue queue = new Queue();
		int nodes = 1;
		int maxWidth = 0;
		int i;
		int j = 2;
		BinaryTree<T>.BinaryNode<T> newRoot = root;
		while(newRoot != null){
			if(newRoot.getLeftNode() != null) {
				queue.enqueue(newRoot.getLeftNode());
				nodes++;
			}
			if(newRoot.getRightNode() != null) {
				queue.enqueue(newRoot.getRightNode());
				nodes++;
			}
			newRoot = queue.dequeue();
		}
		if(3 > nodes && nodes > 0)
			maxWidth = 1;
		for(i=3; i<=nodes; i+=j) {
			maxWidth = j;
			j*=2;
			if(nodes-i > maxWidth)
				maxWidth = nodes - i;
		}
		return maxWidth;
	}

	public String breadthFirstTraverse(){
			StringBuilder stringBuffer = new StringBuilder();			
			Queue queue = new Queue();
			BinaryTree<T>.BinaryNode<T> newRoot = root;
			while(newRoot != null){
				stringBuffer.append(newRoot.toString() + " ");
				if(newRoot.getLeftNode() != null)
					queue.enqueue(newRoot.getLeftNode());
				if(newRoot.getRightNode() != null)
					queue.enqueue(newRoot.getRightNode());
				newRoot = queue.dequeue();
			}
			return stringBuffer.toString().trim();				
	}

	public String preOrderTraverse(){
		return root.preOrderTraverse().trim();				
	}

	public String postOrderTraverse(){
		return root.postOrderTraverse().trim();
	}

	public String inOrderTraverse(){
		return root.inOrderTraverse().trim();
	}
	
	class BinaryNode<T>{
		private T data = null;
		private BinaryNode<T> leftNode = null;
		private BinaryNode<T> rightNode = null;

		public BinaryNode(T data){
			this.data = data;			
		}

		public String toString(){
			return "" + data;
		}

		public BinaryNode<T> getLeftNode(){
			return this.leftNode;
		}

		public BinaryNode<T> getRightNode(){
			return this.rightNode;
		}

		public void setLeftNode(BinaryNode<T> node){
			this.leftNode = node;
		}

		public void setRightNode(BinaryNode<T> node){
			this.rightNode = node;
		}

		public T getData(){
			return this.data;
		}

		public void setData(T data){
			this.data = data;
		}

		public int height(){
			if(isLeaf()) return 0;
			
			int leftHeight = 0;
			int rightHeight = 0;

			if(leftNode != null){ 
				leftHeight = leftNode.height();
			}

			if(rightNode != null){
				rightHeight = rightNode.height();
			}
			
			int maxHeight = leftHeight > rightHeight? leftHeight: rightHeight;

			return maxHeight + 1 ;
		}

		public boolean isLeaf(){
			return (leftNode == null && rightNode == null);
		}


		public String preOrderTraverse(){
			StringBuilder stringBuffer = new StringBuilder();
			
			stringBuffer.append(" " + data);
			
			if(leftNode != null){
				stringBuffer.append(leftNode.preOrderTraverse());				
			}
			
			if(rightNode != null){
				stringBuffer.append(rightNode.preOrderTraverse());
			}

			return stringBuffer.toString();				
		}

		public String postOrderTraverse(){			
			StringBuilder stringBuffer = new StringBuilder();			
			
			if(leftNode != null){
				stringBuffer.append(leftNode.postOrderTraverse());				
			}
			
			if(rightNode != null){
				stringBuffer.append(rightNode.postOrderTraverse());
			}

			stringBuffer.append(" " + data);

			return stringBuffer.toString();				
		}

		public String inOrderTraverse(){	
			StringBuilder stringBuffer = new StringBuilder();			
			
			if(leftNode != null){
				stringBuffer.append(leftNode.inOrderTraverse());				
			}
			
			stringBuffer.append(" " + data);

			if(rightNode != null){
				stringBuffer.append(rightNode.inOrderTraverse());
			}

			return stringBuffer.toString();				
		}
	}

	class Queue {

		class Node{
			private BinaryTree<T>.BinaryNode<T> data;
			private Node next;

			public Node(BinaryTree<T>.BinaryNode<T> data){
				this.data = data;
			}

			public Node(BinaryTree<T>.BinaryNode<T> data, Node next){
				this(data);
				this.next = next;
			}

			public String toString(){
				if(next==null)
					return data.toString();
				return data.toString() + " " + next.toString();
			}

			public BinaryTree<T>.BinaryNode<T> getData(){
				return data;
			}

			public Node getNext(){
				return next;
			}

			public void setData(BinaryTree<T>.BinaryNode<T> data){
				this.data = data;
			}

			public void setNext(Node next){
				this.next = next;
			}

			public boolean hasNext(){
				if(this.next == null)
					return false;
				return true;
			}
		}

		private Node front;

		public BinaryTree<T>.BinaryNode<T> dequeue(){
			if(front != null) {
				BinaryTree<T>.BinaryNode<T> prevFrontData = front.getData();
				front = front.getNext();
				return prevFrontData;
			}
			return null;
		}

		public void enqueue(BinaryTree<T>.BinaryNode<T> binaryNode) {
			if (front == null)
				front = new Node(binaryNode);
			else {
				Node next = front;
				while(next.hasNext()) {
					next = next.getNext();
				}
				next.setNext(new Node(binaryNode));
			}
		}

		public String toString(){
			return front.toString();
		}
	}

}
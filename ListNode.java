/**
 * ListNode is a class for storing a single node of a linked
 * list. This node class is for a list of integer values.
 */
public class ListNode {
	
	private int data;	   // data stored in this node
	private ListNode next;  // link to next node in the list

	/** constructs a node with data 0 and null link */
	public ListNode() {
		this(0, null);
	}

	/** 
	 * constructs a node with given data and null link 
	 * @param data payload
	 */
	public ListNode(int data) {
		this(data, null);
	}

	/**
	 * constructs a node with given data and given link 
	 * @param data payload
	 * @param next the next Node
	 */
	public ListNode(int data, ListNode next) {
		this.data = data;
		this.next = next;
	}
	/**
	 * set the next node
	 * @param next the next node
	 */
	public void setNext(ListNode next){
		this.next = next;
	}
	/**
	 * set the node's data
	 * @param data payload
	 */
	public void setData(int data){
		this.data = data;
	}
	/**
	 * get the next node
	 * @return the next node
	 */
	public ListNode getNext(){
		return next;
	}
	/**
	 * get the node's data
	 * @return the node's data
	 */
	public int getData(){
		return data;
	}
}

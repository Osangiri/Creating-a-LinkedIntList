/**
 * Class LinkedIntList can be used to store a list of integers.
 */

public class LinkedIntList {
	private ListNode front;  // first value in the list

	/** constructs an empty list */
	public LinkedIntList() {
		front = null;
	}
	
	/** 
	 * @return the current number of elements in the list 
	 */
	public int size() {
		int count = 0;
		ListNode current = front;
		while (current != null) {
			current = current.getNext();
			count++;
		}
		return count;
	}

	/**
	 * Give the element at an index
	 * @param index assumes 0 &lt;= index &lt; size()
	 * @return the integer at the given index in the list 
	 * @throws IllegalArgumentException if index is not valid
	 */
	public int get(int index) {
		if(index < 0 || index >= size()){
			throw new IllegalArgumentException();
		}
		return nodeAt(index).getData();
	}

	/**
	 * Create a logical representation of the list
	 * 
	 * @return a comma-separated, bracketed version of the list
	 */
	public String toString() {
		if (front == null) {
			return "[]";
		} else {
			String result = "[" + front.getData();
			ListNode current = front.getNext();
			while (current != null) {
				result += ", " + current.getData();
				current = current.getNext();
			}
			result += "]";
			return result;
		}
	}
	
	/**
	 * Search the list for a value.
	 * 
	 * @param value the value to find
	 * @return the position of the first occurrence of the given value (-1 if not found)
	 */
	public int indexOf(int value) {
		int index = 0;
		ListNode current = front;
		while (current !=  null) {
			if (current.getData() == value) {
				return index;
			}
			index++;
			current = current.getNext();
		}
		return -1;
	}

	/** 
	 * Appends the given value to the end of the list
	 * @param value the value to be added
	 */
	public void add(int value) {
		if (front == null) {
			front = new ListNode(value);
		} else {
			ListNode current = front;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(new ListNode(value));
		}
	}

	/**
	 * Inserts the given value at the given index
	 * assumes 0 &lt;= index &lt;= size()
	 * @param index the index to insert at
	 * @param value the value to insert
	 * @throws IllegalArgumentException if index is not valid
	 */
	public void add(int index, int value) {
		if(index < 0 || index > size()){
			throw new IllegalArgumentException();
		}
		if (index == 0) {
			front = new ListNode(value, front);
		} else {
			ListNode current = nodeAt(index - 1);
			current.setNext(new ListNode(value, current.getNext()));
		}
	}

	/**
	 * Removes value at the given index
	 * @param index the index to remove assuming 0 &lt;= index &lt; size()
	 * @throws IllegalArgumentException if index is not valid
	 */
	public void remove(int index) {
		if(index < 0 || index >= size()){
			throw new IllegalArgumentException();
		}
		if (index == 0) {
			front = front.getNext();
		} else {
			ListNode current = nodeAt(index - 1);
			current.setNext(current.getNext().getNext());
		}
	}

	// pre : 0 <= i < size()
	// post: returns a reference to the node at the given index
	private ListNode nodeAt(int index) {
		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current;
	}
}

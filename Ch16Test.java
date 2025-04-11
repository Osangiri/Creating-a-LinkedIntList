
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;
import java.lang.*;

/**
 * JUnit Tests for Chapter 16
 */
public class Ch16Test {
	
	private LinkedIntList emptyLinkedList;
    private LinkedIntList singleLinkedList;
    private LinkedIntList LinkedList;
	/**
	 * Reset the base data structures just in case
	 */
	@BeforeEach 
	void reset(){
		emptyLinkedList = new LinkedIntList();
        singleLinkedList = new LinkedIntList();
        singleLinkedList.add(1);
        
        LinkedList = new LinkedIntList();
        for (int i = 1; i <= 5; i++) {
            LinkedList.add(i);
        }
        
		
	}
	
	 /**
	 * Tests Equals method
	 */
	 @Test
	 public void testEquals(){
		assertEquals(LinkedList, LinkedList); 
		 
	 }
	  /**
	 * Tests pop method
	 */
	 @Test
	 public void testPop(){
		assertThrows(NoSuchElementException.class, () -> emptyLinkedList.pop(), "Pop on empty stack should throw exception");
        assertEquals(1, singleLinkedList.pop(), "Pop should return the only element in singleStack");
        assertEquals(0, singleLinkedList.size(), "Stack size should be 0 after pop");
        assertEquals(1, LinkedList.pop(), "Pop should return the top element in LinkedList");
        assertEquals(4, LinkedList.size(), "Stack size should be 4 after one pop");
		 
	 }
	  /**
	 * Tests Push method
	 */
	 @Test
	 public void testPush(){
		emptyLinkedList.push(10);
		System.out.println(emptyLinkedList);
		assertEquals(10, emptyLinkedList.pop(), "After push, pop should return the pushed element"); 
		assertEquals(0, emptyLinkedList.size(), "emptyLinkedList size should be 0 after pop");
		LinkedList.push(0);
		assertEquals("[0, 1, 2, 3, 4, 5]", LinkedList.toString(), "push should add new element at front and maintain order");
		 
	 }
	  /**
	 * Tests Stutter method
	 */
	 @Test
	 public void testStutter(){
		LinkedList.Stutter();
		assertEquals("[1, 1, 2, 2, 3, 3, 4, 4, 5, 5]", LinkedList.toString(), "Stutter should double individual numbers" );
		emptyLinkedList.Stutter();
		assertEquals("[]", emptyLinkedList.toString(), "Empty element should remain the same"); 
		singleLinkedList.Stutter();
		assertEquals("[1, 1]", singleLinkedList.toString(), "singleLinkedList should be [1, 1] after Stutter");
		 
	 }
	  /**
	 * Tests doubleList method
	 */
	 @Test
	 public void testdoubleList(){
		LinkedList.doubleList();
		assertEquals("[1, 2, 3, 4, 5, 1, 2, 3, 4, 5]", LinkedList.toString(), "Double list should double the list " );
		emptyLinkedList.doubleList();
		assertEquals("[]", emptyLinkedList.toString(), "If the list is empty no doubling is needed"); 
		singleLinkedList.doubleList();
		assertEquals("[1, 1]", singleLinkedList.toString(), "Double list should double a single element correctly");
		 
	 }
	 
	//@SuppressWarnings("unchecked")
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
	/**
		* equals method
		* Compares the specified object with this LinkedList for equality
		* @param other the object to be compared for equality with LinkedList
		* @return True if the specified object is equal to this LinkedList and otherwise False
		*/
	public boolean equals(Object other) {
		if (other instanceof LinkedIntList LinkedList) {
			LinkedIntList otherList = (LinkedIntList)other;
		 if (this.size() != LinkedList.size()) return false;
			 ListNode Current1 = this.front;//compare nodes one by one
			 ListNode Current2 = otherList.front;
			 while(Current1 != null && Current2 != null){
				 if(Current1.getData() != Current2.getData()){
					 return false;
			 }
				Current1 = Current1.getNext();
				Current2 = Current2.getNext();
				
			}
			return true;
		}
	 return false;
	}
	/**
	 * pops the value at the top of the stack 
	 * @return the value at the top of the stack
	 * @throws NoSuchElementException if is Empty
	 * 
	 */
	 public int pop(){
		 if (front == null){
			 throw new NoSuchElementException("Empty");
		 }
		 int value = front.getData();
		 front = front.getNext();
		 return value;
		 
	 }
	 /**
	  * push method so that it behaves like stack
	  * @param value the value pushed onto the stack
	  */
	 public void push(int value){
		 front = new ListNode(value, front);
	}
	/**
	 * Stutter method
	 * Duplicate each node
	 */
	 public void Stutter(){
		 ListNode current = front;

		 while (current != null) {
			//a duplicate node with the same value as the current node
			ListNode duplicate = new ListNode(current.getData(), current.getNext());
			current.setNext(duplicate);
			current = duplicate.getNext();
    }
	 }
	 /**
	  * doubleList method
	  * doubles the size of the list by appending the
	  * copy of list to end
	  */
	 public void doubleList(){
		 if (front == null){
			return; 
		}
		
	   ListNode current = front;
       ListNode Start = null;
       ListNode End = null;
       while (current != null) {
        
        ListNode newNode = new ListNode(current.getData());//a new node with same data as current

        // Adding the new node to the copied part
        if (Start == null) {
            Start = newNode;  // This is the start of the copied part
            End = Start;  // This is the last node in the copied part
        } else {
            End.setNext(newNode); // Add new node to the copied list
            End = newNode;        // Update the last node in the copied list
        }

        current = current.getNext();
    }
		 if (Start != null) {
			ListNode originalEnd = front;
			while (originalEnd.getNext() != null) {
				originalEnd = originalEnd.getNext();
			}
			originalEnd.setNext(Start);  // Link the original list to the copied list
		}
       
}
}
}

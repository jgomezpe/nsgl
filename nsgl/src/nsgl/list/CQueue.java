package nsgl.list;

import java.util.Iterator;

import nsgl.collection.Collection;
import nsgl.object.Comparable;

/**
 * <p>Title: CQueue</p>
 * <p>Description: A circular queue.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class CQueue<T> implements Collection<T>{
	/**
	 * The cursor of the circular queue (current position)
	 */
	private Node<T> start = null;

	private int size=0;
		
	/**
	 * Constructor: Creates a empty circular queue
	 */
	public CQueue() { }

	/**
	 * Advances in the circular one position.
	 * <p>C = a -> b -> c -> d --> a</p>
	 * <p>C.next() =>  C = b -> c -> d -> a --> b and returns b</p>
	 * @return The object at the circular queue cursor position after advancing one position. Returns null if the queue is empty
	 */
	public T next() {
			start = start.next;
			return start.data;
	}

	/**
	 * Advances in the circular queue n positions.
	 * <p>C = a -> b -> c -> d --> a</p>
	 * <p>C.advance( 2 ) =>  C = c -> d -> a -> b --> c and returns c</p>
	 * @param n The number of positions to advance
	 * @return The object at the circular queue cursor position after advancing n positions. Returns null if the queue is empty
	 */
	public T advance(int n) {
		for (int i = 0; i < n; i++) start = start.next;
		return start.data;
	}

	/**
	 * Backs in the circular one position.
	 * <p>C = a -> b -> c -> d --> a</p>
	 * <p>C.prev( ) =>  C = d -> a -> b -> c --> d and returns d</p>
	 * @return The object at the circular queue cursor position after backing one position. Returns null if the queue is empty
	 */
	public T prev() {
		start = start.prev;
		return start.data;
	}

	/**
	 * Adds a new object to the circular queue. The object is added after the current object in the queue. The cursor is not moved.
	 * <p>C = a -> b -> c -> d --> a</p>
	 * <p>C.add( x ) =>  C = a -> x -> b -> c -> d --> a</p>
	 * @param data The object to be added
	 */
	public boolean add(T data) {
		if (isEmpty()) {
			start = new Node<T>(data);
			start.prev = start;
			start.next = start;
		} else {
			Node<T> aux = new Node<T>(data);
			aux.next = start.next;
			aux.next.prev = aux;
			start.next = aux;
			aux.prev = start;
		}
		size++;
		return true;
	}

	/**
	 * Deletes the current object in the circular queue.
	 * The new current object is the next to the old current object
	 * <p>C = a -> b -> c -> d --> a</p>
	 * <p>C.del() =>  C = b -> c -> d --> b</p>
	 */
	public boolean del() {
		if (!isEmpty()) {
			if (start.next == start) {
				start = null;
			} else {
				start.prev.next = start.next;
				start.next.prev = start.prev;
				start = start.next;
			}
			size--;
			return true;
		}
		return false;
	}

	/**
	 * Gets the current object in the circular queue. If the circular queue is empty return null
	 * <p>C = a -> b -> c -> d --> a</p>
	 * <p>C.get() = a</p>
	 * @return The cursor of the circular queue
	 */
	public T get(){ return start.data; }

	@Override
	public Iterator<T> iterator(){ return new ListIterator<T>(start); }

	@Override
	public boolean contains(T data){
	    if( isEmpty() ) return false;
	    Node<T> x = start;
	    Comparable c = Comparable.cast(data);
	    while(x.next!=start && c.ne(x.data)) x = x.next;
	    return c.ne(x.data);
	}

	@Override
	public int size() { return size; }
}
package nsgl.array.sort;

import nsgl.array.Static;
import nsgl.array.Sort;
import nsgl.array.Array;
import nsgl.order.Order;

/**
 * <p>InsertionSort algorithm</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Insertion extends Sort{
    /**
     * Crates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     */
    public Insertion(Order order) { super(order); }

    /**
     * Crates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorted
     */
    public Insertion(Order order, int start, int end){ super(order, start, end ); }

    /**
     * Sorts a vector of objects using Insertion sort
     * @param a array to be sorted
     */
    public void apply(Object a, int start, int end, Order order) {
	for (int i = start+1; i < end; i++) {
	    Object y;
	    Object x = java.lang.reflect.Array.get(a, i);
	    int j = i - 1;
	    while(j >= start && order.compare(x, y=java.lang.reflect.Array.get(a, j))<0) {
  		java.lang.reflect.Array.set(a, j+1, y);
  		j--;
	    }
	    java.lang.reflect.Array.set(a, j+1, x);
	}
    }
 
    /**
     * Sorts a vector of objects using Insertion sort
     * @param a array to be sorted
     */
    public <T> void apply(Array<T> a, int start, int end, Order order) {
	if( a instanceof Static ) {
	    apply(buffer((Static<T>)a),start,end,order);
	    return;
	}
	for (int i = start+1; i < end; i++) {
	    T y;
	    T x = a.get(i);
	    int j = i - 1;
	    while(j >= start && order.compare(x, y=a.get(j))<0) {
		a.set(j+1, y);
		j--;
	    }
	    a.set(j+1, x);
	}
    }
}

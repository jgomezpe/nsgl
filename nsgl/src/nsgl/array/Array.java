package nsgl.array;

import java.util.Iterator;

import nsgl.integer.Interval;
import nsgl.keymap.KeyMap;

/**
 * <p>Title: Array</p>
 *
 * <p>Description: Abstract Array class.
 *
 */
public interface Array<T> extends KeyMap<Integer,T>{
    /**
     * Gets the element that is located at the given position.
     * @param index Position of the element to obtain
     * @return The element that is located at the given position
     * @throws NoSuchElementException If the index is a non valid position
     */
    T get(int index);
    
    /**
     * Gets the element that is located at the given position.
     * @param index Position of the element to obtain
     * @return The element that is located at the given position
     * @throws NoSuchElementException If the index is a non valid position
     */
    @Override
    default T get(Integer index) { return get((int)index); } 
    
    /**
     * Sets the element at the given position.
     * @param index Position of the element to set
     * @param data Element to set at the given position
     * @return <i>true</> if the element could be set, <i>false</i> otherwise.
     */
    boolean set(int index, T data);
    
    /**
     * Sets the element at the given position.
     * @param index Position of the element to set
     * @param data Element to set at the given position
     * @return <i>true</> if the element could be set, <i>false</i> otherwise.
     */
    default boolean set(Integer index, T data) { return set((int)index,data); }
    
    /**
     * Creates an iterator for the Array. The Array can be traversed using a for each approach.
     * @return An iterator for the Array.
     */
    @Override
    default Iterator<T> iterator(){ return iterator(0); }
	
    /**
     * Creates an iterator for the Array, starting at the given index.
     * @param start Initial position for the iterator
     * @return An iterator for the Array starting at the given position
     */
    default Iterator<T> iterator( int start ) {
	return new Iterator<T>() {
	    protected int pos=start;
	    @Override
	    public boolean hasNext(){ return pos<size(); }
	    @Override
	    public T next() { return get(pos++); }
	};
    }
    
    /**
     * Collection of valid indices
     * @return An Iterable collection of valid indices
     */
    @Override
    default Iterable<Integer> keys() { return new Interval(size()); }   
    
    Array<T> instance(int size);
    
    /**
     * Shuffles the given array of Objects
     * @param <T> Type of objects to be shuffled
     * @param set Array of objects to be shuffled
     */
    public static <T> void shuffle(T[] set) {
	int j, k;
	T temp;
	int[] indices = nsgl.integer.array.Util.shuffle_indices(set.length);
	for (int i = 0; i<indices.length; i+=2) {
	    j = indices[i];
	    k = indices[i+1];
	    temp = set[j];
	    set[j] = set[k];
	    set[k] = temp;
	}
    }

    /**
     * Shuffles the given array of Objects
     * @param <T> Type of objects to be shuffled
     * @param set Array of objects to be shuffled
     */
    public static <T> void shuffle(Array<T> set) {
	int j, k;
	T temp;
	int[] indices = nsgl.integer.array.Util.shuffle_indices(set.size());
	for (int i = 0; i<indices.length; i+=2) {
	    j = indices[i];
	    k = indices[i+1];
	    temp = set.get(j);
	    set.set(j,set.get(k));
	    set.set(k, temp);
	}
    }
}
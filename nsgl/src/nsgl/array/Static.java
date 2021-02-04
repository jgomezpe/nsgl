package nsgl.array;

/**
 * <p>Title: Static</p>
 *
 * <p>Description: Static Array.
 *
 */
public class Static<T> implements Array<T>{
    /**
     * Elements of the array
     */
    protected Object buffer;
    
    /**
     * Size of the array
     */
    protected int size;
   
    /**
     * Creates an array of the given size
     * @param size Size of the array
     */
    public Static( int size ) { 
	this.size = size; 
	this.buffer = java.lang.reflect.Array.newInstance(Object.class,size);
    }

    /**
     * Creates an array from the given buffer of elements. Size of the array is defined by the length of the buffer
     * @param buffer Initial elements of the array
     */
    public Static( Object buffer ) {
	this.buffer = buffer;
	this.size = memsize();
    }

    /**
     * Size of the buffer
     * @return Size of the buffer
     */
    protected int memsize() { return java.lang.reflect.Array.getLength(buffer); }

    /**
     * Creates a buffer of <i>n</i> objects  
     * @param n Length of the buffer to be created
     * @return A buffer of elements
     */
    protected Object alloc(int n) {
	return java.lang.reflect.Array.newInstance(Object.class,n);
    }

    /**
     * Creates a shallow copy of the buffer 
     * @param n Size of the buffer
     * @return A buffer of the given size with a shallow copy of the elements in the array's buffer
     */
    protected Object copy(int n) {
	Object nbuffer = alloc(n);
	System.arraycopy(buffer, 0, nbuffer, 0, Math.min(n, memsize()));
	return nbuffer;
    }

    /**
     * Gets the element that is located at the given position.
     * @param index Position of the element to obtain
     * @return The element that is located at the given position
     * @throws NoSuchElementException If the index is a non valid position
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) { return (T)java.lang.reflect.Array.get(buffer, index); };

    /**
     * Sets the element at the given position.
     * @param index Position of the element to set
     * @param data Element to set at the given position
     * @return <i>true</> if the element could be set, <i>false</i> otherwise.
     */
   @Override
    public boolean set(int index, T data) {
	java.lang.reflect.Array.set(buffer, index, data);
	return index<size();
    }

   /**
    * Determines the number of objects stored by the array
    * @return Number of objects stored by the array.
    */
    @Override
    public int size() { return size; }

    /**
     * Removes the element at the given position
     * @param loc Location of the object to be deleted
     * @return <i>true</i> if the element at the given position could be removed, <i>false</i> otherwise
     */
    @Override
    public boolean remove(Integer loc) { return false; }

    @Override
    public Array<T> instance(int size) {
	return new Static<T>(size);
    }
    
    /**
     * Determines if the collection is full or not
     * @return <i>true</i> if the collection is full <i>false</i> otherwise
     */
    public boolean isFull() { return true; };        
}
package nsgl.array;

/**
 * <p>Title: Dynamic</p>
 *
 * <p>Description: Abstract resizable (add/remove of elements) Array.
 *
 */
public abstract class Dynamic<T> extends Static<T> implements nsgl.collection.Dynamic<T>{

    /**
     * Creates an Array with no elements (size=0)
     */
    public Dynamic() { super(0); }

    /**
     * Creates an array from the given buffer of elements. The size of the array is defined 
     * by the buffer's length
     * @param buffer Buffer of elements.
     */
    public Dynamic(Object buffer) { super(buffer); }
	
    /**
     * Creates an array from the given buffer of elements and provided size. 
     * Parameter <i>size</i> must be less or equal than <i>buffer</i>'s length
     * @param buffer Buffer of elements.
     * @param size Array size
     */
    public Dynamic(Object buffer, int size) {
	super(buffer);
	this.size = size;
    }

    /**
     * Removes all the element of the array (sets size of the array to zero)
     */
    public void clear(){ size = 0; }
	
    /**
     * Removes the element at the given position
     * @param index The position of the object to be deleted
     * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
     */
    public boolean remove( int index ){
	if(0<=index && index<size() && ready4Remove()){
	    leftShift( index );
	    return true;
	}	
	return false;
    }	
	
    /**
     * Adds a data element at the end of the array
     * @param data Data element to be inserted
     * @return <i>true</i> if the element could be added, <i>false</i> otherwise
     */
    public boolean add( T data ){
	if( ready4Add() ) {
	    set( size(), data );
	    size++;
	    return true;
	}else return false;
    }

    /**
     * Adds an element at the given position. Elements at positions <i>index+1...size()-1</i> 
     * are moved one position ahead. 
     * @param index Position to be occupied by the new element
     * @param data Element that will be added into the Vector
     * @return <i>true</i> If the element could be added at the given position, <i>false</i> otherwise
     */
    public boolean add( int index, T data ){
	if( index < 0 || index > size() || !ready4Add() ) return false;
	rightShift(index);
	set( index, data );
	return true;
    }
	
    /**
     * Determines if the dynamic array is ready for adding a new element
     * @return <i>true</i> if the array is ready for adding a new element, <i>false</i> otherwise
     */
    protected abstract boolean ready4Add();

    /**
     * Determines if the dynamic array is ready for removing an element
     * @return <i>true</i> if the dynamic array is ready for removing an element, <i>false</i> otherwise
     */
    protected boolean ready4Remove() { return size()>0; }
		
    protected void leftShift( int index ) throws IndexOutOfBoundsException{
	size--;
	System.arraycopy(buffer, index+1, buffer, index, size-index);
    }
	
    protected void rightShift( int index ) throws IndexOutOfBoundsException{
	System.arraycopy(buffer, index, buffer, index+1, size-index);
	size++;
    }	

    /**
     * Determines if the collection is full or not
     * @return <i>true</i> if the collection is full <i>false</i> otherwise
     */
    public boolean isFull() { return false; };     

}
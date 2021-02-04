package nsgl.array;

/**
 * <p>Title: DynamicWithMaxSize</p>
 *
 * <p>Description: Resizable (add/remove of elements) Array with Maximum size.
 *
 */
public class DynamicWithMaxSize<T> extends Dynamic<T>{
    /**
     * Maximum size of the array
     */
    protected int maxSize;
	
    /**
     * Creates a dynamic array with maximum size
     * @param maxSize Maximum size of the dynamic array
     */
    public DynamicWithMaxSize( int maxSize ) { 
	super(maxSize); 
	this.maxSize = maxSize;
    }

    /**
     * Creates a dynamic array from the buffer with maximum size set to the size of the buffer
     * @param buffer Initial elements of the dynamic array
     */
    public DynamicWithMaxSize(Object buffer) {
	super(buffer);
	this.maxSize = size;
    }

    /**
     * Creates a dynamic array from the buffer with maximum size set to the size of the buffer
     * @param buffer Initial elements of the dynamic array
     * @param size Initial size of the dynamic array
     */
    public DynamicWithMaxSize(Object buffer, int size) {
	super(buffer);
	this.maxSize = this.size;
	this.size = size;
    }
	
    @Override
    public Array<T> instance(int n){
	return new DynamicWithMaxSize<T>(java.lang.reflect.Array.newInstance(buffer.getClass().getComponentType(),n));
    }
		
    /**
     * Determines if the dynamic array is ready for adding a new element
     * @return <i>true</i> if the array is ready for adding a new element, <i>false</i> otherwise
     */
    @Override
    protected boolean ready4Add() { return size()<maxSize; }	
   
    /**
     * Determines if the collection is full or not
     * @return <i>true</i> if the collection is full <i>false</i> otherwise
     */
    public boolean isFull() { return size()==maxSize; }
}
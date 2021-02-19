package nsgl.service.trace;
import nsgl.array.Array;

public class ArrayTracer implements Tracer {
	/**
	 * Traced information
	 */
	private Array<Object[]> object = new Array<Object[]>();
	
	private int each=1;
	private int count = 0;

	/**
	 * Creates a new SingleResultTracer
	 */
	public ArrayTracer(){}

	/**
	 * Creates a new SingleResultTracer
	 */
	public ArrayTracer( int each ){ this.each=each; }

	/**
	 * Replaces the traced information with a new one
	 * @param obj Traced information
	 */
	public void add(Object caller, Object... obj){
		count = (count+1)%each;
		if( count==0 ) object.add(obj);
	}

	/**
	 * Returns the traced information
	 * @return A single object representing the traced information
	 */
	public Object get(){ return object; }

	/**
	 * Cleans the traced information
	 */
	public void clean(){ object.clear(); }

	/**
	 * Closes the tracer (does nothing)
	 */
	public void close() {};
	
	@Override
	public void clear() { object.clear(); }	
}
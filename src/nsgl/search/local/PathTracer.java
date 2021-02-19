package nsgl.search.local;

import nsgl.service.trace.Tracer;
import nsgl.tagged.Tagged;

public class PathTracer<T> implements Tracer {
    public static final String PARENT = "parent";
    
	@SuppressWarnings("unchecked")
	@Override
	public void add(Object caller, Object... obj){
		Tagged<T> parent = (Tagged<T>)obj[0];
		if( obj.length % 2 == 1 ){
			int k=1;		
			for( int i=k; i<obj.length; i+=2 ) ((Tagged<T>)obj[i+1]).setTag( PARENT, parent);	
		}
	}

	@Override
	public void close(){}

	@Override
	public Object get(){ return null; }

	@Override
	public void clear() {}	
}
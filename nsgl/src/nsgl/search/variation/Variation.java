package nsgl.search.variation;

import nsgl.tagged.Tagged;
import nsgl.tagged.Manager;
import nsgl.search.space.Space;

public interface Variation<T> extends Manager<T>{
	public default int arity(){ return 0; };

	public default int range_arity(){ return 0; };
	
	@SuppressWarnings("unchecked")
	public default T[] apply(T... pop){ return unwrap(apply(wrap(pop))); }
    
    @SuppressWarnings("unchecked")
	public default T[] apply(Space<T> space, T... pop){ return space.repair(apply(pop)); }
    
	@SuppressWarnings("unchecked")
	public default Tagged<T>[] apply(Tagged<T>... pop){ return wrap(apply(unwrap(pop))); }    
    
	@SuppressWarnings("unchecked")
	public default Tagged<T>[] apply(Space<T> space, Tagged<T>... pop){ return space.repair(apply(pop)); } 	
}
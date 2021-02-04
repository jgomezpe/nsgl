/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.search.variation;

import nsgl.tagged.Tagged;
import nsgl.search.space.Space;

/**
 *
 * @author jgomez
 */
public interface Variation_1_1<T> extends Variation_n_1<T>{

	public default T apply( Space<T> space, T x ){ return space.repair(apply(x)); }

	@SuppressWarnings("unchecked")
	public default Tagged<T> apply( Space<T> space, Tagged<T> x ){
		return space.repair(apply(x))[0];
	}
    
	public default T apply( T x ){ return apply( wrap(x) ).unwrap(); }   
    
	public default Tagged<T> apply( Tagged<T> x ){ return wrap(apply(x.unwrap())); }   

	@SuppressWarnings("unchecked")
	@Override
	public default T[] apply(T... pop){
		T[] nPop = (T[])new Object[pop.length];
		for( int i=0; i<pop.length; i++ ) nPop[i] = apply(pop[i]);
		return nPop;
	}

	@SuppressWarnings("unchecked")
	@Override
	public default Tagged<T>[] apply(Tagged<T>... pop){ 
		Tagged<T>[] nPop = (Tagged<T>[])new Tagged[pop.length];
		for( int i=0; i<pop.length; i++ ) nPop[i] = apply(pop[i]);
		return nPop;
	}
	
	@Override
	public default int arity(){	return 1; }    
}
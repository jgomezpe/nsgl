/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.random;

import nsgl.random.raw.RawGenerator;

/**
 *
 * @author jgomez
 */
public interface Pick {
	/**
	 * Gets the number of different elements that will be pick from the set
	 * @return Number of different elements that will be pick from the set
	 */
	int size();
	/**
	 * Picks <i>size()</i> different elements from the set <i>0,1,2,...,n-1</i>. Clearly <i>n &lt; size() </i> otherwise the full set will be returned 
	 * @param n Size of the set
	 * @return A subset of elements from the set 0,1,2,...,n-1. The subset has <i>size()</i> different elements 
	 */
    default int[] get( int n ) {
    	int s = size();
    	if( s==1 ) return new int[] { RawGenerator.cast(this).integer(n) };
    	int[] a = nsgl.integer.array.Util.shuffle(n);
    	if( n<s ) {
    		int[] x = new int[n];
    		System.arraycopy(a, 0, x, 0, n);
    		a = x;
    	}
    	return a;
    }
}

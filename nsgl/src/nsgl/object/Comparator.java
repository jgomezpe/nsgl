/**
 * <p>Copyright: Copyright (c) 2019</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2019 by Jonatan Gomez-Perdomo. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 * @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
 * (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */
package nsgl.object;

import java.lang.reflect.Array;

import nsgl.service.Service;

/**
 * <p>Title: Comparator</p>
 *
 * <p>Description: Compares two objects to determine if they are equal or not</p>
 *
 */
public interface Comparator{
    /**
     * Determines if the object one is equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one==two)
     */
    public boolean eq(Object one, Object two);
    
    /**
     * Determines if the object one is not equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one!=two)
     */
    default boolean ne(Object one, Object two) { return !eq(one,two); }
    
    default Comparable cast(Object obj){
	Comparator comp = this;
	return new Comparable() {
	    @Override 
	    public boolean eq(Object two){ return comp.eq(obj,two); } 
	};
    }

    /**
     * Determines if the object one is equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one==two)
     */
    static boolean EQ(Object one, Object two ) {
	if(one instanceof Comparable) return ((Comparable)one).eq(two);
	return get(one).eq(one,two);
    }

    /**
     * Determines if the object one is not equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one!=two)
     */
    static boolean NE(Object one, Object two ) { return !EQ(one,two); }
    
    static void init() {
	Comparator c = (Comparator)Service.get(Object.class,Comparable.class);
	if( c==null ) {
	    c = new Comparator() {
		@Override
		public boolean eq(Object one, Object two) { return one==two; }
	    };
	    Comparator.add(Object.class,c);
	    c = new Comparator() {
		@Override
		public boolean eq(Object one, Object two) {
		    int n = Array.getLength(one);
		    int m = Array.getLength(two);
		    if( n!=m ) return false;
		    boolean flag = true;
		    for( int i=0; flag && i<n; i++ ) 
			flag = Comparator.EQ(Array.get(one, i), Array.get(two, i));
		    return flag; 
		}
	    };
	    Comparator.add(Array.class,c);
	}
	
    }
    
    /**
     * Obtains a comparator method for the given object
     * @param obj Object that will get its comparator method
     * @return A comparator method for the given object
     */
    static Comparator get( Object obj ){
	init();
	if(Service.primitive(obj)) return (Comparator)Service.get(Object.class,Comparable.class);
	if(Service.array(obj)) return (Comparator)Service.get(Array.class,Comparable.class);
	return (Comparator)Service.get(obj,Comparable.class);
    }
    
   
    /**
     * Adds a Comparator for the given object 
     * @param caller Object that will register its comparator method
     * @param comparator Comparator method
     */
    static void add( Object caller, Comparator comparator ){ Service.set(caller, Comparable.class, comparator); }	
    
}
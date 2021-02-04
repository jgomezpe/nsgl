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
package nsgl.service.copy;

import java.lang.reflect.Array;

import nsgl.object.Copyable;
import nsgl.service.Service;

/**
 * <p>Title: Cloner</p>
 *
 * <p>Description: A cloning method</p>
 *
 */
public interface Copier {
    /**
     * Cloning method
     * @param obj Object to be cloned
     * @return A clone of the object
     */
    Object copy( Object obj );
	
    default Copyable cast(Object obj) {
	Copier c = this;
	return new Copyable() {
	    @Override 
	    public Object copy(){ return c.copy(obj); } 
	};
    }
    
    /**
     * Obtains a cloner method for the given object
     * @param obj Object that will get its cloning method
     * @return A cloner method for the given object
     */
    static Copier get( Object obj ){
	init();
	if(Service.primitive(obj)) return (Copier)Service.get(Object.class,Copyable.class);
	if(Service.array(obj)) return (Copier)Service.get(Array.class,Copyable.class);
	return (Copier)Service.get(obj,Copyable.class);
    }
	
    /**
     * Adds a Cloning method for the given object 
     * @param caller Object that will register its cloning method
     * @param cast Cloning method
     */
    static void add( Object caller, Copier cast ){
	Service.set(caller, Copyable.class, cast); 
    }	

		
    /**
     * Clones the object
     * @param obj Object to be cloned
     * @return A clone of the object
     */
    static Object apply( Object obj ){
	if( obj instanceof Copyable ) return ((Copyable)obj).copy();
	return get(obj).copy(obj); 
    }
    
    static void init() {
	Copier c = (Copier)Service.get(Object.class, Copyable.class);
	if( c==null ) {
	    c = new Copier(){ @Override public Object copy(Object obj){ return obj; } };
	    Copier.add(Object.class, c);
	    c = new Copier() {
		@Override
		public Object copy(Object obj) {
		    Class<?> c = obj.getClass();
		    int n = Array.getLength(obj);
		    Object clone = Array.newInstance(c.getComponentType(), n);
		    if( c.getComponentType().isPrimitive() )
			System.arraycopy(obj, 0, clone, 0, n);
		    else 
			for( int i=0; i<n; i++ ) Array.set(clone, i, apply(Array.get(obj, i)));
		    return clone;
		}
	    };
	    Copier.add(Array.class, c);
	}
    }    
}
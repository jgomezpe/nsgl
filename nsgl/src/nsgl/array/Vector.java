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
package nsgl.array;

import nsgl.util.Fibonacci;

/**
 * <p>Title: Vector</p>
 *
 * <p>Description: A vector of objects (parameterized).</p>
 *
 */
public class Vector<T> extends Dynamic<T>{
    /**
     * Growing/Shrinking strategy
     */
    Fibonacci sizeManager;

    /**
     * Creates a Vector having an initial buffer with length 100
     */
    public Vector(){ this(100); }
	
    /**
     * Creates a Vector having an initial buffer with the given length
     * @param n Initial buffer's length
     */
    public Vector(int n){
	sizeManager = new Fibonacci(n);
	sizeManager.prev();
    }

    /**
     * Creates a Vector using the given buffer of elements
     * @param buffer Initial elements of the Vector. Size is set to buffers length
     */
    public Vector(Object buffer){ this(buffer, java.lang.reflect.Array.getLength(buffer)); }

    /**
     * Creates a Vector using the given buffer of elements and the given initial size
     * @param buffer Initial elements of the Vector. 
     * @param size Initial size of the Vector 
     */    
    public Vector(Object buffer, int size) {
	super(buffer);
	sizeManager = new Fibonacci(this.size);
	sizeManager.prev();
	this.size = size; 
	resize();
    }

    public Array<T> instance(int n){
	if( buffer != null ) return new Vector<T>(java.lang.reflect.Array.newInstance(buffer.getClass().getComponentType(),n));
	return new Vector<T>(n);
    }
		
    /**	
     * Reset the array to initial values (including the buffer size)
     */
    @Override
    public void clear(){
	sizeManager.clear();
	resize();
	size = 0;
    }

    /**
     * Sets the size of the array
     * @param n The new size of the array
     */
    protected void resize( int n ){
	int x = sizeManager.n();
	if( x!=sizeManager.find_fib(n) ) resize();
	size = n; 
    }
	
    /**
     * Resizes the inner buffer according to the associated Fibonacci numbers (new buffer size must be <i>c</i>)
     */
    protected void resize() { buffer = copy(sizeManager.n_2()); }

    /**
     * Determines if the dynamic array is ready for adding a new element. Basically,
     * it increases the size of the buffer according to the associated Fibonacci numbers
     * (new buffer size will be <i>b+c</i>)
     * @return <i>true</i> if the array is ready for adding a new element, <i>false</i> otherwise
     */
    @Override
    public boolean ready4Add() {
	if(memsize()==0) buffer = alloc(sizeManager.n_2());
	if( size()==sizeManager.n_2() ){
	    sizeManager.next();
	    resize();        
	}
	return true;
    }
	
    /**
     * Determines if the dynamic array is ready for removing an element. Basically,
     * it decreases the size of the buffer according to the associated Fibonacci numbers 
     * (new buffer size will be <i>b</i>)
     * @return <i>true</i> if the dynamic array is ready for removing an element, <i>false</i> otherwise
     */
    @Override
    public boolean ready4Remove() {
	if(  size() < sizeManager.n() && sizeManager.n()!=sizeManager.prev() ) resize();
	return true;
    }
}
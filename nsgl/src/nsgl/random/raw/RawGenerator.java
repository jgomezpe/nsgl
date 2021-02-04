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
package nsgl.random.raw;

import nsgl.service.Service;

/**
 * <p>Title: RawGenerator</p>
 *
 * <p>Description: Abstract class. It is a generator of uniform distributed pseudo random numbers in the interval [0.0,1.0) (x~U[0,1)).
 *  It has some useful methods for generating boolean values and uniform distributed integers. </p>
 *
 */
public interface RawGenerator{
	/**
	 * Generates a random number in the interval [0.0,1.0) following a uniform distribution x~U[0,1)
	 * @return a random number in the interval [0.0,1.0) following a uniform distribution x~U[0,1)
	 */
	double next();
    
	/**
	 * Generates a boolean value (<i>false</i> or <i>true</i> with equal probability)
	 * @return A boolean value (<i>false</i> or <i>true</i> with equal probability)
	 */
	default boolean bool(){ return (next() >= 0.5); }

	/**
	 * Generates a boolean value with the given probability
	 * @param falseProbability The probability of generating a <i>false</i>. Clearly, (1.0-falseProbability)
	 * provides the probability of generating a <i>true</i> value
	 * @return A boolean value with the given probability
	 */
	default boolean bool(double falseProbability){ return (next() >= falseProbability); }

	/**
	 * Generates a uniform distributed integer value in the interval [0,max-1]
	 * @param max The superior limit of the half-open interval [0,max) defined for generating integer values
	 * @return An integer value in the interval [0,max) with uniform distribution
	 */
	default int integer(int max){ return ((int) (max * next())); }    
    
	/**
	 * Returns a set of random numbers following the x ~ U[0,1) distribution
	 * @param v Array used for returning the generated random numbers
	 * @param offset Stating point for storing the generated real numbers
	 * @param m The number of random numbers to be generated and returned in array <i>v</i>.
	 * @return A set of random numbers following the x ~ U[0,1) distribution
	 */
	default double[] raw(double[] v, int offset, int m){
		for(int i = 0; i < m; i++) v[i+offset] = next();
		return v;
	}

	/**
	 * Returns a set of random numbers following the x ~ U[0,1) distribution
	 * @param m The number of random numbers to be generated
	 * @return An array with <i>m</i> random numbers following the x ~ U[0,1) distribution
	 */
	default double[] raw(int m){
		double[] v = null;
		if (m > 0) {
			v = new double[m];
			raw(v, 0, m);
		}
		return v;
	}
	
// Service	
	/**
	 * Obtains a comparator method for the given object
	 * @param obj Object that will get its comparator method
	 * @return A comparator method for the given object
	 */
	static RawGenerator cast( Object obj ){
		if( Service.get(Object.class,RawGenerator.class) == null ) Service.set(Object.class, RawGenerator.class, new JavaGenerator());
		return (RawGenerator)Service.get(obj,RawGenerator.class);
	}
	
	/**
	 * Adds a Comparable casting method for the given object 
	 * @param caller Object that will register its casting method
	 * @param cast Casting method
	 */
	static void cast( Object caller, RawGenerator cast ){
		if( Service.get(Object.class,RawGenerator.class) == null ) Service.set(Object.class, RawGenerator.class, new JavaGenerator());
		if(Service.get(Object.class,RawGenerator.class) != cast ) Service.set(caller, RawGenerator.class, cast); 
	}		
}
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
package nsgl.real.random;

/**
 * <p>Title: Symmetric</p>
 *
 * <p>Description: Generates random real numbers by combining a one side real random number distribution (for the value) and
 *  a boolean random distribution for the side.The one side distribution must be able to generate number in R+ = [0, inf)</p>
 *
 */
public class Symmetric implements Random {
	/**
	 * Boolean random distribution for the side.
	 */
	protected nsgl.bit.random.Random side;
	/**
	 * One side real random number distribution for values
	 */
	protected Random one_side;
	
	/**
	 * Creates a symmetric real numbers random distribution with a side probability of 0.5 and a [0,1) uniform distribution, i.e., 
	 * this distribution will generate uniform real numbers in the interval (-1, 1)  
	 */
	public Symmetric(){ this( new Uniform()); }
	/**
	 * Creates a symmetric real numbers random distribution with a side probability of 0.5 and the given one side distribution
	 * @param one_side One side real random number distribution for values
	 */
	public Symmetric( Random one_side ){ this( new nsgl.bit.random.Random(), one_side ); }
	
	/**
	 * Creates a symmetric real numbers random distribution with the given side and one side distribution
	 * @param side Boolean random distribution for the side.
	 * @param one_side One side real random number distribution for values
	 */
	public Symmetric( nsgl.bit.random.Random side, Random one_side ){
		this.side = side;
		this.one_side = one_side;
	}
	
   /**
     * Returns a random double number
     * @return A random double number
     */
    @Override
    public double next(){ return side.next()?one_side.next():-one_side.next(); }  
}
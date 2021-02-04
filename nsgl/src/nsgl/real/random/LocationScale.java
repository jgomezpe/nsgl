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

import nsgl.random.raw.UsesRawGenerator;

/**
 * <p>Title: LocationScale.</p>
 *
 * <p>Description: An abstract random distribution that can be located and scaled.</p>
 *
 */
public abstract class LocationScale implements UsesRawGenerator, Random{
	/**
	 * Scalable operation of the random distribution
	 * @author Jonatan Gomez
	 *
	 */
	protected interface Scale{ public double apply( double x); }
	
	/**
	 * When the scaling factor is just one, it is the identity
	 * @author Jonatan Gomez
	 *
	 */
	protected class One implements Scale{ @Override public double apply(double x){ return x; }	}
	
	/**
	 * When the scaling factor is not one, multiply by the scaling factor
	 * @author Jonatan Gomez
	 *
	 */
	protected class NoOne implements Scale{
	    protected double sigma = 1.0;
	    
	    public NoOne(double sigma) { this.sigma = sigma; }
	    
		@Override
		public double apply(double x){ return sigma*x; }
	}
	
	/**
	 * Scaling method associated to the distribution
	 */
	protected Scale p;
	
	/**
	 * Location operation of the random distribution
	 * @author Jonatan Gomez
	 *
	 */
	protected interface Locate{ public double apply( double x); }
	
	/**
	 * When the shift value is zero it is just the identity
	 * @author Jonatan Gomez
	 *
	 */
	protected class Zero implements Locate{ @Override public double apply(double x){ return x; }	}
	
	/**
	 * When the shift value is not zero, add the shift value 
	 * @author Jonatan Gomez
	 *
	 */
	protected class NoZero implements Locate{
	    protected double miu = 0.0;
	    
	    public NoZero(double miu) { this.miu = miu; }
	    
		@Override
		public double apply(double x){ return x+miu; }
	}
	
	/**
	 * Location operation associated to the distribution
	 */
	protected Locate l;

	/**
	 * Creates a standard location-scale distribution (shift-value <i>miu=0</i> and scale factor <i>sigma=1.0</i>)  
	 */
	public LocationScale() { this( 0.0, 1.0 ); 	}
	
	/**
	 * Creates a location-scale distribution whit scale factor <i>sigma=1.0</i> and the given shift-value <i>miu</i> 
	 * @param miu Location of the distribution  
	 */
	public LocationScale( double miu ) { this( 0.0, 1.0 ); }
	
	/**
	 * Creates a location-scale distribution whit the given shift-value <i>miu</i> and scale factor <i>sigma</i>
	 * @param miu Location of the distribution  
	 * @param sigma Scale of the distribution  
	 */
	public LocationScale( double miu, double sigma ) {
		setLocation(miu);
		setScale(sigma);
	}
	
	public void setLocation(double miu) { l = (miu==0.0)?new Zero():new NoZero(miu); }

	public void setScale(double sigma) { p = (sigma==1.0)?new One():new NoOne(sigma); }
	
	/**
	 * Generates random number following the standard distribution, i.e. shift-value <i>miu=0</i> and scale factor <i>sigma=1.0</i>
	 * @return A random number following the standard distribution, i.e. shift-value <i>miu=0</i> and scale factor <i>sigma=1.0</i>
	 */
	public abstract double std();
	
	@Override
	public double next() { return l.apply(p.apply(std())); }
}
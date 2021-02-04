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
 * <p>Title: PowerLaw</p>
 *
 * <p>Description: Generates real numbers following a Generalized Power Law distribution.</p>
 *
 */
public class PowerLaw extends LocationScale{
	/**
	 * Abstract Exponentiation method used by the Power Law distribution (when the <i>coarse alpha</i> is 1.0 is is just a division)
	 * @author Jonatan Gomez
	 *
	 */
	protected interface Exp{ public double apply( double x); }
	
	/**
	 * When the Power Law is using the <i>alpha</i> as 2.0 it is is just a division 
	 * @author Jonatan Gomez
	 *
	 */
	protected class Fast implements Exp{ @Override public double apply(double x){ return 1.0/(1.0-x); }	}
	
	/**
	 * When the Power Law is using the <i>alpha</i> different to 2.0, it is is the pow function 
	 * @author Jonatan Gomez
	 *
	 */
	protected class Pow implements Exp{
		/**
		 * Coarse alpha: Generates real numbers following a Power Law distribution using <i>f(x) = (1-x)<sup>coarse_alpha</sup></i>
		 *  with <i>coarse_alpha=1/(1-alpha)</i> 
		 */
	    protected double coarse_alpha = -1.0;
	    
	    public Pow(double alpha) { coarse_alpha = 1.0/(1.0-alpha); }
		@Override
		public double apply(double x){ return Math.pow(1.0-x, coarse_alpha); }
	}
	
	/**
	 * When scaling factor is different from 1.0, it is not just scaling the standard power law
	 * @author Jonatan Gomez
	 *
	 */
	protected class NoOnePL extends NoOne{
		public NoOnePL(double sigma){ super(sigma); }
		@Override
		public double apply(double x){ return sigma*x - sigma; }		
	}
	
    protected Exp exp; 

    /**
     * Creates a Standard generalized power law <i>alpha=2.0, b=1.0, c=0.0</i>
     */
	public PowerLaw(){ this( 2.0 ); }
	
    /**
     * Creates a generalized power law <i>b=1.0, c=0.0</i>
     * @param alpha <i>alpha</i> parameter of the Generalized power law 
     */
	public PowerLaw( double alpha ){ this( alpha, 1.0 ); }

    /**
     * Creates a generalized power law <i>c=0.0</i>
     * @param alpha <i>alpha</i> parameter of the Generalized power law 
     * @param b <i>b</i> parameter of the Generalized power law 
     */
	public PowerLaw( double alpha, double b ){ this( alpha, b, 0.0 ); }

	/**
     * Creates a generalized power law
     * @param alpha <i>alpha</i> parameter of the Generalized power law 
     * @param b <i>b</i> parameter of the Generalized power law 
     * @param c <i>c</i> parameter of the Generalized power law 
     */
	public PowerLaw( double alpha, double b, double c ){
		super(c);
		b *= (alpha-1.0);
		if( b==1.0 ) p = new One(); else p = new NoOnePL(b);
    	if( alpha==2.0 ) exp = new Fast(); else exp = new Pow(alpha);		
    }

	public void setScale(double b) { 
		b *= (exp instanceof Pow)?(-1.0/((Pow)exp).coarse_alpha):1.0;
		p = (b==1.0)?new One():new NoOnePL(b); 
	}
	
	@Override
	public double std(){ return exp.apply(1.0-raw().next()); }    
}
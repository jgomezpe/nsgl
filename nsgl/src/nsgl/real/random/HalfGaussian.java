package nsgl.real.random;

public class HalfGaussian extends Gaussian{
	/**
	 * Constructor: Creates a Half Gaussian Number Generator |G(0,1)|
	 */
	public HalfGaussian(){  this(1.0);  }

	/**
	 * Constructor: Creates a Half Gaussian Number Generator |G(0,sigma)|
	 * @param sigma standard deviation
	 */
	public HalfGaussian( double sigma ){  this(0.0,sigma);  }

	/**
	 * Constructor: Creates a Half Gaussian Number Generator |G(0,sigma)|
	 * @param miu Zero value
	 * @param sigma standard deviation
	 */
	public HalfGaussian( double miu, double sigma ){  super(miu,sigma);  }

	@Override
	public double std() { return Math.abs(super.std()); }
}
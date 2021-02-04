package nsgl.integer.random;

public class FromRealGenerator implements Random{
	protected nsgl.real.random.Random real;
	
	public FromRealGenerator(nsgl.real.random.Random real) { this.real = real; }

	@Override
	public int next() { return (int)Math.floor(real.next()+0.5); }
}
package nsgl.random;

import nsgl.integer.random.Random;

public class RandomSizePick implements Pick{
	protected Random random;
	
	public RandomSizePick(Random random) { this.random = random; }
	
	@Override
	public int size(){ return random.next(); }
}
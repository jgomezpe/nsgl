package nsgl.bit.variation;

import nsgl.bit.array.Array;
import nsgl.random.Pick;
import nsgl.search.variation.Variation_1_1;

public class IntensityMutation implements Variation_1_1<Array>{

	protected Pick ri;
	
	public IntensityMutation(Pick ri) { this.ri = ri; }
	
	@Override
	public Array apply(Array gen) {
		gen = (Array)gen.copy();
		int[] idx = ri.get(gen.size());
		for( int i=0; i<idx.length; i++ ){ gen.not(idx[i]); }
		return gen;
	}	
}
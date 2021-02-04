package nsgl.bit.space;

import nsgl.bit.array.Array;
import nsgl.random.raw.RawGenerator;

public class VarLength implements nsgl.search.space.Space<Array> {
	protected int minLength;
	protected int maxVarGenes;
	protected int gene_size;
	
	public VarLength( int minLength, int maxLength ){ this(minLength, maxLength, 1); }

	public VarLength( int minLength, int maxLength, int gene_size ){
		this.minLength = minLength;
		this.gene_size = gene_size;
		this.maxVarGenes = (maxLength-minLength)/gene_size;
	}
	
	@Override
	public boolean feasible(Array x) {
		return minLength <= x.size() && x.size()<=minLength+maxVarGenes*gene_size;
	}

	@Override
	public double feasibility(Array x) {
		return feasible(x)?1:0;
	}

	@Override
	public Array repair(Array x) {
		int maxLength = minLength + maxVarGenes * gene_size;
		if( x.size() > maxLength ) return x.subArray(0,maxLength);
		if( x.size() < minLength ){
			Array x2 = new Array(minLength, null);
			try{ for( int i=0; i<minLength;i++)	x2.set(i,x.get(i)); }catch(Exception e){}
			return x2;
		}else return x;
	}
	
	
	@Override
	public Array pick() {
		return (maxVarGenes>0)?new Array(minLength+RawGenerator.cast(this).integer(maxVarGenes*gene_size), null):new Array(minLength, null);
	}
}
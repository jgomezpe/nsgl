package nsgl.real.space;

import nsgl.function.CodeDecodeMap;
import nsgl.real.array.LinearScale;
import nsgl.real.array.Util;

public class ScaledHyperCube extends CodeDecodeMap<double[], double[]> {
    protected LinearScale scale = null;
	
	public ScaledHyperCube( double[] min, double[] max ){
		this.scale = new LinearScale(min, max);
	}
	
	public ScaledHyperCube( HyperCube space ){
		this.scale = new LinearScale(space.min, space.max);
	}
	
	/**
	  * Generates a thing from the given genome
	  * @param genome Genome of the thing to be expressed
	  * @return A thing expressed from the genome
	  */
	public double[] decode(double[] genome) {
		  return scale.inverse(genome); 
	}

	  /**
	   * Generates a genome from the given thing
	   * @param thing A thing expressed from the genome
	   * @return Genome of the thing
	   */
	@Override
	public double[] code(double[] thing) { return scale.apply(thing); }

	
	public static void main( String[] args){
		ScaledHyperCube scale = new ScaledHyperCube(Util.create(5, -10.0), Util.create(5, 10.0));
		for( int i=-10; i<=10; i++){
			double[] x = scale.code(Util.create(5, (double)i));
			for( int k=0; k<x.length; k++ ){ 
				System.out.print(" "+x[k]);
			}
			System.out.println();
		}	
	}	
}

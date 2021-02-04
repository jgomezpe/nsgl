package nsgl.optimize.testbed.real.basic;

import nsgl.optimize.Problem;


import nsgl.optimize.OptimizationFunction;
import nsgl.real.space.HyperCube;
import nsgl.search.space.Space;
import nsgl.real.array.Util;

public class BasicRealsProblem implements Problem<double[]>{
	
	protected BasicFunction f;
	
	protected int D; 
	
	public BasicRealsProblem(String function, int D){
	    ClassLoader classLoader = this.getClass().getClassLoader();
	    try {
	        @SuppressWarnings("rawtypes")
			Class aClass = classLoader.loadClass("nsgl.testbed.optimize.real.basic."+function);
	        System.out.println("aClass.getName() = " + aClass.getName());
	        f = (BasicFunction)aClass.newInstance();
	    } catch (Exception e){ e.printStackTrace(); }
		this.D = D;
	}
	
	@Override
	public OptimizationFunction<double[]> f(){ return f; }

	@Override
	public Space<double[]> space() {
		double[] max = Util.create(D, f.limit());
		double[] min = Util.create(D, -f.limit());
		return new HyperCube(min, max);
	}
}
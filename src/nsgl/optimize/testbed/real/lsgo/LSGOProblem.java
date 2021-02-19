package nsgl.optimize.testbed.real.lsgo;

import nsgl.optimize.OptimizationFunction;
import nsgl.optimize.Problem;
import nsgl.real.space.HyperCube;
import nsgl.search.space.Space;
import nsgl.real.array.Util;

public class LSGOProblem  implements Problem<double[]> {
	protected lsgo.Function f;
	protected OptimizationFunction<double[]> tf;
	
	public LSGOProblem( String function ) {
	    ClassLoader classLoader = this.getClass().getClassLoader();
	    try {
	        @SuppressWarnings("rawtypes")
			Class aClass = classLoader.loadClass("lsgo."+function);
	        System.out.println("aClass.getName() = " + aClass.getName());
	        f = (lsgo.Function)aClass.newInstance();
			tf = new OptimizationFunction<double[]>() {
				@Override
				public java.lang.Double compute(double[] x) { return f.compute(x); }
			};
	    } catch (Exception e){ e.printStackTrace(); }
	}
	
	@Override
	public OptimizationFunction<double[]> f() { return tf; }
	
	public Space<double[]> space(){
		int D = f.getDimension();
		double minL = f.getMin();
		double maxL = f.getMax();
		double[] max = Util.create(D, maxL);
		double[] min = Util.create(D, minL);
		return new HyperCube(min, max);		
	}
}

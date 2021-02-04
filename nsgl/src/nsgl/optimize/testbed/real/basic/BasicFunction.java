package nsgl.optimize.testbed.real.basic;

import nsgl.optimize.OptimizationFunction;

public abstract class BasicFunction extends OptimizationFunction<double[]> {
	public abstract double limit();
}
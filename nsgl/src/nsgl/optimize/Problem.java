package nsgl.optimize;

import nsgl.search.space.Space;

public interface Problem<T> {
	public OptimizationFunction<T> f();
	public Space<T> space();
}
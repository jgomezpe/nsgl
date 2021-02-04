package nsgl.evolve.haea;

import nsgl.object.Describable;
import nsgl.service.descriptor.Descriptor;

public class HaeaStepDescriptor<T> implements Descriptor {
	public double[] features( HaeaStep<T> step ){ return (double[])Describable.cast(step.operators()).features(); }

	@SuppressWarnings("unchecked")
	@Override
	public double[] features(Object obj) { return features((HaeaStep<T>)obj); }
}
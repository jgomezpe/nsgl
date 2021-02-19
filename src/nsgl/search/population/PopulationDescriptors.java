package nsgl.search.population;

import nsgl.service.descriptor.Descriptor;
import nsgl.tagged.Tagged;
import nsgl.real.StatisticsWithMedian;
import nsgl.search.BasicGoalBased;
import nsgl.search.Goal;

public class PopulationDescriptors<T> extends BasicGoalBased<T,Double> implements Descriptor{
	public double[] descriptors( T[] pop ) {
		Goal<T,Double> goal = goal();
		double[] quality = new double[pop.length];
		for(int i=0; i<quality.length; i++ ) quality[i] = goal.apply(pop[i]);
		
		return StatisticsWithMedian.get(quality);		
	}

	public double[] descriptors(Tagged<T>[] pop) {
		Goal<T,Double> goal = goal();
		double[] quality = new double[pop.length];
		for(int i=0; i<quality.length; i++ ) quality[i] = goal.apply(pop[i]);
		return StatisticsWithMedian.get(quality);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public double[] features(Object obj) {
		Class<?> cl = obj.getClass().getComponentType();
		if( Tagged.class.isAssignableFrom(cl)) return descriptors((Tagged<T>[])obj); 
		return descriptors((T[])obj); 
	}
}
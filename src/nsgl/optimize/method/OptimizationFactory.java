package nsgl.optimize.method;

import nsgl.function.iterative.ForLoopCondition;
import nsgl.logic.Predicate;
import nsgl.tagged.Tagged;
import nsgl.optimize.method.annealing.SimpleSimulatedAnnealingScheme;
import nsgl.optimize.method.annealing.SimulatedAnnealingReplacement;
import nsgl.optimize.method.annealing.SimulatedAnnealingScheme;
import nsgl.optimize.method.hillclimbing.HillClimbingReplacement;
import nsgl.search.Goal;
import nsgl.search.local.IterativeLocalSearch;
import nsgl.search.local.LocalSearch;
import nsgl.search.local.VariationReplaceLocalSearch;
import nsgl.search.variation.Variation_1_1;

public class OptimizationFactory<T> {
	public LocalSearch<T,Double> 
		hill_climbing( Variation_1_1<T> variation, HillClimbingReplacement<T> replace, Predicate<Tagged<T>> tC ){
		return new IterativeLocalSearch<T, Double>(new VariationReplaceLocalSearch<T>(variation, replace), tC);
	}

	 public LocalSearch<T,Double> 
		hill_climbing( Goal<T,Double> goal, Variation_1_1<T> variation,	boolean neutral, int MAX_ITERS ){
		 HillClimbingReplacement<T> replacement = new HillClimbingReplacement<T>( neutral );
		 replacement.setGoal(goal);
		 return hill_climbing( variation, replacement, new ForLoopCondition<Tagged<T>>(MAX_ITERS) );
	 }  
	 
	 public LocalSearch<T,Double> 
	 	simulated_annealing( Variation_1_1<T> variation, SimulatedAnnealingReplacement<T> replace, Predicate<Tagged<T>> tC ){
		return new IterativeLocalSearch<T,Double>( 
				new VariationReplaceLocalSearch<T>( variation, replace),  tC );
	 }
	 
	 public LocalSearch<T,Double> 
	 	simulated_annealing( Goal<T,Double> goal, Variation_1_1<T> variation, SimulatedAnnealingScheme scheme, int MAX_ITERS ){
		 SimulatedAnnealingReplacement<T> replacement = new SimulatedAnnealingReplacement<T>(scheme);
		 replacement.setGoal(goal);
    	return simulated_annealing( variation, replacement, 
    								new ForLoopCondition<Tagged<T>>(MAX_ITERS) );
    }       

	public LocalSearch<T,Double> 
	 	simulated_annealing( Goal<T,Double> goal, Variation_1_1<T> variation, int K, int MAX_ITERS ){
		return simulated_annealing( goal, variation, new SimpleSimulatedAnnealingScheme(K), MAX_ITERS );
	}       

    public LocalSearch<T,Double> simulated_annealing( Goal<T,Double> goal, Variation_1_1<T> variation, int MAX_ITERS ){
    	return simulated_annealing( goal, variation, MAX_ITERS, MAX_ITERS );
    }
}
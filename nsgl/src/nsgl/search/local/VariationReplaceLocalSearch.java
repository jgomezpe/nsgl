/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.search.local;

import nsgl.tagged.Tagged;
import nsgl.object.Traceable;
import nsgl.search.Goal;
import nsgl.search.replacement.GoalBasedReplacement;
import nsgl.search.replacement.Replacement;
import nsgl.search.space.Space;
import nsgl.search.variation.Variation_1_1;

/**
 *
 * @author jgomez
 */
public class VariationReplaceLocalSearch<T> extends LocalSearch<T,Double>{
	
	protected Goal<T,Double> goal;
	
	protected Variation_1_1<T> variation;
    protected Replacement<T> replace;
    
	@SuppressWarnings("unchecked")
	public VariationReplaceLocalSearch( Variation_1_1<T> variation, Replacement<T> replace ){
        super();
        this.variation = variation;
        this.replace = replace;
        if( replace!=null && replace instanceof GoalBasedReplacement ) this.goal = ((GoalBasedReplacement<T,Double>)replace).goal();  
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void setGoal(Goal<T,Double> goal){
    	if( replace instanceof GoalBasedReplacement ) ((GoalBasedReplacement<T,Double>)replace).setGoal(goal);
    	this.goal = goal;
    }
        
    @Override 
	public Goal<T,Double> goal(){ return goal; }
    
    @Override
    public Tagged<T> apply(Tagged<T> x, Space<T> space){
        // Check if non stationary
		Tagged<T> y = replace.apply(x,variation.apply(space, x));
		Traceable.cast(this).trace(x, PathTracer.PARENT, y);
        return y;
    }
}
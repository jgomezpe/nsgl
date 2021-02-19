package nsgl.search.local;

import nsgl.tagged.Tagged;
import nsgl.object.Traceable;
import nsgl.search.Goal;
import nsgl.search.replacement.GoalBasedReplacement;
import nsgl.search.space.Space;
import nsgl.search.variation.Variation_1_1;

public class AdaptOperatorLocalSearch<T,P> extends VariationReplaceLocalSearch<T>{
    protected AdaptSearchOperatorParameters<P> adapt;
    
    
    public AdaptOperatorLocalSearch( Variation_1_1<T> variation,
    								 AdaptSearchOperatorParameters<P> adapt, 
    								 GoalBasedReplacement<T,Double> replace ){
        super( variation, replace );
        this.adapt = adapt;
    }
    
    @Override
    public Tagged<T> apply(Tagged<T> x, Space<T> space){
		Goal<T,Double> goal = goal();
		Tagged<T> y = variation.apply(space, x);
        if( adapt != null )	adapt.apply(variation, goal.apply(x), goal.apply(y));
        Tagged<T> z = replace.apply(x, y);
        Traceable.cast(this).trace(x, z);
        return z;
    }    
}
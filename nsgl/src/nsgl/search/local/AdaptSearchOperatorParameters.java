package nsgl.search.local;

import nsgl.search.variation.ParameterizedObject;
import nsgl.search.variation.Variation;

public interface AdaptSearchOperatorParameters<P> {
	public P apply( P parameters, double current, double next );
	public default void apply( Variation<?> operator, double current, double next ){
		if( operator instanceof ParameterizedObject ){
			@SuppressWarnings("unchecked")
			ParameterizedObject<P> pobject = (ParameterizedObject<P>)operator;
			pobject.setParameters(apply(pobject.getParameters(), current, next));
		}
	}
}

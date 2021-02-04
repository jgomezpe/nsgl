package nsgl.search;

import nsgl.metric.Distance;
import nsgl.metric.DistanceOrder;
import nsgl.order.Order;

public abstract class DistanceToGoal<T,R> extends Goal<T,R>{
    protected Order order;
    
    public DistanceToGoal( R goal_value, Distance<R> distance ){
    	order = new DistanceOrder<R>(distance, goal_value);
    }   
    
    @Override
    public Order order(){ return order; }    
}

package nsgl.graph.search;
import nsgl.array.Array;
import nsgl.graph.OptionCost;
import nsgl.graph.GraphSpace;
import nsgl.graph.PathUtil;
import nsgl.tagged.Tagged;
import nsgl.search.Goal;
import nsgl.search.local.LocalSearch;
import nsgl.search.space.Space;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gómez
 * @version 1.0
 */
public abstract class GraphSearch<T,O> extends LocalSearch<T,Boolean>{
	protected Goal<T,Boolean> goal;
	@Override
	public Goal<T, Boolean> goal() { return goal; }
	
	@Override
	public void setGoal(Goal<T, Boolean> goal) { this.goal = goal; }

	public abstract Array<O> apply( T initial, GraphSpace<T,O> space, Goal<T,Boolean> goal, OptionCost<T,O> cost  );
  
	public Tagged<T> apply( Tagged<T> x, Space<T> space ){
		@SuppressWarnings("unchecked")
		GraphSpace<T,O> g_space = (GraphSpace<T,O>)space;
		Goal<T,Boolean> goal = goal();
		OptionCost<T,O> cost = g_space.cost();
		Array<O> action = apply( x.unwrap(), g_space, goal, cost);
		PathUtil<T,O> path = new PathUtil<T,O>();
		T y = path.final_state(x.unwrap(), g_space, action);
		Tagged<T> sol = new Tagged<T>(y);
		sol.setTag(PathUtil.class.getName(), action);
		return sol;
	}
}
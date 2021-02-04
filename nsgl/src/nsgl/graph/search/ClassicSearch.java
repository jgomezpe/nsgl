package nsgl.graph.search;

import nsgl.array.Vector;
import nsgl.graph.*;
import nsgl.object.Copyable;
import nsgl.search.Goal;

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
public abstract class ClassicSearch<T,O> extends GraphSearch<T,O> {
	protected Vector<ClassicSearchNode<T,O>> list;
	protected int max_depth;
	public ClassicSearch( int _max_depth ) {
		max_depth = _max_depth;
		list = new Vector<ClassicSearchNode<T,O>>();
	}

	public abstract void add( ClassicSearchNode<T,O> child );

	public double evaluate( T state, O option, double actual_cost, OptionCost<T,O> option_cost ){
		return actual_cost + option_cost.evaluate(state, option);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vector<O> apply( T initial, GraphSpace<T,O> space, Goal<T,Boolean> goal, OptionCost<T,O> cost ){
		list.clear();
		ClassicSearchNode<T,O> node = new ClassicSearchNode<T,O>( new Vector<O>(), 0.0 );
		list.add(node);
		T state = initial;
		while( node != null && !goal.apply(state) ){
			list.remove(0);
			if( node.path.size() < max_depth ){
				Vector<O> actions = space.succesor(state);
				for(O action : actions) {
					T child_state = space.succesor(state, action);
					if( space.feasible(child_state) ){
						double path_cost = evaluate(state, action, node.cost, cost);
						Vector<O> path;
						path = (Vector<O>)Copyable.cast(node.path).copy();
						path.add(action);
						ClassicSearchNode<T,O> child_node = new ClassicSearchNode<T,O>( path, path_cost);
						add(child_node);
					}
				}
			}
			try{ 
				node = list.get(0);
				state = node.state(space, initial);
			}catch( Exception e ){ node=null; }
		}
		if( node != null ) return node.path;
		return null;
	}
}
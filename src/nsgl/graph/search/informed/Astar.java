package nsgl.graph.search.informed;

import nsgl.graph.OptionCost;
import nsgl.graph.search.uninformed.UniformCostSearch;

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
public class Astar<T,O> extends UniformCostSearch<T,O>{
  protected Heuristic<T,O> heuristic;
  public Astar( int _max_depth, Heuristic<T,O> _heuristic ) {
    super( _max_depth );
    heuristic = _heuristic;
  }

  public double evaluate( T state, O action, double actual_cost,
                          OptionCost<T,O> action_cost ){
    return actual_cost + action_cost.evaluate(state, action) +
           heuristic.evaluate(state,action);
  }

}

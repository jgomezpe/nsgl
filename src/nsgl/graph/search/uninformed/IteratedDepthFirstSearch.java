package nsgl.graph.search.uninformed;

import nsgl.array.Array;
import nsgl.graph.OptionCost;
import nsgl.graph.GraphSpace;
import nsgl.graph.search.ClassicSearch;
import nsgl.graph.search.ClassicSearchNode;
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
public class IteratedDepthFirstSearch<T,O> extends ClassicSearch<T,O>{
  protected DepthFirstSearch<T,O> depth_search = null;
  public IteratedDepthFirstSearch( int _max_depth ) {
    super( _max_depth );
  }

  public void add( ClassicSearchNode<T,O> child ){}

  public Array<O> apply( T initial, GraphSpace<T,O> space, Goal<T,Boolean> goal, OptionCost<T,O> cost ){
    Array<O> path = null;
    int depth = 0;
    while( path != null && depth < max_depth ){
      depth++;
      depth_search = new DepthFirstSearch<T,O>(depth);
      path = depth_search.apply(initial, space, goal, cost );
    }
    return path;
  }
}

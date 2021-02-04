package nsgl.graph;
import nsgl.array.Vector;

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
public class PathUtil<T,O>{
  public double evaluate( T state, GraphSpace<T,O> space, Vector<O> path, OptionCost<T,O> cost ){
    double c = 0.0;
    for(O action : path){
      c += cost.evaluate( state, action);
      state = space.succesor( state, action );
    }
    return c;
  }
  
  public T final_state( T initial, GraphSpace<T,O> space, Vector<O> path ){
      for(O action : path){
        initial = space.succesor( initial, action );
      }
      return initial;
  }
}

package nsgl.graph.search.uninformed;

import nsgl.graph.search.ClassicSearch;
import nsgl.graph.search.ClassicSearchNode;

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
public class BreadthFirstSearch<T,O> extends ClassicSearch<T,O> {
  public BreadthFirstSearch( int _max_depth ) {
    super( _max_depth );
  }

  public void add( ClassicSearchNode<T,O> child ){
    list.add(child);
  }
}

package nsgl.graph.search.uninformed;

import nsgl.array.Sorted;
import nsgl.graph.search.ClassicSearch;
import nsgl.graph.search.ClassicSearchNode;
import nsgl.graph.search.ClassicSearchNodeOrder;

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
public class UniformCostSearch<T,O> extends ClassicSearch<T,O> {
  public UniformCostSearch( int _max_depth ) {
    super( _max_depth );
    list = new Sorted<ClassicSearchNode<T,O>>( new ClassicSearchNodeOrder<T,O>() );
  }


  public void add( ClassicSearchNode<T,O> child ){
     list.add(child);
  }

}

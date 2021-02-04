package nsgl.graph.search;

import nsgl.order.Order;

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
public class ClassicSearchNodeOrder<T,O> implements Order{
	public String getCanonicalName(){
		return ClassicSearchNode.class.getCanonicalName();
	}

	public ClassicSearchNodeOrder() {}

	public int compare( ClassicSearchNode<T,O> one, ClassicSearchNode<T,O> two ){ return (int)(one.cost - two.cost); }

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Object one, Object two){ return compare((ClassicSearchNode<T,O>)one, (ClassicSearchNode<T,O>)two); }
}

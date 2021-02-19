package nsgl.search.selection;

import nsgl.array.Sorted;
import nsgl.pair.Pair;
import nsgl.pair.PairBOrder;
import nsgl.order.Order;
import nsgl.order.Reversed;
import nsgl.search.Goal;

public class Ranking<T,R> extends GoalBasedSelection<T,R> {
	public Ranking( Goal<T,R> goal ){ super(goal); }
	
	@Override
	public int[] apply(int n, R[] x) {
		Order order = goal().order();
		int s = x.length;
		Sorted<Pair<Integer,R>> indexq = new Sorted<Pair<Integer,R>>( 
				new Reversed( new PairBOrder<Integer,R>(order) ) );
		for( int i=0; i<s; i++ ) indexq.add(new Pair<Integer,R>(i, x[i] ) );
		nsgl.integer.random.Roulette roulette = new nsgl.integer.random.Roulette(n);
		int[] sel = roulette.generate(n);
		try{ for( int i=0; i<sel.length; i++ ) sel[i] = indexq.get(i).a(); }catch(Exception e){}
		return sel;
	}

	@Override
	public int choose_one(R[] x) {
		return apply(1,x)[0];
	}
}
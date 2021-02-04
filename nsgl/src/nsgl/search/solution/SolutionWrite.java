package nsgl.search.solution;

import java.io.IOException;
import java.io.Writer;

import nsgl.service.io.Write;
import nsgl.tagged.Tagged;
import nsgl.object.Writable;
import nsgl.search.Goal;

public class SolutionWrite<T> implements Write {
	protected Goal<T,Double> goal;
	protected boolean write_object;
	
	public SolutionWrite( Goal<T,Double> goal, boolean write_object ) {
		this.goal = goal;
		this.write_object = write_object;
	}
	
	public void write(Tagged<T> sol, Writer out) throws IOException {
		out.write(""+goal.apply(sol));
		if( write_object ){
			out.write(' ');
			Writable w = Writable.cast(sol.unwrap());
			if( w != null ) w.write(out);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void write(Object sol, Writer out) throws IOException{ write( (Tagged<T>)sol, out );	}
	
	@Override
	public String toString(){ return "SolutionWrite"; }
}
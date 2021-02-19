package nsgl.agents;

import nsgl.array.Array;
import nsgl.object.Searchable;

/**
 * <p>Title: Kernel </p>
 *
 * <p>Description: A multi agent problem solution kernel</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Kernel implements Runnable{
	/**
	 * Stop/Go checking flag
	 */
	public boolean flag = true;
	/**
	 * Collection of agents in the problem
	 */
	protected Array<Agent> agents;

	public Kernel( Agent agent ){
		agents = new Array<Agent>();
		agents.add(agent);
	}

	public Kernel( Array<Agent> _agents ) { agents = _agents; }

	public Agent getAgent( int index ){ try{ return agents.get(index); }catch(Exception e){ return null; } }

	public Agent getAgent(){ return getAgent(0); }

	public boolean addAgent( Agent agent ){
		boolean cflag = !Searchable.cast(agents).contains(agent);
		if( cflag ) agents.add(agent);
		return cflag;
	}

	public boolean delAgent( Agent agent ){
	    Integer i = (Integer)Searchable.cast(agents).find(agent);
	    if( i!=null ) return agents.remove(i);
	    return false;
	}

	public void stop(){ 
		flag = false;
		for( Agent a:agents) a.die();
	}

	public void run(){
		flag = true;
		for( Agent a:agents ){
			a.live();
			Thread t = new Thread(a);
			a.setThread(t);
			t.start();
			if(!flag) return;
		}
	}
}
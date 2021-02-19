package nsgl.integer;

import java.util.Iterator;

import nsgl.object.Searchable;

public class Interval implements Iterable<Integer>, Searchable{
	protected int start = 0;
	protected int end = 0;
	public Interval( int end ) { this.end = end;	}
	public Interval( int start, int end ) {
		this.start = start;
		this.end = end;	
	}
	
	public int inf() { return start; }
	
	public int sup(){ return end; }

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			protected int pos = start;
			@Override
			public boolean hasNext(){ return pos < end; }

			@Override
			public Integer next() {	return pos++; }
		};
	}
	
	public int size() { return end-start; }
	
	public Object find(Integer pos) { return (start<=pos && pos<end)?pos:null; }

	@Override
	public Object find(Object pos) {
	    if(pos instanceof Integer) return find((Integer)pos);
	    return null; 
	}
}
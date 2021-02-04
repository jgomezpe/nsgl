package nsgl.integer;

import java.util.Iterator;

import nsgl.collection.Collection;

public class Interval implements Collection<Integer>{
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
	
	@Override
	public int size() { return end-start; }
	
	@Override
	public boolean contains(Integer pos) { return start<=pos && pos<end; }
}
package nsgl.service.io;

import nsgl.array.SortedSearch;
import nsgl.array.Vector;
import nsgl.integer.L2HOrder;
import nsgl.object.Named;

public class Source extends Named{
    protected String input;
    protected Vector<Integer> rows = new Vector<Integer>();
    protected SortedSearch search = new SortedSearch(new L2HOrder());
    public Source(String input) {
	this("noname", input);
    }
   
    public Source(String id, String input) {
	super(id);
	this.input = input;
	search.set(rows);
	rows.add(0);
	for(int i=0;i<input.length(); i++) {
	    if(input.charAt(i)=='\n') rows.add(i+1);
	}
    }
   
    public int[] pos(int index) {
	int idx = search.findLeft(index);
	if(idx+1<rows.size() && rows.get(idx+1)==index) return new int[] {idx+1,0};
	return new int[] {idx, index-rows.get(idx)};
    }
    
    public char get(int index) { return input.charAt(index); }
    
    public String substring(int start, int end) { return input.substring(start,end); }
}
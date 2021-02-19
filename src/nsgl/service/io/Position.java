package nsgl.service.io;

import nsgl.json.Castable;
import nsgl.json.JSON;

public class Position implements Castable{
    public static final String INPUT = "input";
    public static final String START = "start";
    public static final String ROW = "row";
    public static final String COLUMN = "column";
    
    protected Source input;
    protected int start;
    public Position(Source input, int start){
	this.input = input;
	this.start = start;	
    }
    
    public void start(int start) { this.start = start; }
    public int start() { return start; }
    
    public void shift(int delta) {
	start+=delta;
    }

    public void input(Source input) { this.input = input; }  
    public Source input(){ return this.input; }

    @Override
    public void config(JSON json) {}

    @Override
    public JSON json() {
	JSON json = new JSON();
	json.set(INPUT, input.id());
	json.set(START, start);
	int[] pos = input.pos(start);
	json.set(ROW, pos[0]);
	json.set(COLUMN, pos[1]);	
	return json;
    }

}
package nsgl.service.io;

import nsgl.json.JSON;

public class Token extends Position{    
    public static final String ERROR = "error";
    public static final String TYPE = "type";
    public static final String END = "end";
    public static final String VALUE = "value";

    protected String type;
    protected int end;
    protected Object value;
    
    public Token(Source input, int start, int end, Object value){
	this(ERROR, input, start, end, value);
    }

    public Token(String type, Source input, int start, int end, Object value){
	super(input,start);
    	this.type = type;
	this.end = end;
	this.value = value;
    }
	
    public int size(){ return this.end-this.start; }
    
    public int end() { return end; }
    
    public void shift(int delta) {
	start+=delta;
	end+=delta;
    }

    @Override
    public JSON json() {
	JSON json = super.json();
	json.set(END, end);
	json.set(VALUE,value.toString());
	json.set(TYPE,type);
	return json;
    }
    
    public Object value() { return this.value; }
    
    public String type() { return type; }
    
    public void type( String type ) { this.type = type; }
    public void value( Object value ) { this.value = value; }
    
    public String toString() {
	return "["+type+','+start+','+end+','+value+']';
    }
    
    public Token toError() { return new Token(input,start,end,type()); }
    
    public boolean isError() { return type()==ERROR; }
}
package nsgl.json.language;

import nsgl.language.lookahead.Rule;

public class Parser extends nsgl.language.lookahead.Parser{
    protected static Rule[] rules() { return new Rule[] {
	    new Obj(null),
	    new List(null),
	    new Value(null),
	    new Attribute(null)
    }; }
    
    public Parser(){
	super(rules(), Obj.TAG);
    }
	
}
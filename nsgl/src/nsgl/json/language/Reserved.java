package nsgl.json.language;

import nsgl.language.lexeme.ID;
import nsgl.service.io.Source;
import nsgl.service.io.Token;

public class Reserved extends ID<Object>{
    public static final String TAG = "reserved";

    @Override
    public String type() { return TAG; }

    public Reserved(){}	
	
    @Override
    public Token match(Source input, int start, int end) {
	Token t = super.match(input, start, end);
	String v = (String)t.value();
	switch(v) {
	case "true":
	    t.value(true);
	    return t;
	case "false":
	    t.value(false);
	    return t;
	case "null":
	    t.value(null);
	    return t;
	default:
	    t.type(Token.ERROR);
	    t.value(type());
	    return t;
	}
    }

    @Override
    public boolean startsWith(char c) { return c=='t' || c=='f' || c=='n'; }
}
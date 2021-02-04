package nsgl.json.language;


import nsgl.array.Vector;
import nsgl.json.JSON;
import nsgl.pair.Pair;
import nsgl.service.io.Token;

public class Meaner implements nsgl.language.Meaner{
    public static final String TAG = "JSON";
    public Meaner() { }
		
    @Override
    public Token apply(Token obj){
	if( obj.isError() ) return obj;
	return new Token(TAG, obj.input(), obj.start(), obj.end(), inner_apply(obj));
    }

    public Object inner_apply(Token obj){
	switch( obj.type() ) {
	case Obj.TAG:
	    JSON json = new JSON();
	    @SuppressWarnings("unchecked") 
	    Vector<Token> attr = (Vector<Token>)obj.value();
	    for(Token a:attr) {
		@SuppressWarnings("unchecked")
		Pair<String,Object> p = (Pair<String,Object>)inner_apply(a);
		json.set(p);
	    }
	    return json;
	case Attribute.TAG:
	    Token[] pair = (Token[])obj.value();
	    Object value = inner_apply(pair[1]);
	    return new Pair<String,Object>((String)pair[0].value(), value);
	case List.TAG:
	    Vector<Object> a = new Vector<Object>();
	    @SuppressWarnings("unchecked") 
	    Vector<Token> l = (Vector<Token>)obj.value();
	    for(Token x:l) {
    		Object y = inner_apply(x);
    		a.add(y);
	    }
	    Object[] b = new Object[a.size()];
	    for(int i=0; i<b.length; i++) b[i] = a.get(i); 
	    return b;
	default:
	    return obj.value();
	}
    }
}
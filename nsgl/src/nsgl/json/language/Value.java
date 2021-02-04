package nsgl.json.language;

import nsgl.language.lexeme.Symbol;
import nsgl.language.lookahead.Parser;
import nsgl.language.lookahead.Rule;
import nsgl.service.io.Token;

public class Value extends Rule{
    public final static String TAG = "VALUE"; 
    public Value(Parser parser) { super(TAG, parser); }
    
    @Override
    public boolean startsWith(Token t) {
	if(t.type().equals(Token.ERROR)) return false;
	if(t.type().equals(Symbol.TAG)) {
	    char c = (char)t.value();
	    return c=='[' || c== '{';
	}
	return true; 
    }
    
    @Override
    public Token analize(nsgl.language.Lexer lexer, Token current) {
	if(current.type()==Symbol.TAG) {
	    char c = (char)current.value();
	    switch(c) {
	    case '[': return parser.rule(List.TAG).analize(lexer, current);
	    case '{': return parser.rule(Obj.TAG).analize(lexer, current);
	    default: return current.toError();
	    }
	}
	return current;
    }
}
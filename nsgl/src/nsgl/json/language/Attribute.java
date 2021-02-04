package nsgl.json.language;

import nsgl.language.lookahead.Parser;
import nsgl.language.lookahead.Rule;
import nsgl.service.io.Source;
import nsgl.service.io.Token;

public class Attribute extends Rule{
    public final static String TAG = "ATTRIBUTE"; 
    public Attribute(Parser parser) { super(TAG, parser); }
    
    @Override
    public boolean startsWith(Token t) {
	return t.type().equals(nsgl.string.Parser.TAG);
    }
    
    @Override
    public Token analize(nsgl.language.Lexer lexer, Token current) {
	if(!startsWith(current)) return current.toError();
	Source input = current.input();
	int start = current.start();
	int end = current.end();
	Token[] pair = new Token[2];
	pair[0] = current;
	current = lexer.next();
	if(current==null) return eof(input,end);
	if(!check_symbol(current, ':')) return current.toError();
	end = current.end();
	pair[1] = parser.analize(Value.TAG,lexer);
	if(pair[1].isError()) return pair[1];
	return token(input,start,pair[1].end(),pair);
    }
}

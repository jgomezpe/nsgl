package nsgl.language;

import nsgl.array.Vector;
import nsgl.service.io.Token;

public class LexerWrap extends Lexer{
    protected Vector<Token> tokens;
    protected int start;
    protected int end;
    
    public LexerWrap(Vector<Token> tokens) {
	this(tokens,0,tokens.size());
    }
    
    public LexerWrap(Vector<Token> tokens, int start) {
	this(tokens,start,tokens.size());
    }
    
    public LexerWrap(Vector<Token> tokens, int start, int end) {
	this.tokens = tokens;
	this.start = start;
	this.end = end;
    }
     
    @Override
    protected Token get() {
	if(start>=end) return null;
	current = tokens.get(start++);
	return current;
    }

}
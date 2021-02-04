package nsgl.language.lookahead;

import nsgl.array.Vector;
import nsgl.hash.HashMap;
import nsgl.language.lexeme.Lexeme;
import nsgl.service.io.Token;

public class Lexer extends nsgl.language.Lexer{
    protected HashMap<String, Lexeme<?>> lexeme = new HashMap<String, Lexeme<?>>();
    protected HashMap<String, Integer> priority = new HashMap<String, Integer>();
      
    public Lexer( Lexeme<?>[] lexemes, String[] removableTokens ){
	this(lexemes, new int[lexemes.length], removableTokens);
    }

    public Lexer( Lexeme<?>[] lexemes ){
	this(lexemes, new int[lexemes.length]);
    }

    public Lexer( Lexeme<?>[] lexemes, int[] priority){
	this(lexemes,priority,new String[] {});
    }

    public Lexer( Lexeme<?>[] lexemes, int[] priority, String[] removableTokens ){
	super(removableTokens);
	for( int i=0; i<lexemes.length; i++ ) {
	    this.lexeme.set(lexemes[i].type(), lexemes[i]);
	    this.priority.set(lexemes[i].type(), priority[i]);
	}
    }
    
    @Override
    protected Token get() {
	if(start>=end) return null;
	char c = input.get(start);
	Vector<Token> opt = new Vector<Token>();
	Vector<Token> error = new Vector<Token>();
	for( Lexeme<?> l:lexeme ) {
	    if(l.startsWith(c)) {
		Token t = l.match(input, start, end);
		if(t.isError()) error.add(t);
		else opt.add(t);
	    }
	}
	if( opt.size() > 0 ) {
	    current = opt.get(0);
	    for( int i=1; i<opt.size(); i++ ) {
		Token e2 = opt.get(i);
		if(e2.size()>current.size() || 
		(e2.size()==current.size()&& priority.get(e2.type())>priority.get(current.type()))) 
		    current = e2;
	    }
	}else {
	    if(error.size()>0) {
		current = error.get(0);
		for( int i=1; i<error.size(); i++ ) {
		    Token e2 = error.get(i);
		    if(e2.size()>current.size()) current = e2;
		}
	    }else {
		current = new Token(input,start, start+1,c);
	    }
	}
	start = current.end();
	return current;
    }
}
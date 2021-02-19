package nsgl.language.lookahead;

import nsgl.language.lexeme.Symbol;
import nsgl.service.io.Source;
import nsgl.service.io.Token;

public abstract class Rule{	
    protected Parser parser;
    protected String type;
    
    public Rule(String type, Parser parser) { 
	this.parser = parser; 
	this.type = type;
    }
    
    public abstract boolean startsWith( Token t );

    public String type() { return type; }

    public boolean check_symbol(Token token, char c) {
	return check_symbol(token,c,Symbol.TAG);
    }

    public boolean check_symbol(Token token, char c, String TAG) {
	return token.type()==TAG && ((char)token.value()) == c;
    }
    
    public Token analize(nsgl.language.Lexer lexer) {
	return analize(lexer, lexer.next());
    }
    
    public abstract Token analize(nsgl.language.Lexer lexer, Token current);
    
    public Token eof(Source input, int end) {
	return new Token(input,end,end,type());
    }
        
    public Token token(Source input, int start, int end, Object value) {
	return new Token(type(), input, start, end, value);
    }
}
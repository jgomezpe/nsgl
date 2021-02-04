package nsgl.language.lookahead;

import nsgl.service.io.Token;

public class Empty extends Rule{

    public Empty(String type, Parser parser) {
	super(type, parser);
    }

    @Override
    public boolean startsWith(Token t) { return true; }

    @Override
    public Token analize(nsgl.language.Lexer lexer, Token current) {
	return new Token(type(), current.input(), current.start(), current.end(), null);
    }

}

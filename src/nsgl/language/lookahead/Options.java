package nsgl.language.lookahead;

import nsgl.service.io.Token;

public class Options extends Rule{
    protected String[] option;
    protected String type;
    
    public Options(String type, Parser parser, String[] options) {
	super(type, parser);
	this.option = options;
    }

    @Override
    public boolean startsWith(Token t) {
	int i=0;
	while(i<option.length && !parser.rule(option[i]).startsWith(t)) i++;
	return i<option.length;
    }

    @Override
    public Token analize(nsgl.language.Lexer lexer, Token current){
	for(String r:option) {
	    Rule rule = parser.rule(r);
	    if(rule.startsWith(current))
		return rule.analize(lexer, current);
	}    
	return current.toError();
    }
}
package nsgl.language.lookahead;

import nsgl.hash.HashMap;
import nsgl.service.io.Token;

public class Parser implements nsgl.language.SyntacticParser{
    protected HashMap<String, Rule> rule = new HashMap<String, Rule>();
    protected String main;
    
    public Parser(Rule[] rules, String main) {
	this.main = main;
	for(Rule r:rules) {
	    rule.set(r.type(),r);
	    r.parser = this;
	}
	    
    }

    public Rule rule(String rule) { return this.rule.get(rule); }

    public String main(){ return main; }
    public void main(String rule) { this.main = rule; }
	
    public Token analize(String rule, nsgl.language.Lexer lexer) {
	Rule r = this.rule(rule);
	return r.analize(lexer);
    }

    @Override
    public Token analize(nsgl.language.Lexer lexer) {
	return analize(main,lexer);
    }
}

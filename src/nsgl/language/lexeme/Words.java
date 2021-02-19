package nsgl.language.lexeme;

import nsgl.service.io.Source;
import nsgl.service.io.Token;

public class Words implements Lexeme<String>{
    protected String[] word;
    protected String type;
    
    public Words(String type, String[] word) {
	this.word = word;
	this.type = type;
    }
    @Override
    public Token match(Source input, int start, int end) {
	for( String w:word) {
	    String x = input.substring(start,Math.min(end, start+w.length()));
	    if(w.equals(x)) return token(input,start,start+x.length(),x);
	}
	return error(input,start,start+1);
    }

    @Override
    public boolean startsWith(char c) {
	for( String w:word) if(w.charAt(0)==c) return true;
	return false;
    }

    @Override
    public String type() {
	return type;
    }

}

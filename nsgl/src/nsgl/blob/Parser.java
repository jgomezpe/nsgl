package nsgl.blob;

import java.util.Base64;
import java.util.Base64.Decoder;

import nsgl.language.lexeme.Lexeme;
import nsgl.service.io.Source;
import nsgl.service.io.Token;

public class Parser implements Lexeme<byte[]>{ 	
    public static final String TAG = "byte[]";
    protected boolean useStarter = false;
    protected int length;

	public Parser() { this(false); } 
	
	public Parser(boolean useStarter ) { this.useStarter = useStarter; }

	public boolean valid(char c) {
	    return Character.isLetterOrDigit(c)||c=='+'||c=='-';
	}
	
	@Override
	public String type() { return TAG; }

	@Override
	public Token match(Source input, int start, int end){
	    if(!startsWith(input.get(start)))
		return error(input,start,start+1);
	    int n=end;
	    end=start+1;
	    while(end<n && valid(input.get(end))) end++;
	    int s = (useStarter)?start+1:start;
	    int m = (end-s)%4;
	    if(s==end || m==1) return error(input,start,end);
	    if(m>0) {
		while(end<n && m<4 && input.get(end)=='=') {
		    end++;
		    m++;
		}
		if(m<4) return error(input,start,end);
	    }
	    Decoder dec = Base64.getMimeDecoder();
	    Object obj = dec.decode(input.substring(s,end));
	    return token(input,start,end,obj);
	}

	@Override
	public boolean startsWith(char c) {
	    return useStarter?(c==Stringifier.STARTER):valid(c);
	}
}
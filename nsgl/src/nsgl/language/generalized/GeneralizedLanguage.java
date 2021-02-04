package nsgl.language.generalized;

import java.io.IOException;

import nsgl.language.Language;
import nsgl.language.Lexer;
import nsgl.language.Meaner;
import nsgl.language.SyntacticParser;
import nsgl.service.io.Token;

public class GeneralizedLanguage<T,S> extends Language<T>{
	protected Encoder<S> encoder;
	public GeneralizedLanguage(Encoder<S> encoder, Lexer lexer, SyntacticParser parser, Meaner meaner) {
		super(lexer, parser, meaner);
		this.encoder = encoder;
	}
	
	public Token match( Iterable<S> reader ) throws IOException{
		StringBuilder sb = new StringBuilder();
		for(S c:reader) sb.append(encoder.encode(c));
		String s = sb.toString();
		return match( s, 0, s.length() ); 
	}
}
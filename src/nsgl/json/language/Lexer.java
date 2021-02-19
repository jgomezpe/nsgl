package nsgl.json.language;

import nsgl.language.lexeme.Lexeme;
import nsgl.language.lexeme.Space;
import nsgl.language.lexeme.Symbol;

public class Lexer extends nsgl.language.lookahead.Lexer{
	public static Lexeme<?>[] lexemes = new Lexeme[] {
		new nsgl.number.Parser(),
		new nsgl.string.Parser(),
		new nsgl.blob.Parser(true),
		new Reserved(),
		new Symbol("[]{},:"),
		new Space()
	};
	
	public Lexer() { super(lexemes, new String[] {Space.TAG}); }
}
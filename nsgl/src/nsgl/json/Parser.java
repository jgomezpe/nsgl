package nsgl.json;

import nsgl.language.Language;
import nsgl.json.language.Lexer;
import nsgl.json.language.Meaner;

public class Parser extends Language<JSON>{
    public Parser() {
	super(new Lexer(), new nsgl.json.language.Parser(), new Meaner());
    }
}
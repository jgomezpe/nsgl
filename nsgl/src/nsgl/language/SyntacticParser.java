package nsgl.language;

import nsgl.service.io.Token;

public interface SyntacticParser{
    Token analize(Lexer lexer);
}
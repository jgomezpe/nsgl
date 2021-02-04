package nsgl.json.language;

import nsgl.language.lookahead.Parser;

public class Obj extends nsgl.language.lookahead.List{
    public final static String TAG = "OBJ"; 
    public Obj(Parser parser) { super(TAG, parser, Attribute.TAG, '{', '}', ','); }
}
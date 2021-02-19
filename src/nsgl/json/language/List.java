package nsgl.json.language;

public class List extends nsgl.language.lookahead.List{
    public final static String TAG = "LIST"; 
    public List(Parser parser) { super(TAG, parser, Value.TAG); }
}

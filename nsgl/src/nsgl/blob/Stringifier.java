package nsgl.blob;

import java.util.Base64;
import java.util.Base64.Encoder;

public class Stringifier implements nsgl.stringify.Stringifier{
    public static final char STARTER = '#';
    protected boolean useStarter = false;
    public Stringifier() { this(false); }
    
    public Stringifier(boolean useStarter) { this.useStarter = useStarter; }
    
    public String stringify(byte[] blob) {
	Encoder enc = Base64.getMimeEncoder();
	String txt = enc.encodeToString(blob);
	StringBuilder sb = new StringBuilder(); 
	if(useStarter) sb.append(STARTER);
	sb.append(txt);
	return sb.toString();
    }

    @Override
    public String stringify(Object obj) { return stringify((byte[])obj); }
}
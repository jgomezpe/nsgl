package nsgl.string;

public class SubString  implements CharSequence{
    protected String string;
    protected int start;
    protected int end;
    
    public SubString(String string, int start) {
	this(string,start,string.length());
    }

    public SubString(String string, int start, int end) {
	this.start = start;
	this.end = end;
	this.string = string;
    }

    @Override
    public int length() {
	// TODO Auto-generated method stub
	return string.length()-start;
    }

    @Override
    public char charAt(int index) {
	// TODO Auto-generated method stub
	return string.charAt(start+index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
	return string.substring(this.start+start,this.start+Math.min(end, this.end));
    }
}

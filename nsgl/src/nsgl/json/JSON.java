package nsgl.json;

import java.io.IOException;

import nsgl.hash.HashMap;
import nsgl.object.Copyable;
import nsgl.stringify.Stringifyable;

public class JSON extends HashMap<String, Object> implements Copyable, Castable{	
	public JSON(){}
	
	@Override
	public Object copy(){ 
	    JSON json = new JSON();
	    json.config(this);
	    return json; 
	}
	
	public double real( String tag ){
		try{
			Object obj = get(tag);
			if( obj instanceof Double ) return (Double)obj;
			if( obj instanceof Integer ) return (Integer)obj;
		}catch(Exception e){}
		return 0;
	}
	
	public int integer( String tag ){ try{ return (Integer)get(tag); }catch(Exception e){ return 0; } } 
	
	public boolean bool( String tag ){ try{ return (Boolean)get(tag); }catch(Exception e){ return false; } }

	public byte[] blob( String tag ){ try{ return (byte[])get(tag);  }catch(Exception e){ return null; } }

	public String string( String tag ){ try{ return (String)get(tag); }catch(Exception e){ return null; } }

	public Object[] array( String tag ){ try{ return (Object[])get(tag); }catch(Exception e){ return null; } }

	public int[] integers_array( String tag ){ 
		Object[] a = array(tag);
		int[] x = null;
		if( a!=null ){
			x = new int[a.length];
			try{ 
			    for(int i=0; i<a.length; i++ )
				x[i] = (Integer)a[i]; 
			}catch(Exception e){ x = null; }
		} 
		return x;
	}
	public double[] reals_array( String tag ){
		Object[] a = array(tag);
		double[] x = null;
		if( a!=null ){
			x = new double[a.length];
			try{ 
			    for(int i=0; i<a.length; i++ ) 
				x[i] = (a[i] instanceof Double)?(Double)a[i]:(Integer)a[i];
			}catch(Exception e){ x = null; }
		} 
		return x;
	}

	public JSON object( String tag ){ try{ return (JSON)get(tag); }catch(Exception e){ return null; } }

	
	public boolean storable(Object obj){
		if( obj instanceof Object[] ){
			Object[] v = (Object[])obj;
			int i=0;
			while( i<v.length && storable(v[i]) ){ i++; }
			return i==v.length;
		}
		return ( obj == null || obj instanceof JSON || obj instanceof byte[] ||
			 obj instanceof String || obj instanceof Integer || obj instanceof Double || 
			 obj instanceof Boolean || obj instanceof double[] || obj instanceof int[] );
	}
	
	@Override
	public boolean set(String key, Object obj ){
		if( storable(obj ) ){
			if( obj instanceof double[] ){
				double[] a = (double[])obj;
				Object[] x = new Object[a.length];
				for( int i=0; i<a.length; i++ ) x[i] = a[i];
				obj = x;
			}else if( obj instanceof int[] ){
				int[] a = (int[])obj;
				Object[] x = new Object[a.length];
				for( int i=0; i<a.length; i++ ) x[i] = a[i];
				obj = x;
			}
			return super.set(key, obj);
		}
		return false;
	} 
	
//	@Override
	public String stringify() {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		try{
			boolean prComma = false;
			for( String key : this.keys() ){
				if( prComma ) sb.append(',');
				sb.append(Stringifyable.cast(key).stringify());
				sb.append(':');
				Object obj = this.get(key);
				sb.append(Stringifyable.cast(obj).stringify());
				prComma = true;
			}
		}catch( Exception e ){}	
		sb.append('}');
		return sb.toString();
	}

	@Override
	public JSON json() { return this; }

	@Override
	public void config(JSON json){
		this.clear();
		try{
			for( String key:json.keys() ){
				Object obj = json.get(key);
				Copyable c = Copyable.cast(obj);
				set(key, c.copy());
			}
		}catch(Exception e){ e.printStackTrace(); }	
	}
	
	public static void main( String[] args ) {
	    JSON json = new JSON();
	    json.set("id","Hello Motto!");
	    json.set("nuip", new Object[] {123.4,"4567",null});
	    String input = json.stringify();
	    System.out.println(input);
	    Parser parser = new Parser();
	    try{
		json = parser.get(input);
		System.out.println(json.stringify());
		//json = parser.get("{"+input);
		//System.out.println(json.stringify());
	    }catch(IOException e) { e.printStackTrace(); }
	}
}

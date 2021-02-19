package nsgl.object;

import java.io.IOException;
import java.io.OutputStream;

import nsgl.service.Service;
import nsgl.service.io.Store;

public interface Storable {
	/**
	 * Writes the object to a OutputStream (The object should has a write method)
	 * @param os The OutputStream
	 * @throws IOException IOException
	 */
	void store(OutputStream os) throws IOException;	
	
	static Storable cast( Object obj ){
		if( obj==null ) return null;
		if( obj instanceof Storable ) return (Storable)obj;
		Store cast = (Store)Service.get(obj,Storable.class);
		return new Storable(){ @Override public void store(OutputStream os) throws IOException { cast.store(obj, os); } }; 
	}
	
	/**
	 * Adds a reading method for the given object 
	 * @param caller Object that will register its reading method
	 * @param cast Reading method
	 */
	static void addCast( Object caller, Store cast ) { Service.set(caller, Storable.class, cast); }		

}

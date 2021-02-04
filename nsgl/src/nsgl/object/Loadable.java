package nsgl.object;

import java.io.IOException;
import java.io.InputStream;

import nsgl.service.Service;
import nsgl.service.io.Load;

public interface Loadable {
	/**
	 * Loads an object from the given input stream
	 * @param is The input stream from which the object will be read
	 * @return An object, of the type the read service is attending, that is read from the input stream
	 * @throws IOException IOException
	 */
	Object load(InputStream is) throws IOException;
	
	static Loadable cast( Object obj ){
		if( obj == null ) return null; 
		if( obj instanceof Loadable ) return (Loadable)obj;
		Load cast = (Load)Service.get(obj,Loadable.class);
		if( cast != null ) return new Loadable() {
			@Override
			public Object load(InputStream is) throws IOException {
				return cast.load(obj, is);
			}
		}; 
		return null; 
	}
	
	/**
	 * Adds a reading method for the given object 
	 * @param caller Object that will register its reading method
	 * @param cast Reading method
	 */
	static void addCast( Object caller, Load cast ) { Service.set(caller, Loadable.class, cast); }	
}
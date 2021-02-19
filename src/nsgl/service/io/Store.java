package nsgl.service.io;

import java.io.IOException;
import java.io.OutputStream;

import nsgl.object.Storable;
import nsgl.service.Service;

public interface Store {
	/**
	 * Writes an object to the given writer
	 * @param obj Object to write
	 * @param writer The writer object
	 * @throws IOException IOException
	 */
	public void store(Object obj, OutputStream os) throws IOException;
	
	static Store store(Object obj) { return (Store)Service.get(obj,Storable.class); } 

}

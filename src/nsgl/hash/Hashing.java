package nsgl.hash;

import nsgl.keymap.Key;

/**
 * <p>Title: Hashing</p>
 *
 * <p>Description: A hashing method</p>
 *
 */
public interface Hashing<T> extends Key<Integer,T>{
	/**
	 * Gets a hash code for an object
	 * @param obj Object computing its hash code
	 * @return A hash code for the object
	 */
	int hashCode( T obj );	

	default Integer key( T obj ) { return hashCode(obj); }
}
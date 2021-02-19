package nsgl.keymap;

/**
 * <p>Title: Mappable</p>
 *
 * <p>Description: Class of objects that have associated a key
 * 
 */
public interface Mappable<K> {
    /**
     * Gets the key of the object
     * @return Object's key
     */
    K map();
}
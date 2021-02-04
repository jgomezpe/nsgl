package nsgl.collection;

/**
 * <p>Title: Dynamic</p>
 *
 * <p>Description: Abstract resizable (add/remove of elements) Collection.
 *
 */
public interface Dynamic<T> extends Collection<T> {
    /**
     * Adds a data element to the collection
     * @param data Data element to be inserted
     * @return <i>true</i> if the element could be added, <i>false</i> otherwise
     */
    boolean add(T data);

    /**
     * Removes a data element from the collection
     * @param data Data element to be removed
     * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
     */
    boolean del(T data);

    /**
     * Removes all the element of the collection
     */
    void clear();
}
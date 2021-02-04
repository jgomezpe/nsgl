/**
 * <p>Copyright: Copyright (c) 2019</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2019 by Jonatan Gomez-Perdomo. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 * @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
 * (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */
package nsgl.keymap;

import java.util.Iterator;

import nsgl.collection.Collection;
import nsgl.object.Comparable;
import nsgl.pair.Pair;

/**
 * <p>Title: KeyMap</p>
 *
 * <p>Description: An indexed (key/map) collection</p>
 *
 */
public interface KeyMap<K,V> extends Collection<V>{
    /**
     * Determines if the given object belongs to the collection
     * @param data Data object to be located
     * @return <i>true</i>If the object belongs to the collection, <i>false</i> otherwise
     */
    @Override
    default boolean contains( V data ) { return find(data)!=null; }

    /**
     * Removes a data element from the structure
     * @param data Data element to be removed
     * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
     */
    default boolean del(V data) { return remove(find(data)); }

    /**
     * Obtains the object that has the given key
     * @param key Key of the object
     * @return Object with the given key 
     */
    @SuppressWarnings("unchecked")
    default V obtain(Object loc){ return get((K)loc); }
	
    /**
     * Obtains the location of the object into the search collection
     * @param data Data object to be located
     * @return A location (collection dependent) where the object is located, <i>null</i> if the object is not in the search collection
     */
    default K find(V data) {
	Comparable c = Comparable.cast(data);
	for( K i:keys() ) if( c.eq(get(i)) ) return i;
	return null;
    }
	
    /**
     * Determines if there is an object in the collection at the given location
     * @param loc Location of the object
     * @return <i>true</i>If there is an object at the given location in the collection, <i>false</i> otherwise
     */
    default boolean valid( K loc ) {
	Comparable c = Comparable.cast(loc);
	for( K i:keys() ) if( c.eq(i) ) return true;
	return false;
    }	

    /**
     * Gets the object that is at a given location or <i>null</i> if there is not object at such location
     * @param loc Location of the object
     * @return the object that is at the given location or <i>null</i> if there is not object in such location
     */
    V get( K loc );

    /**
     * Sets an element with the given location into the Indexed Structure
     * @param loc Location for setting the object 
     * @param value The element to be set
     * @return <i>true</i> if the element was set, <i>false</i> otherwise
     */
    boolean set( K loc, V value );

    /**
     * Sets an element into the KeyMap using the given key
     * @param pair Location,Value pair for the setting process
     * @return <i>true</i> if the element was set using the given key, <i>false</i> otherwise
     */
    default boolean set( Pair<K,V> pair ){ return set(pair.a(), pair.b()); }

    /**
     * Removes the element at the given position
     * @param loc Location of the object to be deleted
     * @return <i>true</i> if the element at the given position could be removed, <i>false</i> otherwise
     */
    boolean remove( K loc );
	
    /**
     * Collection of keys
     * @return An Iterable collection of the keys stored by the HashMap
     */
    Iterable<K> keys();
	
    /**
     * Gets an iterable collection of Pairs (Key/Value)
     * @return An iterable collection of the key/value pairs stored by the collection.
     */
    default Iterable<Pair<K, V>> pairs(){
	return new Iterable<Pair<K, V>>() {
	    protected Iterable<K> keys=keys();
	    
	    @Override
	    public Iterator<Pair<K, V>> iterator() {
		return new Iterator<Pair<K,V>>() {
		    protected Iterator<K> inner = keys.iterator();
		    @Override
		    public boolean hasNext(){ return inner.hasNext(); }
		    
		    @Override
		    public Pair<K, V> next() {
			K key = inner.next();
			return new Pair<K,V>(key, get(key)); 	
		    }
		};
	    }
	};	
    }
	
    /**
     * Merges the given HashMap (shallow copy) with the HashMap, replace if already in the HashMap
     * @param map MashMap to be merged
     */
    default void merge( KeyMap<K, V> map ){ for( K k : map.keys() ) this.set(k, map.get(k)); }
}
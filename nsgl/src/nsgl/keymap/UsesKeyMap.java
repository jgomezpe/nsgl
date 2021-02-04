package nsgl.keymap;

import nsgl.hash.HashMap;

public class UsesKeyMap<K,V> {
	protected KeyMap<K, V> keymap;
	
	public UsesKeyMap( KeyMap<K, V> keymap ){ this.keymap = keymap; }
	
	public UsesKeyMap(){ this( new HashMap<K,V>() ); }
}
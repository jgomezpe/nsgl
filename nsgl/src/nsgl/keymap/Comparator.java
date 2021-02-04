package nsgl.keymap;

import nsgl.object.Comparable;

public class Comparator  implements nsgl.object.Comparator{
    public boolean eq(KeyMap<?,?> one, KeyMap<?,?> two) {
	int n = one.size();
	int m = two.size();
	if( n!=m ) return false;
	for( Object obj : one ) if( !Comparable.cast(one.obtain(obj)).eq(two.obtain(obj)) ) return false;
	return true;
    }
	
	@Override
	public boolean eq(Object one, Object two) {
		if( one==two ) return true;
		if( one instanceof KeyMap ) return eq((KeyMap<?,?>)one, (KeyMap<?,?>)two);
		return false;
	}
}

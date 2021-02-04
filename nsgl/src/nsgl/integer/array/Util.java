package nsgl.integer.array;

import nsgl.integer.random.Uniform;
import nsgl.random.raw.RawGenerator;

public class Util {
    /**
     * Reverses the given array
     * @param a Integer array to be reversed
     */
    public static void invert(int[] a) {
        int n = a.length;
        int j = n - 1;
        for (int i = 0; i < j; i++) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            j--;
        }
    }
    
    public static int max(int[] a){
        int m = a[0];
        for( int i=1; i<a.length; i++ ){
            if( m < a[i] ){
                m = a[i];
            }
        }
        return m;
    }
    
    public static int min(int[] a){
        int m = a[0];
        for( int i=1; i<a.length; i++ ){
            if( m > a[i] ){
                m = a[i];
            }
        }
        return m;
    }   
    
    public static int[] create(int n, int v) {
	int[] x = new int[n];
	for( int i=0; i<n; i++ ) x[i] = v;
	return x;
    }
    
	/**
	 * Inner generator of indices for shuffling the set of objects
	 */
	protected static Uniform ig = new Uniform(0);
	
	/**
	 * Sets the RawGenerator  
	 * @param g RawGenerator used by the object 
	 */
	public static void raw( RawGenerator g ){ RawGenerator.cast(ig,g); }
	
	public static RawGenerator raw(){ return RawGenerator.cast(ig); }
	
	/**
	 * Generates an array with all the integers in the interval [0,n) stored in a random fashion
	 * @param n Sup limit (the generated array has <i>n</i> elements (the integer numbers in the interval [0,n))
	 * @return An array with all the integers in the interval [0,n) stored in a random fashion
	 */
	public static int[] shuffle(int n) {
		int[] set = new int[n];
		for (int i = 0; i < n; i++) set[i] = i;
		shuffle(set);
		return set;
	}
	
	public static int[] shuffle_indices(int n){
		ig.set(n);
		return ig.generate(2 * n);
	}
	
	/**
	 * Shuffles the given array of integers
	 * @param set Array of integers to be shuffled
	 */
	public static void shuffle(int[] set) {
		int j, k;
		int temp;
		int[] indices = shuffle_indices(set.length);
		for (int i = 0; i<indices.length; i+=2) {
			j = indices[i];
			k = indices[i+1];
			temp = set[j];
			set[j] = set[k];
			set[k] = temp;
		}
	}
}
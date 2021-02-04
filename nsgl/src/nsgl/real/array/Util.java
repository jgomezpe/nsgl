package nsgl.real.array;

public class Util {

    /**
     * Reverses the given array
     * @param a Double array to be reversed
     */
    public static void invert(double[] a) {
        int n = a.length;
        int j = n - 1;
        for (int i = 0; i < j; i++) {
            double tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            j--;
        }
    }

    /**
     * Normalizes the array to the interval [0,1] using the sum of the values in the array as the maximum value
     * (precondition: Values in the array should be non negatives and at least one value should be different of zero
     * @param x Array to be normalized
     */
    public static void normalize( double[] x ){
    int n = x.length;
        double sum = 0.0;
        for( int i=0; i<n; i++ ){
            sum +=  x[i];
        }
        if( !nsgl.real.Util.isZero(sum) ){
            for (int i = 0; i < n; i++) {
                x[i] /= sum;
            }
        }
    }
    
    public static double max(double[] a){
        double m = a[0];
        for( int i=1; i<a.length; i++ ){
            if( m < a[i] ){
                m = a[i];
            }
        }
        return m;
    }
    
    public static double min(double[] a){
        double m = a[0];
        for( int i=1; i<a.length; i++ ){
            if( m > a[i] ){
                m = a[i];
            }
        }
        return m;
    }   

    public static double[] create(int n, double v) {
	double[] x = new double[n];
	for( int i=0; i<n; i++ ) x[i] = v;
	return x;
    }
    
	/**
	 * Shuffles the given array of integers
	 * @param set Array of integers to be shuffled
	 */
	public static void shuffle(double[] set) {
		int j, k;
		double temp;
		int[] indices = nsgl.integer.array.Util.shuffle_indices(set.length);
		for (int i = 0; i<indices.length; i+=2) {
			j = indices[i];
			k = indices[i+1];
			temp = set[j];
			set[j] = set[k];
			set[k] = temp;
		}
	}

}

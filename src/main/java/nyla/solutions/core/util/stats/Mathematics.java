package nyla.solutions.core.util.stats;

/**
 * 
 * Based on
 * 
 * http://introcs.cs.princeton.edu/java/stdlib/StdStats.java.html
 * 
 *
 */
public class Mathematics
{
	public static double var(double[] a) {
      
        if (a.length == 0) return Double.NaN;
        double avg = mean(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }
        return sum / (a.length - 1);
    }
	
	  /**
     * Returns the average value in the specified array.
     *
     * @param  a the array
     * @return the average value in the array {@code a[]};
     *         {@code Double.NaN} if no such value
     */
    public static double mean(double[] a) {

        if (a.length == 0) return Double.NaN;
        double sum = sum(a);
        return sum / a.length;
    }

    /**
     * Returns the population variance in the specified array.
     *
     * @param  a the array
     * @return the population variance in the array {@code a[]};
     *         {@code Double.NaN} if no such value
     */
    public static double varp(double[] a) {

        if (a.length == 0) return Double.NaN;
        double avg = mean(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }
        return sum / a.length;
    }
    
    /**
     * Returns the population standard deviation in the specified array.
     *
     * @param  a the array
     * @return the population standard deviation in the array;
     *         {@code Double.NaN} if no such value
     */
    public static double stdDev(double[] a) {

    	if(a == null || a.length == 0)
    		return -1;
    	
        return Math.sqrt(varp(a));
    }
    
    private static double sum(double[] a) {

        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

}

package math;

/**
 * A class containing the result of an application of the Extended Euclidean algorithm.
 * 
 * The result is gcd = a*x + b*y, where a and b are the inputs to the algorithm.
 * 
 * @author Sebastian Björkqvist
 */
public class ExtendedEuclideanResult {

    public final int gcd;
    public final int x;
    public final int y;

    public ExtendedEuclideanResult(int gcd, int x, int y) {
        this.gcd = gcd;
        this.x = x;
        this.y = y;
    }
}

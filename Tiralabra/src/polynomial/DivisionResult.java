package polynomial;

/**
 * The result of a polynomial division. 
 * 
 * @author Sebastian Björkqvist
 */
public class DivisionResult {

    public final IPolynomial quotient;
    public final IPolynomial remainder;

    public DivisionResult(IPolynomial quotient, IPolynomial remainder) {
        this.quotient = quotient;
        this.remainder = remainder;
    }
    
}

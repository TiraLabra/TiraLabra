package polynomial.impl.array;

import math.MathUtil;
import polynomial.DivisionResult;
import polynomial.IPolynomial;

/**
 * An implementation of IPolynomial using arrays.
 *
 * The values of the polynomial are kept in an array. The coefficient of degree
 * k is found at the index k in the array.
 * 
 * The multiplication is currently done naively in O(n^2) time.
 *
 * @author Sebastian Björkqvist
 */
public class ArrayPolynomial implements IPolynomial {

    private final int characteristic;
    private int[] coefficients;

    public ArrayPolynomial(int characteristic) {
        if (characteristic < 0) {
            throw new IllegalArgumentException("The characteristic " + characteristic + " is negative!");
        }
        this.characteristic = characteristic;
        this.coefficients = new int[0];
    }

    @Override
    public int getCharacteristic() {
        return characteristic;
    }

    @Override
    public int getDegree() {
        return coefficients.length - 1;
    }

    @Override
    public int getNumberOfNonZeroCoefficients() {
        int amount = 0;
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0) {
                amount++;
            }
        }
        return amount;
    }

    @Override
    public void addTerm(int coefficient, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("The exponent " + exponent + " is negative.");
        }
        if (coefficient == 0) {
            return;
        }
        if (exponent < coefficients.length) {
            coefficients[exponent] = getValueModuloCharacteristic(coefficients[exponent] + coefficient);
            if (exponent == coefficients.length - 1) {
                coefficients = shrinkArray(coefficients);
            }
        } else {
            int[] oldCoefficients = coefficients;
            coefficients = new int[exponent + 1];
            System.arraycopy(oldCoefficients, 0, coefficients, 0, oldCoefficients.length);
            coefficients[exponent] = coefficient;
        }
    }

    @Override
    public void removeTerm(int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("The exponent " + exponent + " is negative.");
        }
        if (exponent >= coefficients.length) {
            return;
        }
        
        coefficients[exponent] = 0;
        
        coefficients = shrinkArray(coefficients);
    }

    @Override
    public int evaluate(int value) {
        int result = 0;

        for (int exponent = 0; exponent < coefficients.length; exponent++) {
            if (coefficients[exponent] != 0) {
                if (characteristic == 0) {
                    result += getValueModuloCharacteristic(coefficients[exponent] * MathUtil.pow(value, exponent));
                } else {
                    result += getValueModuloCharacteristic(coefficients[exponent] * MathUtil.powModulo(value, exponent, characteristic));
                }
            }
        }

        return getValueModuloCharacteristic(result);
    }

    @Override
    public int getCoefficientOfLeadingTerm() {
        if (coefficients.length == 0) {
            return 0;
        }
        return coefficients[coefficients.length - 1];
    }

    @Override
    public int getCoefficientAtDegree(int degree) {
        if (degree < 0) {
            throw new IllegalArgumentException("The degree " + degree + " is negative!");
        }
        if (degree >= coefficients.length) {
            return 0;
        }
        return coefficients[degree];
    }

    @Override
    public String toString() {
        StringBuilder stringRepr = new StringBuilder();
        boolean first = true;
        int exponent = coefficients.length - 1;
        while (exponent >= 0) {
            int coefficient = coefficients[exponent];
            if (coefficient != 0) {
                if (first) {
                    if (coefficient < 0) {
                        stringRepr.append("-");
                    }
                } else {
                    stringRepr.append(" ");
                    if (coefficient > 0) {
                        stringRepr.append("+");
                    } else {
                        stringRepr.append("-");
                    }
                    stringRepr.append(" ");
                }
                if ((coefficient != 1 && coefficient != -1) || exponent == 0) {
                    stringRepr.append(MathUtil.abs(coefficient));
                }
                if (exponent >= 1) {
                    stringRepr.append("x");
                    if (exponent > 1) {
                        stringRepr.append("^");
                        stringRepr.append(exponent);
                    }
                }
            }
            exponent--;
            first = false;
        }

        return stringRepr.toString();
    }

    @Override
    public IPolynomial add(IPolynomial polynomial) {
        checkNull(polynomial);
        checkCharacteristic(polynomial.getCharacteristic());
        checkImplementation(polynomial);

        ArrayPolynomial toAdd = (ArrayPolynomial) polynomial;

        int degreeOfResult;

        if (toAdd.getDegree() > getDegree()) {
            degreeOfResult = toAdd.getDegree();
        } else {
            degreeOfResult = getDegree();
        }

        ArrayPolynomial result = new ArrayPolynomial(characteristic);

        result.coefficients = new int[degreeOfResult + 1];

        for (int i = 0; i < result.coefficients.length; i++) {
            int newValue = 0;
            if (i < toAdd.coefficients.length) {
                newValue = getValueModuloCharacteristic(newValue + toAdd.coefficients[i]);
            }
            if (i < coefficients.length) {
                newValue = getValueModuloCharacteristic(newValue + coefficients[i]);
            }
            result.coefficients[i] = newValue;
        }

        result.coefficients = shrinkArray(result.coefficients);

        return result;
    }

    @Override
    public IPolynomial subtract(IPolynomial polynomial) {
        checkNull(polynomial);
        checkCharacteristic(polynomial.getCharacteristic());
        checkImplementation(polynomial);

        ArrayPolynomial other = (ArrayPolynomial) polynomial;

        // Subtracting is the same as adding the additive inverse, so we just
        // change the sign of all coefficients and use the add-method.
        ArrayPolynomial inverse = new ArrayPolynomial(other.characteristic);

        inverse.coefficients = new int[other.coefficients.length];

        for (int i = 0; i < inverse.coefficients.length; i++) {
            inverse.coefficients[i] = -other.coefficients[i];
        }

        return add(inverse);
    }

    @Override
    public IPolynomial multiply(IPolynomial polynomial) {
        checkNull(polynomial);
        checkCharacteristic(polynomial.getCharacteristic());
        checkImplementation(polynomial);

        // This is a naive, O(n^2) implementation of the multiplication.
        
        ArrayPolynomial other = (ArrayPolynomial) polynomial;
        
        // Multiplication with the zero polynomial results in a zero polynomial.
        if (isZeroPolynomial(other)) {
            return new ArrayPolynomial(characteristic);
        }
        
        int degreeOfResult = getDegree() + other.getDegree();
        
        ArrayPolynomial result = new ArrayPolynomial(characteristic);
        
        result.coefficients = new int[degreeOfResult + 1];
        
        for (int indexOfThis = 0; indexOfThis < coefficients.length; indexOfThis++) {
            for (int indexOfOther = 0; indexOfOther < other.coefficients.length; indexOfOther ++) {
                int degree = indexOfThis + indexOfOther;
                int coefficientToAdd = getValueModuloCharacteristic(coefficients[indexOfThis] * other.coefficients[indexOfOther]);
                
                result.coefficients[degree] = getValueModuloCharacteristic(coefficientToAdd + result.coefficients[degree]);
            }
        }
        
        result.coefficients = shrinkArray(result.coefficients);
        
        return result;
    }

    @Override
    public DivisionResult divide(IPolynomial polynomial) {
        checkNull(polynomial);
        checkCharacteristic(polynomial.getCharacteristic());
        checkImplementation(polynomial);

        ArrayPolynomial divisor = (ArrayPolynomial) polynomial;

        if (isZeroPolynomial(divisor)) {
            throw new IllegalArgumentException("Divisor is the zero polynomial.");
        }        
        
        // In the beginning, the remainder is the same as this polynomial and the
        // quotient is zero.
        ArrayPolynomial quotient = new ArrayPolynomial(characteristic);
        ArrayPolynomial remainder = createCopyOfPolynomial(this);        
        
        while (remainder.getDegree() >= divisor.getDegree() && !isZeroPolynomial(remainder)) {
            ArrayPolynomial leadingTermDivisionResult = divideLeadingTerms(remainder, divisor);
            // Adding the leading term to the quotient. The leading term is always a monomial.
            quotient.addTerm(leadingTermDivisionResult.getCoefficientOfLeadingTerm(), leadingTermDivisionResult.getDegree());
            
            ArrayPolynomial removeFromRemainder = (ArrayPolynomial) leadingTermDivisionResult.multiply(divisor);
            // Removing the divided part from the remainder
            remainder = (ArrayPolynomial) remainder.subtract(removeFromRemainder);
        }

        return new DivisionResult(quotient, remainder);        
    }

    private int getValueModuloCharacteristic(int value) {
        if (characteristic == 0) {
            return value;
        }
        return value % characteristic;
    }

    private void checkNull(IPolynomial polynomial) {
        if (polynomial == null) {
            throw new IllegalArgumentException("The polynomial given is null!");
        }
    }

    private void checkCharacteristic(int characteristic) {
        if (this.characteristic != characteristic) {
            throw new IllegalArgumentException("The characteristic " + this.characteristic
                    + " of this polynomial is different than the characteristic " + characteristic
                    + " of the other polynomial.");
        }
    }

    private void checkImplementation(IPolynomial polynomial) throws UnsupportedOperationException {
        if (polynomial.getClass() != ArrayPolynomial.class) {
            throw new UnsupportedOperationException("Calculations with implementations of "
                    + "type " + polynomial.getClass().getName() + " not yet supported.");
        }
    }

    /**
     * Shrinks the coefficient array so there are no zeros in the end.
     *
     * We do this so that the last coefficient is always non-zero, and thus
     * we may always get the degree of the polynomial straight from the
     * size of the coefficient array.
     * 
     * @param coefficientArray The coefficient array
     * @return The same array with zeros removed from the end.
     */
    private int[] shrinkArray(int[] coefficientArray) {
        int currentHighestNonZeroIndex = coefficientArray.length - 1;
        while (currentHighestNonZeroIndex >= 0 && coefficientArray[currentHighestNonZeroIndex] == 0) {
            currentHighestNonZeroIndex--;
        }
        
        // If the highest index is already non-zero, we don't need to change the array.
        if (currentHighestNonZeroIndex == coefficientArray.length - 1) {
            return coefficientArray;
        }
        
        int[] newCoefficientArray = new int[currentHighestNonZeroIndex + 1];
        
        System.arraycopy(coefficientArray, 0, newCoefficientArray, 0, newCoefficientArray.length);
        
        return newCoefficientArray;
    }
    
    private boolean isZeroPolynomial(ArrayPolynomial polynomial) {
        return polynomial.getDegree() == -1;
    }

    private ArrayPolynomial createCopyOfPolynomial(ArrayPolynomial polynomial) {
        ArrayPolynomial copy = new ArrayPolynomial(polynomial.characteristic);
        
        copy.coefficients = new int[polynomial.coefficients.length];
        
        System.arraycopy(polynomial.coefficients, 0, copy.coefficients, 0, copy.coefficients.length);
        
        return copy;
    }
    
    /**
     * Divides the leading term of the polynomial remainder with the leading
     * term of the polynomial divisor.
     *
     * @param remainder
     * @param divisor
     * @throws IllegalArgumentException if the coefficient would be an
     * non-integer and it can't be represented as an integer modulo the
     * characteristic.
     * @return ArrayPolynomial that is the result of the division. It will
     * contain only one non-zero coefficient.
     */
    private ArrayPolynomial divideLeadingTerms(ArrayPolynomial remainder, ArrayPolynomial divisor) {
        int coefficientRemainder = remainder.getCoefficientOfLeadingTerm();
        int coefficientDivisor = divisor.getCoefficientOfLeadingTerm();

        int coefficientOfResult;

        if (coefficientRemainder % coefficientDivisor == 0) {
            coefficientOfResult = coefficientRemainder / coefficientDivisor;
        } else if (remainder.characteristic == 0) {
            throw new UnsupportedOperationException("Cannot represent non-integer coefficients in characteristic 0");
        } else {
            int inverseOfDivisorCoefficient = MathUtil.getInverseModP(coefficientDivisor, remainder.characteristic);
            if (inverseOfDivisorCoefficient == -1) {
                throw new UnsupportedOperationException("Cannot represent 1/" + coefficientDivisor + " as an integer modulo " + characteristic);
            }
            coefficientOfResult = (inverseOfDivisorCoefficient * coefficientRemainder) % remainder.characteristic;
        }
        
        int exponentOfResult = remainder.getDegree() - divisor.getDegree();
        
        ArrayPolynomial result = new ArrayPolynomial(remainder.characteristic);
        
        result.coefficients = new int[exponentOfResult + 1];
        result.coefficients[exponentOfResult] = coefficientOfResult;
        
        return result;
    }    

}

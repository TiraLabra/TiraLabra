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
        if (exponent < coefficients.length) {
            coefficients[exponent] = getValueModuloCharacteristic(coefficients[exponent] + coefficient);
            if (exponent == coefficients.length - 1 && coefficients[exponent] == 0) {
                // Removing unnecessary part of array.
                int[] oldCoefficients = coefficients;
                int currentDegree = exponent - 1;
                while (currentDegree >= 0 && oldCoefficients[currentDegree] == 0) {
                    currentDegree--;
                }
                coefficients = new int[currentDegree + 1];
                System.arraycopy(oldCoefficients, 0, coefficients, 0, coefficients.length);
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
        if (exponent == coefficients.length - 1) {
            // Checking what the new degree is
            int[] oldCoefficients = coefficients;
            int currentDegree = exponent - 1;
            while (currentDegree >= 0 && oldCoefficients[currentDegree] == 0) {
                currentDegree--;
            }
            coefficients = new int[currentDegree + 1];
            System.arraycopy(oldCoefficients, 0, coefficients, 0, coefficients.length);
        }
    }

    @Override
    public int evaluate(int value) {
        int result = 0;
        
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0) {
                result += getValueModuloCharacteristic(coefficients[i] * MathUtil.pow(value, i));
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
                if (!first) {
                    stringRepr.append(" ");
                    if (coefficient > 0) {
                        stringRepr.append("+");
                    } else {
                        stringRepr.append("-");
                    }
                    stringRepr.append(" ");
                }
                if (coefficient != 1) {
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
        
        ArrayPolynomial other = (ArrayPolynomial) polynomial;
        
        int newDegree;
        
        if (other.getDegree() > getDegree()) {
            newDegree = other.getDegree();
        } else {
            newDegree = getDegree();
        }
        
        ArrayPolynomial result = new ArrayPolynomial(characteristic);
        
        result.coefficients = new int[newDegree + 1];
        
        for (int i = 0; i < result.coefficients.length; i++) {
            int newValue = 0;
            if (i < other.coefficients.length) {
                newValue = getValueModuloCharacteristic(newValue + other.coefficients[i]);
            }
            if (i < coefficients.length) {
                newValue = getValueModuloCharacteristic(newValue + coefficients[i]);
            }
            result.coefficients[i] = newValue;
        }
        
        if (result.coefficients[result.coefficients.length - 1] == 0) {
            // Checking what the new degree is
            int[] oldCoefficients = result.coefficients;
            int currentDegree = result.coefficients.length - 2;
            while (currentDegree >= 0 && oldCoefficients[currentDegree] == 0) {
                currentDegree--;
            }
            result.coefficients = new int[currentDegree + 1];
            System.arraycopy(oldCoefficients, 0, result.coefficients, 0, result.coefficients.length);
        }
        
        return result;
    }

    @Override
    public IPolynomial subtract(IPolynomial polynomial) {
        checkNull(polynomial);
        checkCharacteristic(polynomial.getCharacteristic());
        checkImplementation(polynomial);
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IPolynomial multiply(IPolynomial polynomial) {
        checkNull(polynomial);
        checkCharacteristic(polynomial.getCharacteristic());
        checkImplementation(polynomial);
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DivisionResult divide(IPolynomial polynomial) {
        checkNull(polynomial);
        checkCharacteristic(polynomial.getCharacteristic());
        checkImplementation(polynomial);
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}

package polynomial.impl.linkedlist;

import math.MathUtil;
import polynomial.DivisionResult;
import polynomial.IPolynomial;

/**
 * An implementation of IPolynomial using linked lists.
 *
 * The polynomial is constructed as a doubly linked list of monomials. The list
 * is kept in order by degree. Monomials with coefficient zero are removed from
 * the list.
 *
 * @author Sebastian Björkqvist
 */
public class LinkedListPolynomial implements IPolynomial {

    private final int characteristic;
    private Monomial highestDegreeTerm;
    private Monomial lowestDegreeTerm;

    private class Monomial {

        private final int coefficient;
        private final int exponent;
        private Monomial lowerMonomial;
        private Monomial higherMonomial;

        public Monomial(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
        
        @Override
        public String toString() {
            StringBuilder stringRepr = new StringBuilder();
            if (coefficient != 1) {
                stringRepr.append(coefficient);
            }
            if (exponent > 0) {
                stringRepr.append("x");
                if (exponent > 1) {
                    stringRepr.append("^").append(exponent);
                }
            }
            return stringRepr.toString();
        }

    }

    public LinkedListPolynomial(int characteristic) {
        if (characteristic < 0) {
            throw new IllegalArgumentException("Characteristic " + characteristic + " is negative!");
        }
        this.characteristic = characteristic;
        this.highestDegreeTerm = null;
        this.lowestDegreeTerm = null;
    }

    @Override
    public int getCharacteristic() {
        return characteristic;
    }

    @Override
    public int getDegree() {
        if (highestDegreeTerm != null) {
            return highestDegreeTerm.exponent;
        }
        return 0;
    }

    @Override
    public int getNumberOfNonZeroCoefficients() {
        if (highestDegreeTerm == null) {
            return 0;
        }

        int numberOfNonZeroCoefficients = 1;
        Monomial next = highestDegreeTerm.lowerMonomial;
        while (next != null) {
            if (next.coefficient != 0) {
                numberOfNonZeroCoefficients++;
            }
            next = next.lowerMonomial;
        }

        return numberOfNonZeroCoefficients;
    }

    @Override
    public void addTerm(int coefficient, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("The exponent " + exponent + " is negative!");
        }
        if (getValueModuloCharacteristic(coefficient) == 0) {
            return;
        }
        // We do the checking starting from the lowest degree monomial
        if (lowestDegreeTerm == null) {
            Monomial monomial = new Monomial(getValueModuloCharacteristic(coefficient), exponent);
            highestDegreeTerm = monomial;
            lowestDegreeTerm = monomial;
            return;
        }

        if (lowestDegreeTerm.exponent > exponent) {
            Monomial monomial = new Monomial(getValueModuloCharacteristic(coefficient), exponent);
            monomial.higherMonomial = lowestDegreeTerm;
            lowestDegreeTerm.lowerMonomial = monomial;
            lowestDegreeTerm = monomial;
            return;
        }

        if (lowestDegreeTerm.exponent == exponent) {
            int newCoefficient = getValueModuloCharacteristic(lowestDegreeTerm.coefficient + coefficient);
            Monomial higherTerm = lowestDegreeTerm.higherMonomial;
            // If the new coefficient is 0, we remove the monomial entirely
            if (newCoefficient == 0) {
                lowestDegreeTerm = higherTerm;
                // If a higher term existed previously, it is now the lowest term.
                // Otherwise, the polynomial is now empty.
                if (higherTerm != null) {
                    higherTerm.lowerMonomial = null;
                } else {
                    highestDegreeTerm = null;
                }
            } else {
                Monomial toAdd = new Monomial(newCoefficient, exponent);
                toAdd.higherMonomial = higherTerm;
                toAdd.lowerMonomial = null;
                lowestDegreeTerm = toAdd;
                // If a higher term existed, its lower term is now the added term.
                // Otherwise, only the added term exists.
                if (higherTerm != null) {
                    higherTerm.lowerMonomial = toAdd;
                } else {
                    highestDegreeTerm = toAdd;
                }
            }
            return;
        }

        // If we reach this stage, we know that the Monomial lowestDegreeTerm
        // has an exponent lower than the exponent given as an argument.
        Monomial prev = lowestDegreeTerm;
        Monomial next = lowestDegreeTerm.higherMonomial;

        // Advancing the linked list to find the correct spot for the new term
        while (next != null && next.exponent < exponent) {
            Monomial newNext = next.higherMonomial;
            prev = next;
            next = newNext;
        }

        // Checking if we've reached the end of the linked list
        if (next == null) {
            Monomial toAdd = new Monomial(getValueModuloCharacteristic(coefficient), exponent);
            toAdd.lowerMonomial = prev;
            prev.higherMonomial = toAdd;
            highestDegreeTerm = toAdd;
            return;
        }

        // If the exponents match, we replace the current monomial with this
        // exponent with one where we sum the coefficients
        if (next.exponent == exponent) {
            int newCoefficient = (next.coefficient + coefficient) % characteristic;
            Monomial higherTerm = next.higherMonomial;
            // If the new coefficient is 0, we remove the monomial entirely
            Monomial toAdd = null;
            if (newCoefficient == 0) {
                prev.higherMonomial = higherTerm;
                if (higherTerm != null) {
                    higherTerm.lowerMonomial = prev;
                }
            } else {
                toAdd = new Monomial(newCoefficient, exponent);
                toAdd.higherMonomial = higherTerm;
                toAdd.lowerMonomial = prev;
                prev.higherMonomial = toAdd;
                if (higherTerm != null) {
                    higherTerm.lowerMonomial = toAdd;
                }
            }
            // if the polynomial next was the highest term, we must replace it
            if (highestDegreeTerm == next) {
                if (toAdd != null) {
                    highestDegreeTerm = toAdd;
                } else {
                    highestDegreeTerm = prev;
                }
            }
            return;
        }

        if (next.exponent > exponent) {
            Monomial toAdd = new Monomial(getValueModuloCharacteristic(coefficient), exponent);
            prev.higherMonomial = toAdd;
            next.lowerMonomial = toAdd;
            toAdd.lowerMonomial = prev;
            toAdd.higherMonomial = next;
        }

    }

    @Override
    public void removeTerm(int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("The exponent " + exponent + " is negative!");
        }

        // We do the checking starting from the lowest degree monomial
        if (lowestDegreeTerm == null) {
            return;
        }

        if (lowestDegreeTerm.exponent > exponent) {
            return;
        }

        if (lowestDegreeTerm.exponent == exponent) {
            Monomial newLowest = lowestDegreeTerm.higherMonomial;
            if (highestDegreeTerm == lowestDegreeTerm) {
                highestDegreeTerm = newLowest;
            }
            lowestDegreeTerm = newLowest;
            newLowest.lowerMonomial = null;
            return;
        }

        // If we reach this stage, we know that the exponent of the lowest
        // degree term is lower than the exponent given as an argument
        Monomial prev = lowestDegreeTerm;
        Monomial next = lowestDegreeTerm.higherMonomial;

        // Finding the term to remove
        while (next != null && next.exponent < exponent) {
            Monomial newNext = next.higherMonomial;
            prev = next;
            next = newNext;
        }

        // If the exponent matches, remove the term.
        if (next != null && next.exponent == exponent) {
            Monomial newHigher = next.higherMonomial;
            prev.higherMonomial = newHigher;

            if (newHigher != null) {
                newHigher.lowerMonomial = prev;
            } else {
                // This means that next was the highest monomial.
                // Thus prev is now the highest monomial.
                highestDegreeTerm = prev;
            }
        }
    }

    @Override
    public int evaluate(int value) {
        int result = 0;
        Monomial current = highestDegreeTerm;
        while (current != null) {
            int valueAtMonomial = current.coefficient * MathUtil.pow(value, current.exponent);
            result = getValueModuloCharacteristic(result + valueAtMonomial);
            current = current.lowerMonomial;
        }
        return result;
    }

    @Override
    public int getCoefficientOfLeadingTerm() {
        if (highestDegreeTerm == null) {
            return 0;
        }
        return highestDegreeTerm.coefficient;
    }

    @Override
    public int getCoefficientAtDegree(int degree) {
        if (degree < 0) {
            throw new IllegalArgumentException("The degree " + degree + " is negative!");
        }
        if (highestDegreeTerm == null) {
            return 0;
        }
        Monomial current = highestDegreeTerm;
        while (current != null && current.exponent > degree) {
            current = current.lowerMonomial;
        }

        if (current == null || current.exponent < degree) {
            return 0;
        }

        return current.coefficient;
    }

    @Override
    public IPolynomial add(IPolynomial polynomial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IPolynomial subtract(IPolynomial polynomial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IPolynomial multiply(IPolynomial polynomial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DivisionResult divide(IPolynomial polynomial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        StringBuilder stringRepr = new StringBuilder();
        Monomial current = highestDegreeTerm;
        boolean first = true;
        while (current != null) {
            if (!first) {
                stringRepr.append(" ");
                if (current.coefficient > 0) {
                    stringRepr.append("+");
                } else {
                    stringRepr.append("-");
                }
                stringRepr.append(" ");
            }
            if (current.coefficient != 1) {
                stringRepr.append(Math.abs(current.coefficient));
            }
            if (current.exponent >= 1) {
                stringRepr.append("x");
                if (current.exponent > 1) {
                    stringRepr.append("^");
                    stringRepr.append(current.exponent);
                }
            }
            current = current.lowerMonomial;
            first = false;
        }

        return stringRepr.toString();
    }

    private int getValueModuloCharacteristic(int value) {
        if (characteristic == 0) {
            return value;
        }
        return value % characteristic;
    }

}

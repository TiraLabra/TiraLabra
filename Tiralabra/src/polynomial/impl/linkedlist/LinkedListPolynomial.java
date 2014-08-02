package polynomial.impl.linkedlist;

import polynomial.DivisionResult;
import polynomial.IPolynomial;

/**
 * An implementation of IPolynomial using linked lists.
 * 
 * The polynomial is constructed as a doubly linked list of monomials.
 * The list is kept in order by degree. Monomials with coefficient zero are
 * removed from the list.
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
        private Monomial nextMonomial;
        private Monomial prevMonomial;

        public Monomial(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
        
    }

    public LinkedListPolynomial(int characteristic) {
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
        Monomial next = highestDegreeTerm.nextMonomial;
        while (next != null) {
            if (next.coefficient != 0) {
                numberOfNonZeroCoefficients++;
            }
            next = next.nextMonomial;
        }
        
        return numberOfNonZeroCoefficients;
    }

    @Override
    public void addTerm(int coefficient, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("The exponent " + exponent + " is negative!");
        }
        if (coefficient == 0) {
            return;
        }
        if (highestDegreeTerm == null) {
            Monomial monomial = new Monomial(coefficient, exponent);
            highestDegreeTerm = monomial;
            lowestDegreeTerm = monomial;
            return;
        }
        
    }

    @Override
    public void removeTerm(int exponent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public int evaluate(int value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCoefficientOfLeadingTerm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCoefficientAtDegree(int degree) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}

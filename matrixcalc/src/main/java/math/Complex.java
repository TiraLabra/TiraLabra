package math;

/**
 * Contains a complex number z = a + bi, where a = Re(z) and b = Im(z).
 *
 * @author ydna
 */
public class Complex {

    /**
     * The real part.
     */
    private double a;

    /**
     * The imaginary part.
     */
    private double b;

    /**
     * Constructs a complex number.
     *
     * @param real Real part.
     * @param imag Imaginary part.
     */
    public Complex(double real, double imag) {
        this.a = real;
        this.b = imag;
    }

    public Complex(double real) {
        this(real, 0);
    }

    @Override
    public String toString() {
        if (b == 0) {
            return a + "";
        } else if (a == 0) {
            return b + "i";
        } else if (b < 0) {
            return a + "" + b + "i";
        } else {
            return a + "+" + b + "i";
        }
    }

    /**
     * Returns the real part of this complex number.
     *
     * @return Real part.
     */
    public double re() {
        return this.a;
    }

    /**
     * Returns the imaginary part of this complex number.
     *
     * @return Imaginary part.
     */
    public double im() {
        return this.b;
    }

    /**
     * Calculates the conjugate of this complex number.
     *
     * @return Complex conjugate.
     */
    public Complex conjugate() {
        return new Complex(a, -b);
    }

    /**
     * Calculates the reciprocal of this complex number.
     *
     * @return Reciprocal.
     */
    public Complex reciprocal() {
        return new Complex(a / (a * a + b * b), -b / (a * a + b * b));
    }

    /**
     * Adds this complex number to another one.
     *
     * @param that Another complex number.
     * @return Sum.
     */
    public Complex add(Complex that) {
        return new Complex(a + that.re(), b + that.im());
    }

    /**
     * Subtracts another complex number from this one.
     *
     * @param that Another complex number.
     * @return Difference.
     */
    public Complex subtract(Complex that) {
        return new Complex(a - that.re(), b - that.im());
    }

    /**
     * Multiplies this complex number with another one.
     *
     * @param that Factor.
     * @return Product.
     */
    public Complex multiply(Complex that) {
        return new Complex(a * that.re() - b * that.im(), b * that.re() + a * that.im());
    }

    /**
     * Divides this complex number with another one.
     *
     * @param that Divisor.
     * @return Quotient.
     */
    public Complex divide(Complex that) {
        return this.multiply(that.reciprocal());
    }

    /**
     * Adds a real number to this complex number.
     *
     * @param number Real number.
     * @return Sum.
     */
    public Complex add(double number) {
        return new Complex(a + number, b);
    }

    /**
     * Subtracts a real number from this complex number.
     *
     * @param number Real number.
     * @return Difference.
     */
    public Complex subtract(double number) {
        return new Complex(a - number, b);
    }

    /**
     * Multiplies this complex number with a real number.
     *
     * @param number Factor.
     * @return Product.
     */
    public Complex multiply(double number) {
        return new Complex(number * a, number * b);
    }

    /**
     * Divides this complex number with a real number.
     *
     * @param number Divisor.
     * @return Quotient.
     */
    public Complex divide(double number) {
        return new Complex(a / number, b / number);
    }

    /**
     * Calculates the absolute value of this complex number.
     *
     * @return Absolute value.
     */
    public double abs() {
        return Math.sqrt(a * a + b * b);
    }

}

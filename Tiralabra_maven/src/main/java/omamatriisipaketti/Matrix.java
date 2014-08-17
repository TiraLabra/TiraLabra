package omamatriisipaketti;


public interface Matrix {

    public Matrix smoni(double skalaari);
    public Matrix plus(Matrix matrix) throws Exception;
    public double det();
    public Matrix multiplication(Matrix matrix);
    public Matrix minus(Matrix matrix);
    public Matrix inv();
    public double[][] getArray();
}

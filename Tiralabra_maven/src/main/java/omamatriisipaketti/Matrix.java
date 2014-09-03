package omamatriisipaketti;


public interface Matrix {

    public Matrix smoni(double skalaari);
    public Matrix plus(Matrix matrix) throws Exception;
    public double det() throws Exception;
    public Matrix kerro(Matrix matrix) throws Exception;
    public Matrix minus(Matrix matrix) throws Exception;
    public Matrix inv() throws Exception;
    public double[][] getArray();
    public Matrix rref() throws Exception;
    public LUPdecomposition lup() throws Exception;
    public int getN();
    public int getM();
    public void print();
}

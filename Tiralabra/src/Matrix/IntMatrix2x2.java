package Matrix;

public class IntMatrix2x2 extends Matrix2x2<Types.Integer> {
    private static Types.Integer[][] toASD(int[][] values) {
        Types.Integer[][] res = new Types.Integer[2][2];
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                res[i][j] = new Types.Integer(values[i][j]);
            }
        }
        
        return res;
    }
    
    public IntMatrix2x2(int[][] values) {
        super(toASD(values));
    }
}

package Matrix;

import Types.ASDInteger;

public class IntMatrix2x2 extends Matrix2x2<ASDInteger> {
    private static ASDInteger[][] toASD(int[][] values) {
        ASDInteger[][] res = new ASDInteger[2][2];
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                res[i][j] = new ASDInteger(values[i][j]);
            }
        }
        
        return res;
    }
    
    public IntMatrix2x2(int[][] values) {
        super(toASD(values));
    }
}

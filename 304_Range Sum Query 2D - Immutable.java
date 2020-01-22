//1.0 2d prefix sum O(m*n)&O(1)/O(m*n)
class NumMatrix {
    private int n = 0, m = 0;
    private int[][] rangeSum = null;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        m = matrix.length; n = matrix[0].length;
        rangeSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                rangeSum[i + 1][j + 1] = rangeSum[i + 1][j] + rangeSum[i][j + 1] - rangeSum[i][j] + matrix[i][j];        
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return rangeSum[row2 + 1][col2 + 1] - rangeSum[row2 + 1][col1] - rangeSum[row1][col2 + 1] + rangeSum[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
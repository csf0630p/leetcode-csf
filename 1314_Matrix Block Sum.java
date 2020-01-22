// https://leetcode.com/problems/matrix-block-sum/discuss/477041/Java-Prefix-sum-with-Picture-explain-Clean-code-O(m*n)
// https://leetcode.com/problems/matrix-block-sum/discuss/477036/JavaPython-3-PrefixRange-sum-w-analysis-similar-to-LC-30478
// 1.0 2d prefix sum O(m*n)/O(m*n)
class Solution {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length, n = mat[0].length;
        int[][] rangeSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                rangeSum[i + 1][j + 1] = rangeSum[i + 1][j] + rangeSum[i][j + 1] - rangeSum[i][j] + mat[i][j];
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j) {
                int r1 = Math.max(0, i - K), c1 = Math.max(0, j - K), r2 = Math.min(m, i + K + 1), c2 = Math.min(n, j + K + 1);
                ans[i][j] = rangeSum[r2][c2] - rangeSum[r2][c1] - rangeSum[r1][c2] + rangeSum[r1][c1];
            }
        return ans;
    }
}


// wrong, need to sum up all integers in rectangle
class Solution {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length, n = mat[i].length;
        int[][] sumRow = new int[m + 1][n + 1], 
            sumCol = new int[m + 1][n + 1],
            res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                sumRow[i][j] = sumRow[i][j - 1] + mat[i][j - 1];
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 1; i <= m; i++) {            
                sumCol[i][j] = sumCol[i - 1][j] + mat[i - 1][j];
            }
        }       
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = ((i + k < m) ? sumRow[m] : sumRow[i + k + 1]) -
                    ((i - k >= 0) ? 0 : sumRow[i - k]) +
                    ((j + k < n) ? sumCol[n] : sumCol[j + k + 1]) -
                    ((j - k >= 0) ? 0 : sumRow[j - k]) -
                    mat[i][j];                
            }
        }        
        return res;
    }
}
// 1 stack with leetcode 84, O(mn)/O(mn)
class Solution { 
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = matrix[i][j]-'0';
                if (dp[i][j] > 0 && i>0) dp[i][j] += dp[i - 1][j];
            }
        }
        int max = 0;
        for (int[] a : dp) max=Math.max(largestRectangleArea(a), max);
        return max;
    }
    
    // copied "Largest Rectangle in Histogram" solution
    public int largestRectangleArea(int[] a) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i <= a.length; i++) {
            while (!stack.isEmpty() && (i == a.length || a[stack.peek()] > a[i])) {
                int height = a[stack.pop()];
                int width = (!stack.isEmpty()) ? i - stack.peek() - 1 : i;
                max = Math.max(max, height * width);
            }
            stack.push(i);
        }
        return max;
    }
}

// 2 dp

// 不要试图用预处理的DP算法（计算sum[i][j]），这样仍需要四重循环，时间复杂度太大。
// wrong
class Solution {
    int res = 0;
    char[][] memo = null;
    
    public int maximalRectangle(char[][] matrix) {
        memo = new char [matrix.length][matrix[].length];
        dfs(matrix, matrix.length - 1, matrix[].length - 1);
    }
    
    void dfs(char[][] matrix, int row, int column) {
        if (memo[row][column] != 0) return memo[row][column];
        
    }
}
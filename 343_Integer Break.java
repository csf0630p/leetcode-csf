// 1.0 O(n^2)/O(n), dp
class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }
        return dp[n];        
    }
}

// 2.0 O(logn)或O(1)/O(logn)或O(1) 取决于幂运算pow
    public int integerBreak(int n) {
        if(n <= 3) return n - 1;
        if(n % 3 == 0) 
            return (int)Math.pow(3, n / 3);
        else if(n % 3 == 1) 
            return 4 * (int)Math.pow(3, (n - 4) / 3);
        else // (n % 3 == 2)
            return 2 * (int)Math.pow(3, n / 3);            
    }
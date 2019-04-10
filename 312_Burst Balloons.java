class Solution {
    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x  iNums) 
            nums[n++] = x;
        nums[0] = nums[n] = 1;


        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i  n; i++)
            for (int j = 0; j  n; j++)
                dp[i][j] = 0;
                
        for (int k = 0; k  n - 1; ++k)
            for (int left = 1; left  n - k; ++left) {
                int right = left + k;
                for (int i = left; i = right; ++i) {
                    dp[left][right] = Math.max(dp[left][right], 
                    nums[left - 1]  nums[i]  nums[right + 1] + dp[left][i - 1] + dp[i + 1][right]);
                }
            }

        return dp[1][n - 1];
    }
}
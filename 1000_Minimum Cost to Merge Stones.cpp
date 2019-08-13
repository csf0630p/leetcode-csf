// https://www.youtube.com/watch?v=FabkoUzs64o
// https://zxi.mytechroad.com/blog/?s=1000

// 1.0 O(kn^3)/O(kn^2)

// 1.1 ++m => m += k - 1, O(kn^3) => O(n^3)
/* dp[i][j][k] := min cost to merge subarray i ~ j into k piles
Init: dp[i][j][k] = 0 if i==j and k == 1 else inf
ans: dp[0][n-1][1]
transition: 
1. dp[i][j][k] = min{dp[i][m][1] + dp[m+1][j][k-1]} for all i <= m < j
2. dp[i][j][1] = dp[i][j][K] + sum(A[i]~A[j]) */
// Running time: 12 ms, 12.1 MB
class Solution {
public:
  int mergeStones(vector<int>& stones, int K) {
    const int n = stones.size();
    if ((n - 1) % (K - 1)) return -1;
    const int kInf = 1e9;    
    vector<int> sums(n + 1);
    for (int i = 0; i < stones.size(); ++i)
      sums[i + 1] = sums[i] + stones[i];
    // dp[i][j][k] := min cost to merge subarray i~j into k piles.
    vector<vector<vector<int>>> dp(n, vector<vector<int>>(n, vector<int>(K + 1, kInf)));
    for (int i = 0; i < n; ++i)
      dp[i][i][1] = 0;
    
    for (int l = 2; l <= n; ++l) // subproblem length
      for (int i = 0; i <= n - l; ++i) { // start
        int j = i + l - 1; // end
        for (int k = 2; k <= K; ++k) // piles
          for (int m = i; m < j; m += K - 1) // split point
            dp[i][j][k] = min(dp[i][j][k], dp[i][m][1] + dp[m + 1][j][k - 1]);
        dp[i][j][1] = dp[i][j][K] + sums[j + 1] - sums[i];
      }        
    return dp[0][n - 1][1];
  }
};

// 2.0 O(n^3/k)/O(n^2)
/* dp[l][i] := min cost to merge [i, i + l) into as less piles as possible. Number of merges will be (l-1) / (K – 1) and 
Transition: dp[l][i] = min(dp[m][i] + dp[l – m][i + m]) for 1 <= m < l
if ((l – 1) % (K – 1) == 0) [i, i + l) can be merged into 1 pile, dp[l][i] += sum(A[i:i+l]) */
// Author: Huahua 4ms, 9.6 MB
class Solution {
public:
  int mergeStones(vector<int>& stones, int K) {
    const int n = stones.size();
    if ((n - 1) % (K - 1)) return -1;
        
    vector<int> sums(n + 1);
    for (int i = 0; i < stones.size(); ++i) 
      sums[i + 1] = sums[i] + stones[i];
    
    const int kInf = 1e9;
    // dp[i][j] := min cost to merge subarray A[i] ~ A[j] into (j-i)%(K-1) + 1 piles
    vector<vector<int>> dp(n, vector<int>(n, kInf));
    for (int i = 0; i < n; ++i) dp[i][i] = 0;
    
    for (int l = 2; l <= n; ++l) // subproblem length 
      for (int i = 0; i <= n - l; ++i) { // start        
        int j = i + l - 1;
        for (int m = i; m < j ; m += K - 1) // split point
            dp[i][j] = min(dp[i][j], dp[i][m] + dp[m + 1][j]);
        // If the current length can be merged into 1 pile
        // The cost is independent of the merge orders.
        if ((l - 1) % (K - 1) == 0)
          dp[i][j] += sums[j + 1] - sums[i];
      }
    return dp[0][n - 1];
  }
};

// didn't finish
// f[i][i+k] = sum(s[i:i+k])
// f[i][i~i+k-1] = impossible
// f[i][j] = sum(s[i:j]) + min{f[i][k] + f[k + 1][j]}
class Solution {
    public int mergeStones(int[] stones, int K) {
        if (stones == null || stones.length < K) return -1;
        int n = stones.length;
        int[][] f = new int[n][n];
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + stones[i];
        for (int k = K - 1; k < n; k++) {
            int min = Integer.MAX_VALUE >> 2;
            for (int i = 0; i + k <= n; i++) {
                if (k == K - 1) 
                    f[i][i + k] = sum[i + k + 1] - sum[i];
                else for (int j = i + k; j < {
                    
                }
            }
        }
    }
                          
}
                          
                          
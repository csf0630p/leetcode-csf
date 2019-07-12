// O(m*n)/O(m*n)
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 2; i <= n; i++) { 
            dp[0][i] = (p.charAt(i - 1) == '*') && dp[0][i - 2];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*' && j > 1) {
                    if (p.charAt(j - 2) == '.' || s.charAt(i - 1) == p.charAt(j - 2)) {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        // for (int i = 0; i <= m; i++) {
        //     for (int j = 0; j <= n; j++)
        //         System.out.print(String.valueOf(j) + (dp[i][j] ? "T " : "F "));
        //     System.out.println();
        // }
        return dp[m][n];
    }
}

// dp[i][j] =
//     dp[i-1][j-1], if s[i]=p[j] or p[j]=.
//     dp[i][j-2], if p[j]=* and !(s[i]=p[j-1] or p[j-1]=.)
//     dp[i-1][j] or dp[i][j-2], if p[j]=* and (s[i]=p[j-1] or p[j-1]=.)
// offset for s,p : -1

// dp[0][0] = T
// dp[1~M][0] = F
// dp[0][1] = F
// dp[0][j:2~N] = dp[0][j-2], if p[j]=* 
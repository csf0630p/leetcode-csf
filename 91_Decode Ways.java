// 1.0 dp O(n)/O(n)
	public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        if(s.charAt(0) != '0') dp[1] = 1;
        
        for(int i = 2; i < len + 1; i ++){
            if(s.charAt(i - 1) != '0')
                dp[i] += dp[i - 1];
            int val = Integer.valueOf(s.substring(i - 2, i));
            if(val >= 10 && val <= 26)
                dp[i] += dp[i - 2];
        }
        return dp[len];
    }
	
// 1.1 dp O(n)/O(1)
class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        int dp0 = 1, dp1 = (s.charAt(0) != '0') ? 1 : 0, dp2 = 0;        
        
        for(int i = 2; i <= len; i++){
            dp2 = 0;
            if(s.charAt(i - 1) != '0')
                dp2 += dp1;
            int val = Integer.valueOf(s.substring(i - 2, i));
            if(val >= 10 && val <= 26)
                dp2 += dp0;
            dp0 = dp1; dp1 = dp2;
        }
        return dp1;
    }
}

// 2.0 dfs+memo(dp) O(n)/O(1)
class Solution {
    private int[] cache;
    
    public int numDecodings(String s) {
        cache = new int[s.length()];
        Arrays.fill(cache, -1);
        return dfs(s, 0);
    }

    public int dfs(String s, int idx) {
        if (idx >= s.length()) return 1;
        if (cache[idx] !=  -1) return cache[idx];
        int n = 0;
        for (int i = idx; i < s.length() && i - idx <= 2; i++) {
            if (s.charAt(idx) != '0' && Integer.parseInt(s.substring(idx, i + 1)) < 27) {
                n += dfs(s, i + 1);
            }
        }
        cache[idx] = n;
        return n;
    }

    
}
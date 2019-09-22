// 1.0 DP O(n)/O(n);
// DFA: s1[i - 1] --sell--> s2[i]
//      s2[i - 1] --buy-> s3[i]
//      s3[i - 1] --sell--> s4[i]      

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[] s1 = new int[n + 1], s2 = new int[n + 1], s3 = new int[n + 1], s4 = new int[n + 1];
        s1[0] = s3[0] = (Integer.MIN_VALUE >> 2);
        for (int i = 1; i <= n; i++) {
            s1[i] = Math.max(-prices[i - 1], s1[i - 1]);
            s2[i] = s2[i - 1];
            if (i > 1) s2[i] = Math.max(s1[i - 1] + prices[i - 1], s2[i - 1]);
            s3[i] = s3[i - 1];
            if (i > 2) s3[i] = Math.max(s2[i - 1] - prices[i - 1], s3[i - 1]);
            s4[i] = s4[i - 1];
            if (i > 3) s4[i] = Math.max(s3[i - 1] + prices[i - 1], s4[i - 1]);
            // System.out.println(i + " " + s1[i] + " " + s2[i] + " " + s3[i] + " " + s4[i]);
        }
        return Math.max(0, Math.max(s2[n], s4[n]));
    }
}

// 1.1 DP O(n)/O(1)
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39611/Is-it-Best-Solution-with-O(n)-O(1).
    public int maxProfit(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        for(int i:prices){                              // Assume we only have 0 money at first
            release2 = Math.max(release2, hold2+i);     // The maximum if we've just sold 2nd stock so far.
            hold2    = Math.max(hold2,    release1-i);  // The maximum if we've just buy  2nd stock so far.
            release1 = Math.max(release1, hold1+i);     // The maximum if we've just sold 1nd stock so far.
            hold1    = Math.max(hold1,    -i);          // The maximum if we've just buy  1st stock so far. 
        }
        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
    }

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39615/My-explanation-for-O(N)-solution!
	public int maxProfit(int[] prices) {
		int sell1 = 0, sell2 = 0, buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
		for (int i = 0; i < prices.length; i++) {
			buy1 = Math.max(buy1, -prices[i]);
			sell1 = Math.max(sell1, buy1 + prices[i]);
			buy2 = Math.max(buy2, sell1 - prices[i]);
			sell2 = Math.max(sell2, buy2 + prices[i]);
		}
		return sell2;
	}
	
// extend: dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j-1]), j=[0..i-1]
// For k transactions, on i-th day,
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/135704/Detail-explanation-of-DP-solution

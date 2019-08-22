// https://blog.csdn.net/zjuPeco/article/details/76468185
// 1.0 dp, state machine thinking, O(n)/O(n)
/*  s0[i] = max(s0[i - 1], s2[i - 1])
    s1[i] = max(s0[i - 1] - prices[i], s1[i - 1])
    s2[i] = s1[i - 1] + prices[i]
    s0 —— sell后rest或者rest后rest
    s1 —— rest后的buy或者buy后的rest
    s2 —— rest后的sell */

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[] s0 = new int[n], s1 = new int[n], s2 = new int[n];
        s0[0] = 0; s1[0] = -prices[0]; s2[0] = Integer.MIN_VALUE >> 2;
        for (int i = 1; i < n; i++) {
            s0[i] = Math.max(s0[i - 1], s2[i - 1]);
            s1[i] = Math.max(s0[i - 1] - prices[i], s1[i - 1]);
            s2[i] = s1[i - 1] + prices[i];
        }
        return Math.max(s0[n - 1], s2[n - 1]);
    }
}

// 1.1 O(n)/O(1)
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int sold = 0, hold = Integer.MIN_VALUE, rest = 0;
        for (int i=0; i<prices.length; ++i) {
            int prvSold = sold;
            sold = hold + prices[i];
            hold = Math.max(hold, rest-prices[i]);
            rest = Math.max(rest, prvSold);
        }
        return Math.max(sold, rest);
    }
}        
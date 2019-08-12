//my thoughts:
//f[i] = nums[i] - min{f[j=0..i-1]} + f[j], i=0..n
//return max{f[0..n]}
//other's:
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/39479/Reduction-to-greedy-from-DP
//对于 [a, b, c, d]，如果有 a <= b <= c <= d ，那么最大收益为 d - a。而 d - a = (d - c) + (c - b) + (b - a) ，因此当访问到一个 prices[i] 且 prices[i] - prices[i-1] > 0，那么就把 prices[i] - prices[i-1] 添加到收益中。
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        // if (prices == null || prices.length < 2) return 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                res += prices[i] - prices[i - 1];
        }
        return res;
    }
}
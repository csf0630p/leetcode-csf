// 1.0 DP, O(n)/O(n)
// Soldi=Max(Soldi−1,Holdi−1+Pricesi−fee); Holdi=Max(Holdi,Soldi−1−Pricesi)
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // if (n < 2) return 0;
        int[] sold = new int[n];
        int[] hold = new int[n];
        hold[0] = -prices[0];
        for (int i = 1; i < n; i++){
            sold[i] = Math.max(sold[i-1], hold[i-1] + prices[i] - fee);
            hold[i] = Math.max(hold[i-1], sold[i-1] - prices[i]);
        }
        return sold[n-1];        
    }
}

// 1.1 DP, O(n)/O(1)
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int sold = 0;
        int hold = -prices[0];
        for (int i = 1; i < n; i++){
            int soldpre = sold;
            sold = Math.max(soldpre, hold + prices[i] - fee);
            hold = Math.max(hold, soldpre - prices[i]);
        }
        return sold;
    }
	
// 2.0 Greedy, O(n)/O(1)
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int profit=0, curProfit=0;
        int minP=prices[0], maxP=prices[0];
        for(int i=1;i<prices.length;i++){
            minP = Math.min(minP,prices[i]);
            maxP = Math.max(maxP,prices[i]);
            curProfit = Math.max(curProfit,prices[i]-minP-fee);
            if((maxP-prices[i])>=fee){//can just sell the stock at maxP day.
                profit += curProfit;
                curProfit = 0;
                minP = prices[i];
                maxP = prices[i];
            }
        }
        return profit + curProfit;
    }
}
// https://leetcode.com/problems/dice-roll-simulation/discuss/403736/ChineseC%2B%2B-1223.
// 1.0 memo dfs(dp), O(n*15*6)/O(n*15*6)
// 状态定义为f(k, index, n)，代表数字index在前面出现k次时，再掷n次骰子会出现多少序列。
// f(k, index, n) = 五个 f(1, newindex, n-1) + 一个合法的f(k+1, index, n-1)
class Solution {
    typedef long long LL;
    LL cache[5002][16][6];
    LL mod = 1000000000 + 7;
    vector<int> rollMax;
    LL dfs(int k, int index, int n){
        if(n == 0){
            return 1;
        }
        if(cache[n][k][index] != -1){
            return cache[n][k][index];
        }
        
        LL ans = 0;
        for(int i = 0; i < 6; i++){
            if(index != i){
                ans += dfs(1, i, n-1); //无关数字
            }else if(k < rollMax[i]){
                ans += dfs(k+1, i, n-1); //还没到达限制
            }else{
				//忽略
			}
        }
        return cache[n][k][index] = ans % mod;
    }
    
public:
    int dieSimulator(int n, vector<int>& rollMax) {
        this->rollMax =  rollMax;
        LL ans = 0;
        memset(cache, -1, sizeof(cache));
        return dfs(0, 0, n); //这里把起始状态也当做依赖状态，不过这个依赖等价于没有依赖
    }
};

//1.1 dp 
class Solution {
    private int mod = 1000000007;
    public int dieSimulator(int n, int[] rollMax) {        
        if(n == 1) {
            return rollMax.length;
        }
        
        // dp[i][j] number of valid combination with i number of rolls and dice end with j+1 value.
        // dp[i][rollMax.length] total number of valid combinations with i number of rolls. 
        long[][] dp = new long[n+1][rollMax.length + 1]; 
        for(int i = 0; i < rollMax.length; ++i) {
            dp[1][i] = 1;
        }
        
        // when there is 0 rolls there is only one combination (empty)
        dp[0][rollMax.length] = 1;   
        // total number with 1 roll; 
        dp[1][rollMax.length] = rollMax.length; 
        
        long total = 0;
        long totalEndWithCurNum = 0;
        for(int i = 2; i <= n; ++i) {
            total = 0;
            for(int j = 0; j < rollMax.length; ++j) {
                totalEndWithCurNum = 0; 
                // based on max allowed occurence to calculate the possible combinations end with j + 1
                for(int k = 1; k <= rollMax[j] && i >= k; ++k) {
					// dp[i-k][rollMax.length] - dp[i-k][j]  is number of combinations that i-k rolls have that does not end with j+1
                    // the rest k rolls with have consistent value of j + 1
                    // in this way, the new combination is still valid.
		            totalEndWithCurNum += (dp[i-k][rollMax.length] - dp[i-k][j] + this.mod) % this.mod;
                    totalEndWithCurNum %= this.mod;
                }
                
                total += totalEndWithCurNum; 
                total %= this.mod;
                dp[i][j] = totalEndWithCurNum;
            }
            
            dp[i][rollMax.length] = total;
        }
        
        return (int)total;
    }
}
// 1.0 dp O(n)/O(n)
// https://blog.csdn.net/qq508618087/article/details/50863010
class Solution {
    public int numWays(int n, int k) {
        if(n == 0 || k == 0) return 0;
        int[] dp1 = new int[n+1], dp2 = new int[n+1];
        dp1[1] = 0;
        dp2[1] = k;
        for(int i = 2; i <= n; i++) {
            dp1[i] = dp2[i-1];
            dp2[i] = (dp1[i-1] + dp2[i-1]) * (k-1);
        }
        return dp1[n] + dp2[n];
    }
}

// 1.1 dp O(n)/O(1)
class Solution {
    public int numWays(int n, int k) {
        if(n==0 || k==0 || (k==1 && n>2)) return 0;
        int same = 0, diff = k, total = k;
        for(int i = 2; i <= n; i++)
        {
            same = diff; diff = (k-1)*total;
            total = same + diff;
        }
        return total;
    }
}

// 1.2 dfs+memo O(n)/O(3*n)
// https://leetcode.jp/leetcode-276-paint-fence-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/
int[][] memo; // 记忆数组
public int numWays(int n, int k) {
    // 例外情况返回0
    if(n==0 || k==0 || k==1 && n>2) return 0;
    // 初始化记忆数组
    memo=new int[n][3];
    // 递归求解
    return help(n,k,0,0);
}
 
int help(int n, int k, int index, int sameCount){
    // 如果越界，返回1
    if(index==n) return 1;
    // 如果记忆数组中存在当前解，返回记忆数组中的值
    if(memo[index][sameCount]>0) return memo[index][sameCount];
    // 返回值
    int count=0;
    // 如果连续相同颜色数小于等于1，
    if(sameCount<=1){
        // 选择与之前相同的颜色，连续相同颜色数加一
        count+= help(n, k, index+1, sameCount+1);
    }
    // 选择与之前不同的颜色，有k-1中选法
    // 此时连续相同颜色为1
    count+= (k-1)*help(n, k, index+1, 1);
    // 将结果保存至记忆数组
    memo[index][sameCount]=count;
    return count;
}



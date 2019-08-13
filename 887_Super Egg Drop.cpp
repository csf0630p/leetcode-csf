// https://www.1point3acres.com/bbs/thread-542696-1-1.html
/*1. f[i][j][k]表示经过测试，我们已知X的范围是[i,j]，并且现在剩余k个鸡蛋，问至少需要多少次试验才能知道正确的X。

得到状态表示后，状态转移实际上是一件十分机械化的事情：枚举你在这个状态下所有可以做的事情，找到里面的最优解。
所以我们再考虑，根据这个状态，我们能做什么呢——选择一个楼层，然后扔鸡蛋；对于这个题，你还需要考扔鸡蛋的结果：破了，或是没破。如果在l层破了，那么我们就知道最后结果在[l + 1, j]中，并且我们浪费了一个鸡蛋，所以答案是由f[l + 1][j][k - 1]转移过来的；如果没破，则知道最后结果在[i, l]中，并且鸡蛋数量保持不变，所以是由f[i][l][k]转移过来的。由于我们要考虑最坏情况，所以实际上需要的是得到一个最大值的最小值，这样的话，状态转移就已经很清楚了：

f[i][j][k] = 1 + min(max(f[i][l - 1][k - 1] , f[l][j][k])

状态数是N^2K，状态转移O(N)，最终复杂度O(N^3K)，边界条件很简单，当i=j时，我们就知道答案了。

2. 去除重复信息，这个需要对状态内容进行考量，以这个题目来说，我们发现当k不变，j-i的值固定时，结果实际上是一样的，因为我们可以把所有[i,j]层视作[0,j-i]层！那么，根据这一点我们就可以将这个维度给抹去，得到新的状态表达：
f[i][k]表示剩下k个鸡蛋，范围被缩减到i层，需要的最少实验次数。
根据这个转化，可以得到新的递推方程：
f[i][k] = 1 + min(max(f[l - 1][k - 1], f[i - l][k]))

通过简化状态，我们将空间复杂度降低为O(NK)，时间复杂度降低为O(N^2K)
*/
/* https://www.cnblogs.com/Phantom01/p/9490508.html
p[n][k] = min{ max(dp[i-1][k-1], dp[n-i][k]) + 1 } (1 <= i <= n) */
const int MAXK = 100, MAXN = 100;

int max(int a, int b) {return a > b ? a : b;}
int min(int a, int b) {return a < b ? a : b;}

int superEggDrop(int K, int N) {
    int dp[MAXN+2][MAXK+2];
    for (int i = 0; i <= MAXN; i++) {
        dp[i][0] = 0;
        dp[i][1] = i;
    }
    for (int j = 2; j <= MAXK; j++) {
        for (int i = 1; i <= MAXN; i++) {
            dp[i][j] = i;
            for (int k = 1; k < i; k++) {
                dp[i][j] = min(dp[i][j], max(dp[k-1][j-1], dp[i-k][j]) + 1);
            }
        }
    }
    return dp[N][K];
}

//https://www.cnblogs.com/grandyang/p/11048142.html
// 若我们仔细观察 dp[i - 1][k - 1] 和 dp[i][j - k]，可以发现前者是随着k递增，后者是随着k递减，且每次变化的值最多为1，所以只要存在某个k值使得二者相等，那么就能得到最优解，否则取最相近的两个k值做比较，由于这种单调性，我们可以在 [1, j] 范围内对k进行二分查找，找到第一个使得 dp[i - 1][k - 1] 不小于 dp[i][j - k] 的k值，然后用这个k值去更新 dp[i][j] 即可，这样时间复杂度就减少到了 O(KNlgN) 
class Solution {
public:
    int superEggDrop(int K, int N) {
        vector<vector<int>> dp(K + 1, vector<int>(N + 1));
        for (int j = 1; j <= N; ++j) dp[1][j] = j;
        for (int i = 2; i <= K; ++i) {
            for (int j = 1; j <= N; ++j) {
                dp[i][j] = j;
                int left = 1, right = j;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (dp[i - 1][mid - 1] < dp[i][j - mid]) left = mid + 1;
                    else right = mid;
                }
                dp[i][j] = min(dp[i][j], max(dp[i - 1][right - 1], dp[i][j - right]) + 1);
            }
        }
        return dp[K][N];
    }
};

//进一步来想，对于固定的k，dp[i][j-k] 会随着j的增加而增加，最优决策点也会随着j单调递增，所以在每次移动j后，从上一次的最优决策点的位置来继续向后查找最优点即可，这样时间复杂度就优化到了 O(KN)，我们使用一个变量s表示当前的j值下的的最优决策点，然后当j值改变了，我们用一个 while 循环，来找到第下一个最优决策点s，使得 dp[i - 1][s - 1] 不小于 dp[i][j - s]，参见代码如下：
class Solution {
public:
    int superEggDrop(int K, int N) {
        vector<vector<int>> dp(K + 1, vector<int>(N + 1));
        for (int j = 1; j <= N; ++j) dp[1][j] = j;
        for (int i = 2; i <= K; ++i) {
            int s = 1;
            for (int j = 1; j <= N; ++j) {
                dp[i][j] = j;
                while (s < j && dp[i - 1][s - 1] < dp[i][j - s]) ++s;
                dp[i][j] = min(dp[i][j], max(dp[i - 1][s - 1], dp[i][j - s]) + 1);
            }
        }
        return dp[K][N];
    }
};

/*其实我们还可以进一步优化时间复杂度到 O(KlgN)，不过就比较难想到了，需要将问题转化一下，变成已知鸡蛋个数，和操作次数，求最多能测多少层楼的临界点。还是使用动态规划 Dynamic Programming 来做，用一个二维 DP 数组，其中 dp[i][j] 表示当有i次操作，且有j个鸡蛋时能测出的最高的楼层数。再来考虑状态转移方程如何写，由于 dp[i][j] 表示的是在第i次移动且使用第j个鸡蛋测试第 dp[i-1][j-1]+1 层，因为上一个状态是第i-1次移动，且用第j-1个鸡蛋。此时还是有两种情况：

鸡蛋碎掉：说明至少可以测到的不会碎的层数就是 dp[i-1][j-1]。
鸡蛋没碎：那这个鸡蛋可以继续利用，此时我们还可以再向上查找 dp[i-1][j] 层。
那么加上当前层，总共可以通过i次操作和j个鸡蛋查找的层数范围是 [0, dp[i-1][j-1] + dp[i-1][j] + 1]，这样就可以得到状态转移方程如下：
dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] + 1
当 dp[i][K] 正好小于N的时候，i就是我们要求的最小次数 */

class Solution {
public:
    int superEggDrop(int K, int N) {
        vector<vector<int>> dp(N + 1, vector<int>(K + 1));
        int m = 0;
        while (dp[m][K] < N) {
            ++m;
            for (int j = 1; j <= K; ++j) {
                dp[m][j] = dp[m - 1][j - 1] + dp[m - 1][j] + 1;
            }
        }
        return m;
    }
};

/* https://www.cnblogs.com/Phantom01/p/9490508.html
dp[k][0] = 0
dp[1][m] = m (m > 0)
dp[k][m] = dp[k-1][m-1] + dp[k][m-1] + 1 (k > 0, m>0)*/
int superEggDrop(int K, int N) {
    int dp[N+2][K+2];
    memset(dp, 0, sizeof(dp));
    dp[0][0] = 0;
    for (int m = 1; m <= N; m++) {
        dp[m][0] = 0;
        for (int k = 1; k <= K; k++) {
            dp[m][k] = dp[m-1][k] + dp[m-1][k-1] + 1;
            if (dp[m][k] >= N) {
                return m;
            }
        }
    }
    return N;
}

// 可以进一步的优化空间，因为当前的操作次数值的更新只跟上一次操作次数有关，所以我们并不需要保存所有的次数，可以使用一个一维数组，其中 dp[i] 表示当前次数下使用i个鸡蛋可以测出的最高楼层。状态转移方程的推导思路还是跟上面一样
class Solution {
public:
    int superEggDrop(int K, int N) {
        vector<int> dp(K + 1);
        int res = 0;
        for (; dp[K] < N; ++res) {
            for (int i = K; i > 0; --i) {
                dp[i] = dp[i] + dp[i - 1] + 1;
            }
        }
        return res;
    }
};

//解法五：数学推导公式, O(1)
//method 1: dp
// // Sub-problem and state:
// let dp[i][j] denote at stone i, the frog can or cannot make jump of size j

// // Recurrence relation:
// for any j < i,
// dist = stones[i] - stones[j];
// if dp[j][dist]:
//     dp[i][dist - 1] = ture
//     dp[i][dist] = ture
//     dp[i][dist + 1] = ture

class Solution {
    public boolean canCross(int[] stones) {
        int N = stones.length;
        boolean[][] dp = new boolean[N][N + 1];
        dp[0][1] = true;        
        for(int i = 1; i < N; ++i){
            for(int j = 0; j < i; ++j){
                int diff = stones[i] - stones[j];
                if(diff < 0 || diff > N || !dp[j][diff]) 
                    continue;
                dp[i][diff] = true;
                if(diff - 1 >= 0) 
                    dp[i][diff - 1] = true;
                if(diff + 1 <= N)
                    dp[i][diff + 1] = true;
                if(i == N - 1) 
                    return true;
            }
        }
        return false;
    }
}

//method 2: dfs
class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {return false;}
        int n = stones.length;
        if (n == 1) {return true;}
        if (stones[1] != 1) {return false;}
        if (n == 2) {return true;}
        int last = stones[n - 1];
        HashSet<Integer> hs = new HashSet();
        for (int i = 0; i < n; i++) {
            if (i > 3 && stones[i] > stones[i - 1] * 2) {return false;} // The two stones are too far away. 
            hs.add(stones[i]);
        }
        return canReach(hs, last, 1, 1);
    }
    
    private boolean canReach(HashSet<Integer> hs, int last, int pos, int jump) {
        if (pos + jump - 1 == last || pos + jump == last || pos + jump + 1 == last) {
            return true;
        }
        if (hs.contains(pos + jump + 1)) {
            if (canReach(hs, last, pos + jump + 1, jump + 1)) {return true;}
        }
        if (hs.contains(pos + jump)) {
            if (canReach(hs, last, pos + jump, jump)) {return true;}
        }
        if (jump > 1 && hs.contains(pos + jump - 1)) {
            if (canReach(hs, last, pos + jump - 1, jump - 1)) {return true;}
        }
        return false;
    }
}
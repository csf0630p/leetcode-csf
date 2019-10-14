// 1.0 dp O(n^3)/O(n^2)
class Solution {
    public int balancedStringSplit(String s) {
      int n = s.length();
      int[] sum = new int[n + 1];
      int[][] dp = new int[n][n];
      for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + (s.charAt(i - 1) == 'R' ? 1 : -1);
      // for (int id = 0; id <= n; id++) System.out.print(id + " " + sum[id] + ";");
      // System.out.println();
      for (int i = 1; i < n; i += 2){
          for (int j = 0; j + i < n; j += 2) {
              if (sum[i + j + 1] - sum[j] == 0) {
                  dp[j][j + i] = 1;
                  if (i > 1) {
                      for (int k = j + 1; k < j + i; k += 2) {
                          if (dp[j][k] + dp[k + 1][j + i] > dp[j][j + i])
                              dp[j][j + i] = dp[j][k] + dp[k + 1][j + i];
                      }
                  }
              }
          }
        // for (int id = 0; id + i < n; id++) System.out.print(id + " " + dp[id][id + i] + ";");
        // System.out.println(i);
      }
      return dp[0][n - 1];
    }
}

//2.0 greedy O(n)/O(1)
public int balancedStringSplit(String s) {
    int res = 0, cnt = 0;
    for (int i = 0; i < s.length(); ++i) {
        cnt += s.charAt(i) == 'L' ? 1 : -1;
        if (cnt == 0) ++res;
    }
    return res;             
}    

//2.1 greedy & stack O(n)/O(n)
    public int balancedStringSplit(String s) {
        Stack<Character> stack = new Stack();
        int res = 0;
        //going over string chars
        for (char ch : s.toCharArray()) {
            //check if char doesn't balance the previous one, or the stack is empty
            if (stack.isEmpty() || stack.peek() == ch)
                stack.push(ch);
            //if chars are balanced - remove the pair
            else
                stack.pop();
            //if stack is empty - all pairs are balanced and we have a balanced substring
            if (stack.isEmpty()) 
                res++;
        }
        return res;
    }
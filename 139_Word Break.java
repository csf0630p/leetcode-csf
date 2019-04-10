// dp: f[0][j] = || {f[0][0 ~ j-1] && inDict f[1 ~ j]}
// return f[0][n], compress to 1d

class Solution {    
    public boolean wordBreak(String s, List<String> wordDict) {
        int l = s.length();
        int[] f = new int[l];
        Set<String> set = new HashSet<>(wordDict);
        return dfs(s, set, f, l - 1);
    }
    
    private boolean dfs(String s, Set<String> set, int[] f, int end) {
        if (f[end] == 1) 
            return true;
        else if (f[end] == -1)
            return false;
        if (set.contains(s.substring(0, end + 1))) {
            f[end] = 1;
            return true;   
        }                     
        for (int i = 0; i < end; i++) 
            if (dfs(s, set, f, i) && set.contains(s.substring(i + 1, end + 1))) {
                f[end] = 1;
                return true;                  
            }
        f[end] = -1;    
        return false;
    }
}

//todo: use Boolean obj = null to maintain 3-state
//todo2: use Tabulation replace Memorization

class Solution {    
    public boolean wordBreak(String s, List<String> wordDict) {
      if (s == null || s.length() == 0) return false;  
      int n = s.length();
      Set<String> dict = new HashSet<>(wordDict);
      boolean[] dp = new boolean[n];  
      // dp[i] represents whether s[0...i] can be formed by dict

      for (int i = 0; i < n; i++) {
        for (int j = 0; j <= i; j++) {
          String sub = s.substring(j, i + 1);      
          if (dict.contains(sub) && (j == 0 || dp[j - 1])) {
            dp[i] = true;
            break;
          }
        }
      }  
      return dp[n - 1];
    }
}

// Approach 3: Using Breadth-First-Search
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == 0) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = 1;
            }
        }
        return false;
    }
}
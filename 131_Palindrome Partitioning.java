// recursive
class Solution {
    public List<List<String>> partition(String s) {
       List<List<String>> list = new ArrayList<>();
       backtrack(list, new ArrayList<>(), s, 0);
       return list;
    }

    public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
       if(start == s.length())
          list.add(new ArrayList<>(tempList));
       else{
          for(int i = start; i < s.length(); i++){
             if(isPalindrome(s, start, i)){
                tempList.add(s.substring(start, i + 1));
                backtrack(list, tempList, s, i + 1);
                tempList.remove(tempList.size() - 1);
             }
          }
       }
    }

    public boolean isPalindrome(String s, int low, int high){
       while(low < high)
          if(s.charAt(low++) != s.charAt(high--)) return false;
       return true;
    } 
}

// https://www.cnblogs.com/grandyang/p/4270008.html
// recursive with original method
class Solution {
public:
    vector<vector<string>> partition(string s) {
        vector<vector<string>> res;
        if (s.empty()) return {{}};
        for (int i = 0; i < s.size(); ++i) {
            if (!isPalindrome(s, i + 1)) continue;
            for (auto list : partition(s.substr(i + 1))) {
                list.insert(list.begin(), s.substr(0, i + 1));
                res.push_back(list);
            }
        }
        return res;
    }
    bool isPalindrome(string s, int n) {
        for (int i = 0; i < n / 2; ++i) {
            if (s[i] != s[n - 1 - i]) return false;
        }
        return true;
    }
};

// dp, memo dfs
class Solution {
public:
    vector<vector<string>> partition(string s) {
        int n = s.size();
        vector<vector<string>> res;
        vector<string> out;
        vector<vector<bool>> dp(n, vector<bool>(n));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (s[i] == s[j] && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }
        }
        helper(s, 0, dp, out, res);
        return res;
    }
    void helper(string s, int start, vector<vector<bool>>& dp, vector<string>& out, vector<vector<string>>& res) {
        if (start == s.size()) { res.push_back(out); return; }
        for (int i = start; i < s.size(); ++i) {
            if (!dp[start][i]) continue;
            out.push_back(s.substr(start, i - start + 1));
            helper(s, i + 1, dp, out, res);
            out.pop_back();
        }
    }
};
// method 3.2
class Solution {
    public List<List<String>> partition(String s) {        
       List<List<String>> list = new ArrayList<>();
        if (s == "") return list;
        int[][] dp = new int[s.length()][s.length()];
       backtrack(list, new ArrayList<>(), s, 0, dp);
       return list;
    }

    public void backtrack(List<List<String>> list, List<String> tempList, String s, int start, int[][] dp){
       if(start == s.length())
          list.add(new ArrayList<>(tempList));
       else{
          for(int i = start; i < s.length(); i++){
             if(isPalindrome(s, start, i, dp)){
                tempList.add(s.substring(start, i + 1));
                backtrack(list, tempList, s, i + 1, dp);
                tempList.remove(tempList.size() - 1);
             }
          }
       }
    }

    public boolean isPalindrome(String s, int low, int high, int[][] dp){
        if (dp[low][high] == 1) 
            return true;
        else if (dp[low][high] == -1) 
            return false;
        else {
            int l = low, h = high;            
            while(low < high){
              if(s.charAt(low++) != s.charAt(high--)) {
                  dp[l][h] = -1;
                  return false;
              }
            }
           dp[l][h] = 1; 
           return true;
        }
    } 
}

// dp, interative
class Solution {
public:
    vector<vector<string>> partition(string s) {
        int n = s.size();
        vector<vector<vector<string>>> res(n + 1);
        res[0].push_back({});
        vector<vector<bool>> dp(n, vector<bool>(n));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (s[i] == s[j] && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    string cur = s.substr(j, i - j + 1);
                    for (auto list : res[j]) {
                        list.push_back(cur);
                        res[i + 1].push_back(list);
                    }
                }
            }
        }
        return res[n];
    }
};
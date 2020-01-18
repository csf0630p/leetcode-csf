// https://www.acwing.com/file_system/file/content/whole/index/content/304613/
// https://leetcode.com/problems/distinct-echo-substrings/discuss/477217/Java-Brute-force-and-Hash-Solution-Clean-code
// 1.0 Brute force + HashSet for results O(n^3)/O(n? n^2?)
class Solution {
    public int distinctEchoSubstrings(String str) {
        HashSet<String> set = new HashSet<>();
        int n = str.length();
        for (int i = 0; i < n; i++) {
            for (int len = 2; i + len <= n; len += 2) {
                int mid = i + len / 2;
                String subStr1 = str.substring(i, mid);
                String subStr2 = str.substring(mid, i + len);
                if (subStr1.equals(subStr2)) set.add(subStr1);
            }
        }
        return set.size();
    }
}

// 2.0 rolling hash O(n^2)/O(n? n^2?) collision?
class Solution {
    long BASE = 29L, MOD = 1000000007L;
    public int distinctEchoSubstrings(String str) {
        HashSet<Long> set = new HashSet<>();
        int n = str.length();
        long[] hash = new long[n + 1]; // hash[i] is hash value from str[0..i]
        long[] pow = new long[n + 1]; // pow[i] = BASE^i
        pow[0] = 1;
        for (int i = 1; i <= n; i++) {
            hash[i] = (hash[i - 1] * BASE + str.charAt(i - 1)) % MOD;
            pow[i] = pow[i - 1] * BASE % MOD;
        }
        for (int i = 0; i < n; i++) {
            for (int len = 2; i + len <= n; len += 2) {
                int mid = i + len / 2;
                long hash1 = getHash(i, mid, hash, pow);
                long hash2 = getHash(mid, i + len, hash, pow);
                if (hash1 == hash2) set.add(hash1);
            }
        }
        return set.size();
    }

    long getHash(int l, int r, long[] hash, long[] pow) {
        return (hash[r] - hash[l] * pow[r - l] % MOD + MOD) % MOD;
    }
}

// https://leetcode.com/problems/distinct-echo-substrings/discuss/477060/Accepted-O(n2)-rolling-hash-solution
// 2.1 double hash

// https://leetcode.com/problems/distinct-echo-substrings/discuss/478284/C%2B%2B-Using-a-trie%3A-O(n)-without-hash-collision-issues
// 3.0 trie + hash O(n^2)/O(n)

// https://leetcode.com/problems/distinct-echo-substrings/discuss/478854/C%2B%2B-DP-solution-O(N2)-with-explanation
// 4.0 DP O(n^2? n^3?)/O(n^2)

// wrong, must distinct, can't deal it with dp
class Solution {
    public int distinctEchoSubstrings(String text) {
        char[] txt = text.toCharArray();
        int len = txt.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len - 1; i++) dp[i][i + 1] = (txt[i] == txt[i + 1]) ? 1 : 0;
        for (int k = 2; k < len; k++) {
            for (int i = 0; i < len - k; i++) {
                if (k % 2 == 0) {
                    
                } else {
                    
                }
            }
        }
        return dp[0][len - 1];
    }
}

ab -> (b)ab(a)
aa -> 
bcab -> (a)b[c][a]b(c)
bdcabd -> (a)bd[c][a]bd(c)    
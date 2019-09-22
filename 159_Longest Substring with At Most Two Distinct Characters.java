// like 904 fruit into baskets
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int res = 0, cur = 0, count_b = 0;
        char a = 0, b = 0;
        for (char c : s.toCharArray()) {
            cur = (c == a || c == b ? cur + 1 : count_b + 1);
            count_b = (c == b ? count_b + 1 : 1);
            if (b != c) {a = b; b = c;}
            res = Math.max(res, cur);
        }
        return res;        
    }
}
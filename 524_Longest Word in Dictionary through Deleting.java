// two pointers to isSubStr, greedy: result must come from targets, O(len(s)*len(d))/O(len(res))
class Solution {
    public String findLongestWord(String s, List<String> d) {
        String res = "";
        for (String target : d) {
            if (isSubstr(s, target)) {
                int l1 = res.length(), l2 = target.length();                
                if (l1 < l2 || (l1 == l2 && res.compareTo(target) > 0)) {
                    res = target;
                }                             
            }
        }
        return res;
    }

    private boolean isSubstr(String s, String target) {
        int i = 0, j = 0;
        while (i < s.length() && j < target.length()) {
            if (s.charAt(i) == target.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == target.length();
    }
}
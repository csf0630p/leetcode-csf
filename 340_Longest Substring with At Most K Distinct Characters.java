// https://aaronice.gitbooks.io/lintcode/two_pointers/longest_substring_with_at_most_k_distinct_characters.html
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        HashMap<Character, Integer> count = new HashMap<>();
        int n = s.length();
        int firstIdx = 0;
        char[] chs = s.toCharArray();

        int longest = 0;

        for (int i = 0; i < n; i++) {
            count.put(chs[i], count.getOrDefault(chs[i], 0) + 1);
            while (count.size() > k) {
                count.put(chs[firstIdx], count.get(chs[firstIdx]) - 1);
                if (count.get(chs[firstIdx]) == 0) {
                    count.remove(chs[firstIdx]);
                }
                firstIdx++;
            }
            longest = Math.max(longest, i - firstIdx + 1);
        }
        return longest;
    }
}

//wrong
import java.util.*;

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;
        Map<Character, Integer> count = new HashMap<>();
        Map<Integer, Character> firstAppear = new TreeMap<>();
        int n = s.length(), firstIdx = 0, longest = 0;
        char[] chs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (count.containsKey(chs[i])) {
                count.put(chs[i], count.get(chs[i]) + 1);                
            } else {
                count.put(chs[i], 0);
                firstAppear.put(i, chs[i]);
            }             
            if (count.size() > k) {
                Map.Entry<Integer, Character> first = firstAppear.firstEntry();
                Map.Entry<Integer, Character> second = firstAppear.higherEntry(first.getKey());
                if (second.getKey() - first.getKey() == count.get(first.getValue())) {
                    count.remove(first.getValue());
                    firstAppear.remove(first.getKey());
                    firstIdx = second.getKey();
                } else {
                    count.put(first.getValue(), count.get(first.getValue()) - (second.getKey() - first.getKey()));
                    firstAppear.remove(first.getKey());
                    firstIdx = second.getKey();
                    while (count.size() > k) {
                        count.put(chs[firstIdx], count.get(chs[firstIdx]) - 1);
                        if (count.get(chs[firstIdx]) == 0) {
                            count.remove(chs[firstIdx]);
                        }
                        firstAppear.remove(firstIdx);
                        firstAppear.put(firstIdx + 1, chs[firstIdx + 1]);
                        firstIdx++;
                    }
                }
            }
            longest = Math.max(longest, i - firstIdx + 1);
        }
        return longest;        
    }
}

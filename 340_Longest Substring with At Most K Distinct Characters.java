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

// 1.0 two pointers/slide window
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // 如果字符串长度为0或者k为0，返回0
        if(s.length()==0 || k==0) return 0;
        // 将字符串转为字符数组
        char[] arr = s.toCharArray();
        // 区间内字符种类
        int distinct = 0;
        // 记录区间内出现过的每种字符的个数
        Map<Character, Integer> count=new HashMap<>();
        // 左指针
        int left=0;
        // 返回结果
        int res=0;
        // 从0向右移动右指针
        for(int right=0;right<arr.length;right++){
            // 当前右指针字符
            char rightChar=arr[right];
            // 查看该字符在区间内的个数
            int c = count.getOrDefault(rightChar,0);
            // 个数为0，说明当前是第一次出现，字符种类加一
            if(c==0) distinct++;
            // 更新当前字符在区间内出现的次数
            count.put(rightChar, c+1);
            // 如果字符种类小于等于k，当前区间为合理区间
            if(distinct<=k){
                // 更新合理区间最大值
                res=Math.max(res, right-left+1);
            }else{
                // 当前为非合理区间
                while(distinct>k){
                    // 左指针指向的字符
                    char leftChar=arr[left];
                    // 该字符区间内出现的次数
                    int leftCount = count.getOrDefault(leftChar,0);
                    // 移除该字符
                    count.put(leftChar,leftCount-1);
                    // 如果该字符数量为0，说明区间内不在包含该字符
                    // 字符种类减一
                    if(leftCount-1==0) distinct--;
                    // 否则继续移动左指针
                    left++;
                }
            }
        }
        return res;
    }
	
//1.1 
public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int num = 0, i = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) num++;
            if (num > k) {
                while (--count[s.charAt(i++)] > 0);
                num--;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}

//2.0 Java O(nlogk) using TreeMap to keep last occurrence Interview "follow-up" question!
//https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/discuss/80044/Java-O(nlogk)-using-TreeMap-to-keep-last-occurrence-Interview-%22follow-up%22-question!
// I would say using a linkedHashMap data structure makes the performance O(n).

public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int lo = 0, hi = 0;
        int n = s.length();
        int max = 0;
        if (k == 0) return 0;
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        
        while (hi < n) {
            char ch = s.charAt(hi);
            if (map.containsKey(ch) || map.size() < k) {
                map.remove(ch);
                map.put(ch, hi);
                max = Math.max(max, hi++ - lo + 1);
            }
            else {
                Character key = map.keySet().iterator().next();
                lo = map.get(key);
                map.remove(key);
                lo++;
            }
        }
        return max;
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

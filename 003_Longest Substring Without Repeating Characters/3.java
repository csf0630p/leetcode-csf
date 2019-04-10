// 双指针O(n)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l = s.length();
        if (l == 0)
            return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int count = 0, max = 0, start = -1;
        for (int i = 0; i < l; i++) {
            if (map.containsKey(s.charAt(i))) {
                int pre = map.get(s.charAt(i));
                if (pre >= start) 
                    start = pre;             
            }
            count = i - start;                
            if (count > max)
                max = count;                        
            map.put(s.charAt(i), i);            
        }
        return max;
    }
}
// 012345678
// xyazbcaxb
// 0~5
// 3~6
// 3~7
// 5~8

//效率低 存在remove hashmap, 大于O(n)
import java.util.*;
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l = s.length();
        if (l == 0)
            return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int count = 0, max = 0, start = 0;
        for (int i = 0; i < l; i++) {
            if (map.containsKey(s.charAt(i))) {
                int pre = map.get(s.charAt(i));
                count = i - pre;
                for (int j = start; j < pre; j++) {
                    map.remove(s.charAt(j));
                }
                start = pre + 1;
            } else {
                count++;
                if (count > max)
                    max = count;
            }
            // System.out.print(count + " ");
            map.put(s.charAt(i), i);
            // if ((i == 6) || (i==5))
            //     System.out.print("-----" + map + "-----");
        }
        return max;
    }
}
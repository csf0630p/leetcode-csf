//O(n)/O(n)
class Solution {
    public String minWindow(String s, String t) {
        char[] str = s.toCharArray(), target = t.toCharArray();
        int[] table = new int[128];
        int count = 0;
        for (char ch : target) {
            if (table[ch] == 0) count++;
            table[ch]--;
        }
        int slow = 0, fast = 0, minSlow = 0, minFast = Integer.MAX_VALUE;
        for (; fast <= str.length; fast++) {
            if (count == 0) {
                for (; count == 0; slow++) {
                    if (table[str[slow]] == 0) count++;   
                    table[str[slow]]--;                                    
                }
                if (fast - slow + 1 < minFast - minSlow) {
                    minSlow = slow - 1;
                    minFast = fast;
                }                
                // System.out.println(slow + " " + fast + " " + count);
                // System.out.println(String.valueOf(Arrays.copyOfRange(str, slow, fast)));
            }
            if (fast == str.length) break;
            table[str[fast]]++;
            if (table[str[fast]] == 0) count--;
        }        
        return minFast == Integer.MAX_VALUE ? "" : String.valueOf(Arrays.copyOfRange(str, minSlow, minFast));
    }
}
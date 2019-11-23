//1.0 O(n)/O(n)
public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();//<sum, index>
        map.put(0, -1);//or we can in loop use if(sum == k),len = i+1; since longest will be from begin of array 
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(!map.containsKey(sum))
                map.put(sum, i);
            if(map.containsKey(sum - k)){
                len = Math.max(len , i - map.get(sum - k));
            }
        }
        return len;
    }
}

//1.1 O(n)/O(n)
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        
        int prefixSum = 0, max = 0;
        Map<Integer, Integer> indexByPrefixSum = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (prefixSum == k) //special case -- if it sums to k this must be the longest!
                max = i + 1;
            else if (indexByPrefixSum.containsKey(prefixSum - k)) 
                max = Math.max(max, i - indexByPrefixSum.get(prefixSum - k));
            if (!indexByPrefixSum.containsKey(prefixSum))
                indexByPrefixSum.put(prefixSum, i); //since we want the longest length, don't update the index if there is a more recent entry (i.e. preserve the existing one)
        }
        return max;
    }
}

//1.11
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                max = i + 1;
            }
            if (map.containsKey(k - sum)) {
                max = Math.max(max, i - map.get(k - sum));
            } 
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }   
        }
        return max;
    }
}
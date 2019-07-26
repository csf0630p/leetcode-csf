// O(n*k)/O(k), update 0~k-1 (nums[0~i-1] involved) complement every time
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) return false;
        if (k == 0) {
            for (int i = 0; i < nums.length - 1; i++)
                if (nums[i] + nums[i + 1] == 0) return true;
            return false;
        }
        if (k < 0) k = -k;
        Map<Integer, Integer> comp = new HashMap<>();
        comp.put(nums[0] % k, 0);
        for (int i = 1; i < nums.length; i++) {            
            int target = (k - nums[i]) % k;
            if (comp.containsKey(target)) return true;
            Map<Integer, Integer> newComp = new HashMap<>();
            newComp.put(nums[i] % k, i);
            for (Map.Entry<Integer, Integer> e : comp.entrySet()) {                
                int key = e.getKey(), value = e.getValue();
                newComp.put((nums[i] + key) % k, value);
            }
            comp = newComp;
            // System.out.println(comp);
        }
        return false;
    }
}

//O(n^2)/O(1)
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        for(int i = 0; i < nums.length-1; i++) {
            int sum = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                if ((k == 0 && sum == 0) || (k != 0 && sum % k == 0)) 
                    return true;
            }
        }
        return false;
    }
}

//O(n)/min{O(n), O(k)}, if sum[0~j] and sum[0~i] mod k have same remainder, then sum[i~j] % k == 0
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) return false;
        if (k == 0) {
            for (int i = 0; i < nums.length - 1; i++)
                if (nums[i] + nums[i + 1] == 0) return true;
            return false;
        }
        if (k < 0) k = -k;
        // Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // map.put(0,-1);
        int runningSum = 0;
        for (int i=0; i<nums.length; i++) {
            runningSum = (runningSum + nums[i]) % k;
            // if (i > 0 && runningSum == 0) return true;
            int prev = map.getOrDefault(runningSum, -2);
            if (prev != -2) {
                if (i - prev > 1) 
                    return true;
            }else 
                map.put(runningSum, i);
        }
        return false;
    }
}

//
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) return false;
        // Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,-1);
        int runningSum = 0;
        for (int i=0; i<nums.length; i++) {
            runningSum = (k == 0) ? (runningSum + nums[i]) : (runningSum + nums[i]) % k;
            int prev = map.getOrDefault(runningSum, -2);
            if (prev != -2) {
                if (i - prev > 1) 
                    return true;
            }else 
                map.put(runningSum, i);
        }
        return false;
    }
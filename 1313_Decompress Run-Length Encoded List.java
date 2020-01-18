// O(n)/O(n)
class Solution {
    public int[] decompressRLElist(int[] nums) {
        int len = 0, pt = 0;
        for (int i = 0; i < nums.length; i += 2) len += nums[i];        
        int[] res = new int[len];
        for (int i = 0; i < nums.length; i += 2) {
            for (int j = 0; j < nums[i]; j++) {
                res[pt++] = nums[i + 1];
            }
        }
        return res;        
    }
}

// wrong, can't change orders
class Solution {
    public int[] decompressRLElist(int[] nums) {
        int[] map = new int[101];
        int len = 0, pt = 0;
        for (int i = 0; i < nums.length; i += 2) {
            map[nums[i + 1]] += nums[i];
            len += nums[i];
        }
        int[] res = new int[len];
        for (int i = 1; i <= 100; i++) {
            for (int j = 0; j < map[i]; j++) {
                res[pt++] = i;
            }
        }
        return res;
    }
}
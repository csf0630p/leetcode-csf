// 1.0 dp O(n)/O(n)
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];        
        return Math.max(helper(nums, 0, nums.length - 1),
                        helper(nums, 1, nums.length));
    }
    
    private int helper(int[] nums, int p, int q) {
        int[] f = new int[q];
        f[p] = nums[p];
        if (q - p > 1)
            f[p + 1] = Math.max(nums[p], nums[p + 1]);
        for (int i = p + 2; i < q; i++) {
            f[i] = Math.max(f[i-2] + nums[i], f[i-1]);
        }
        return f[q - 1];
    }    
}

// 1.1 O(n)/O(1)
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];        
        return Math.max(helper(nums, 0, nums.length - 1),
                        helper(nums, 1, nums.length));
    }
    
    private int helper(int[] nums, int p, int q) {
		int pre2 = 0, pre1 = 0;
		for (int i = first; i <= last; i++) {
			int cur = Math.max(pre1, pre2 + nums[i]);
			pre2 = pre1;
			pre1 = cur;
		}
		return pre1;
    }    
}

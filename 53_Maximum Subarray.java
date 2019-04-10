//method 1: dp, O(n)/O(n)
class Solution {
    public int maxSubArray(int[] nums) {
        // f[i] = max(f[i-1]+n[i], n[i])
        int[] f = new int[nums.length];
        f[0] = nums[0];
        int max = f[0];
        for (int i = 1; i < nums.length; i++) {
            f[i] = Math.max(f[i - 1] + nums[i], nums[i]);
            max = (max < f[i]) ? f[i] : max;
        }
        return max;
    }
}

//method 2: dp, O(n)/O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int f = nums[0];
        int max = f;
        for (int i = 1; i < nums.length; i++) {
            f = Math.max(f + nums[i], nums[i]);
            max = Math.max(f, max);
        }
        return max;
    }
}

//method 3: divide and conquer,	O(nlogn)/O(logn)
class Solution {
    public int maxSubArray(int[] nums) {
        return sum(nums, 0, nums.length - 1);
    }
    
    int sum(int[] nums, int i, int j) {
        if (i == j)
            return nums[i];
        int mid = i + (j - i) / 2;
        int left = sum(nums, i, mid);
        int right = sum(nums, mid + 1, j);
        int cross = 0, temp = 0, max = Integer.MIN_VALUE;
        for (int k = mid + 1; k <= j; k++) {
            temp += nums[k];
            max = Math.max(temp, max);
        }
        cross = max; temp = 0; max = Integer.MIN_VALUE;
        for (int k = mid; k >= i; k--) {
            temp += nums[k];
            max = Math.max(temp, max);
        }
        cross += max;
        return Math.max(cross, Math.max(left, right));
    }
}
class Solution {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0)return -1;
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            if(j - i <= 1) 
                return nums[i] < nums[j] ? nums[i] : nums[j];
            int mid = i + (j - i) / 2;
            if(nums[mid] < nums[j]) 
                j = mid;
            else {//nums[mid] >= nums[j]
                i = mid;
            }
        }
        return -1;        
    }
}

//Method 2:
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/discuss/48499/4ms-simple-C%2B%2B-code-with-explanation
class Solution {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0)return -1;
        int i = 0, j = nums.length - 1;
        while(i < j) {
            int mid = i + (j - i) / 2;
            if(nums[mid] < nums[j]) 
                j = mid;
            else {//nums[mid] > nums[j]
                i = mid + 1;
            }
        }
        return nums[i];        
    }
}
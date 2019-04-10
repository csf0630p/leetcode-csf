class Solution {
    public int findMin(int[] nums) {
        // if(nums == null || nums.length == 0)return -1;
        int i = 0, j = nums.length - 1;
        while(i < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] < nums[j])
                j = mid;
            else if (nums[mid] > nums[j])
                i = mid + 1;
            else { //num[mid]==num[j]
                if (nums[mid] == nums[i]) {
                    i++;
                    j--;
                } else
                    j = mid;
            }
        }
        return nums[i];            
    }
}
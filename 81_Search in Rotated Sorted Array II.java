//https://www.youtube.com/watch?v=eG27FBcmy1k
class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0)return false;
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            if(i == j) 
                return nums[i] == target? true : false;
            int mid = i + (j - i) / 2;
            if(nums[mid] == target)
                return true;
            if(nums[mid] < nums[j]) {
                if(target < nums[mid] || target > nums[j])
                    j = mid - 1;
                else 
                    i = mid + 1;
            }
            else if (nums[mid] > nums[j]){
                if(target > nums[mid] || target < nums[i])
                    i = mid + 1;
                else 
                    j = mid - 1;
            } else { //nums[mid] == nums[j] we don't know where is the breakpoint now
                j--;
            }
        }
        return false;        
    }
}
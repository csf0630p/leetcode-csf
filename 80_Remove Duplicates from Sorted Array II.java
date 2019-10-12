// https://leetcode.wang/leetCode-80-Remove-Duplicates-from-Sorted-ArrayII.html
// 1.0
 class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {return 0;}
        int slow = 0, flag = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] && flag == 0) {
                flag = 1;
                slow++;
            } else if (nums[i] != nums[i - 1]) {
                flag = 0;
                slow++;
            }
            nums[slow] = nums[i];
        }
        return slow + 1;
    }
}
 
// 2.0
    public int removeDuplicates(int[] nums) {
        int id = 2;
        for(int i = 2; i < nums.length; ++i) 
            if (nums[i] != nums[id - 2])
                nums[id++] = nums[i];
        return id;        
    }
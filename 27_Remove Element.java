class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[slow] = nums[i];
            if (nums[i] != val) slow++;
        }
        return slow;
    }
}
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {            
            int target = 0 - nums[i], j = i + 1, k = nums.length - 1;
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    j++;
                    while (j < k && nums[k] == nums[k - 1]) k--;
                    k--;
                } else if (nums[j] + nums[k] < target) {
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    j++;                    
                } else {
                    while (j < k && nums[k] == nums[k - 1]) k--;
                    k--;                    
                }                    
            }
        }
        return res;
    }
}
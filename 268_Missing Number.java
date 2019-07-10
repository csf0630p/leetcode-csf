// O(n)/O(1)
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length, sum = (1 + n) * n / 2;
        for (Integer id : nums) sum -= id;
        return sum;
    }
}

// O(n)/O(1)
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length, sum = n; //0 xor n = n
        for (int i = 0; i < n; i++) {
            sum ^= (i ^ nums[i]);
        }
        return sum;
    }
}
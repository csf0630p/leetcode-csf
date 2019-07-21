// 1.0 use division
class Solution {
    public int[] productExceptSelf(int[] nums) {
        long sum = 1;
        int numOfZero = 0;
        for (Integer id : nums) {
            if (id == 0)
                numOfZero++;
            else
                sum *= id;
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (numOfZero == 0)
                ans[i] = (int)(sum / (long)nums[i]);                
            else if (numOfZero == 1)
                if (nums[i] == 0)
                    ans[i] = (int)sum;
                else
                    ans[i] = 0;
            else 
                ans[i] = 0;
        }
        return ans;
    }
}

//2.0 use left and right part to multiply
// a   b   c   d
// 1   a   ab  abc
// bcd cd  d   1
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int left = 1, right = 1, n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);
        for (int i = 0; i < n; i++) {
            res[i] *= left;
            left *= nums[i];
        }
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}
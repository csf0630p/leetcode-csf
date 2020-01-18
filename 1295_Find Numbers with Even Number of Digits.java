class Solution {
    public int findNumbers(int[] nums) {
        int cnt = 0;
        for (int num : nums) {
            // System.out.println((Math.log(num) / Math.log(10)) + " " + (int)(Math.log(num) / Math.log(10)) % 2);
            if ((int)(Math.log(num + 0.5) / Math.log(10)) % 2 == 1)
                cnt++;
        }
        return cnt;
    }
}
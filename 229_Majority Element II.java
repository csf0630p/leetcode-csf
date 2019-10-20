// Moore voting
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int count1 = 0, count2 = 0, num1 = 0, num2 = 0;
        for (int idx : nums) {
            if (idx == num1) {
                count1++;
            } else if (idx == num2) {
                count2++;
            } else if (count1 == 0) {
                num1 = idx;
                count1++;
            } else if (count2 == 0) {
                num2 = idx;
                count2++;
            } else {
                count1--; count2--;
            }
        }
        count1 = 0; count2 = 0;
        for (int idx : nums) {
            if (idx == num1) {
                count1++;
            } else if (idx == num2) {
                count2++;
            } 
        }
        List<Integer> res = new ArrayList<>();
        if (count1 > nums.length / 3) res.add(num1);
        if (count2 > nums.length / 3) res.add(num2);
        return res;
    }
}

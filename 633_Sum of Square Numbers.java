class Solution {
    public boolean judgeSquareSum(int c) {
        int a = 0, b = (int)Math.sqrt(c);
        while (a <= b) {
            int sum = a * a + b * b;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                b--;
            } else { // sum < c 
                a++;
            }
        }
        return false;
    }
}
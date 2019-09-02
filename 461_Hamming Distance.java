class Solution {
    public int hammingDistance(int x, int y) {
        int temp = x ^ y, res = 0;
        while (temp > 0) {
            res += temp & 1;
            temp >>= 1;
        }
        return res;
    }
}
class Solution {
    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        bits[0] = 0;
        for (int i = 1; i <= num; i++) {
            bits[i] = (i & 1) + bits[i >> 1];
        }
        return bits;
    }
}
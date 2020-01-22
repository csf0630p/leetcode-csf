class Solution {
    public int[] sumZero(int n) {
        int[] res = new int[n];
        int i = 0, pt = 1;
        if (n % 2 == 1) res[i++] = 0;
        for (; i < n; i += 2) {
            res[i] = pt;
            res[i + 1] = -pt;
            pt++;
        }
        return res;
    }
}
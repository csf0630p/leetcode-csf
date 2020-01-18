// https://leetcode.com/problems/xor-queries-of-a-subarray/discuss/470787/JavaC%2B%2BPython-Straight-Forward-Solution
// https://leetcode.com/problems/xor-queries-of-a-subarray/discuss/474351/pythonc%2B%2Bjava-prefix-xor
class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] ^= arr[i - 1];
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            res[i] = q[0] > 0 ? arr[q[1]] ^ arr[q[0] - 1] : arr[q[1]];
        }
        return res;
    }
}

s[i] = a[0] + a[1] .. a[i]
s[i] - s[j] = a[j + 1] + a[j + 2] ... a[i]

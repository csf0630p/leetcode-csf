// if (bit in c == 0) {
//     cnt += bit in a + bit in b
// } else (bit in c == 1) {
//     cnt += (bit in a | bit in b) ? 0 : 1
// }

class Solution {
    public int minFlips(int a, int b, int c) {
        int cnt = 0;
        while (!(a == 0 && b == 0 && c == 0)) {
            int bitA = a & 1, bitB = b & 1, bitC = c & 1;
            if (bitC == 0) {
                cnt += bitA + bitB;
            } else { //(bitC == 1)
                cnt += ((bitA | bitB) == 1) ? 0 : 1;
            }
            // System.out.printf("%d %d %d %d %d %d   ", a, b, c, bitA, bitB, bitC);
            a >>= 1; b >>= 1; c >>= 1;
        }
        return cnt;
    }
}
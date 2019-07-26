O(3n)/O(2n)
class Solution {
    public int longestMountain(int[] A) {
        if ((A == null) || (A.length == 0)) return 0;
        int n = A.length;
        int[] inc = new int[n], dec = new int[n];    
        inc[0] = 1;
        for (int i = 1; i < n; i++) 
            inc[i] = (A[i] > A[i - 1]) ? inc[i - 1] + 1 : 1;
        dec[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) 
            dec[i] = (A[i] > A[i + 1]) ? dec[i + 1] + 1 : 1;   
        int max = 0;
        // for (int i = 0; i < n; i++) System.out.println(i + " " + inc[i] + " " + dec[i]);
        for (int i = 0; i < n; i++) 
            if (inc[i] >= 2 && dec[i] >= 2 && inc[i] + dec[i] - 1 > max)
                max = inc[i] + dec[i] - 1;
        return max;
    }
}

// O(n)/O(1)
    public int longestMountain(int[] A) {
        int res = 0, up = 0, down = 0;
        for (int i = 1; i < A.length; ++i) {
            if (down > 0 && A[i - 1] < A[i] || A[i - 1] == A[i]) up = down = 0;
            if (A[i - 1] < A[i]) up++;
            if (A[i - 1] > A[i]) down++;
            if (up > 0 && down > 0 && up + down + 1 > res) res = up + down + 1;
        }
        return res;
    }

// wrong
class Solution {
    public int longestMountain(int[] A) {
        if ((A == null) || (A.length == 0)) return 0;
        int n = A.length, bottom = 0, dec = 0, max = 0;
        for (int i = 1; i < n - 1; i++) {
            if (A[i] <= A[i - 1] && A[i] < A[i + 1]) 
                bottom = i;
            else if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                dec = i;
                while (dec < n - 1 && A[dec] > A[dec + 1]) dec++;
                if (dec - bottom > max) max = dec - bottom + 1;
                i = dec - 1;
            }
            // System.out.println(i + " " + bottom + " " + dec);
        }
        return max;
    }
}
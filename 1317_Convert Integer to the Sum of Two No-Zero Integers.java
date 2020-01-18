// 2ms
class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int i = 1; i <= n / 2; i++) {
            if (!(hasZero(i) || hasZero(n - i))) {
                return new int[]{i, n - i};
            }
        }
        return null;
    }
    
    boolean hasZero(int n) {
        String s = String.valueOf(n);
        return s.contains("0");
    }
}

// 0ms
class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int i = 1; i <= n / 2; i++) {
            if (!(hasZero(i) || hasZero(n - i))) {
                return new int[]{i, n - i};
            }
        }
        return null;
    }
    
    boolean hasZero(int n) {
        while (n > 0) {
            if (n % 10 == 0) return true;
            n /= 10;
        }
        return false;        
    }
}
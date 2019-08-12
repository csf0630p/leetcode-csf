// O(n)/O(1) extra space
class Solution {
    public boolean validPalindrome(String s) {
        int f = 0, r = s.length() - 1;
        while (f < r) {
            if (s.charAt(f) == s.charAt(r)) {
                f++; r--;
            } else {
                return isPalindrome(s, f + 1, r) ||
                    isPalindrome(s, f, r - 1);
            }
        }
        return true;        
    }
    
    private boolean isPalindrome(String s, int f, int r) {
        while (f < r) {
            if (s.charAt(f) == s.charAt(r)) {
                f++; r--;
            } else 
                return false;
        }
        return true;
    }    
}
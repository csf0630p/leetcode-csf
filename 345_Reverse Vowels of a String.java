class Solution {
    public String reverseVowels(String s) {
        final HashSet<Character> vowels = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        char[] res = s.toCharArray();
        int f = 0, r = res.length - 1;
        while (f < r) {
            if (vowels.contains(res[f]) && vowels.contains(res[r])) {
                char temp = res[f];
                res[f] = res[r];
                res[r] = temp;
                f++; r--;
            } else if (!vowels.contains(res[f]) && vowels.contains(res[r])) {
                f++;
            } else if (vowels.contains(res[f]) && !vowels.contains(res[r])) {
                r--;
            } else {
                f++; r--;
            }
        }
        return new String(res);
    }
}
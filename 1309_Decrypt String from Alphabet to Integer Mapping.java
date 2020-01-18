class Solution {
    public String freqAlphabets(String s) {
        int p = 0;
        StringBuilder res = new StringBuilder();
        char[] ch = s.toCharArray();
        while (p < s.length()) {            
            if ((ch[p] == '1' || ch[p] == '2') && (p < s.length() - 2) && (ch[p + 2] == '#')) {
                res.append(Character.valueOf((char)('a' + (ch[p] - '0') * 10 + (ch[p + 1] - '0') - 1)));
                p += 3;
            } else {
                res.append(Character.valueOf((char)('a' + ch[p] - '0' - 1)));
                p++;
            }
        }
        return res.toString();
    }
}

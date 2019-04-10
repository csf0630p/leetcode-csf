class Solution {
    public String addBinary(String a, String b) {
        if (a.length() > b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        StringBuilder sb = new StringBuilder(), res = new StringBuilder();
        for (int i = 0; i < b.length() - a.length(); i++) 
            sb.append("0");
        a = sb.toString() + a;
        //now two strings have same length
        int add = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            int aDigit = a.charAt(i) - '0', bDigit = b.charAt(i) - '0';
            int sum = aDigit + bDigit + add;
            if (sum >= 2) {
                add = 1;
                sum -= 2;
            } else 
                add = 0;
            res.append(String.valueOf(sum));
        }
        if (add == 1)
            res.append(String.valueOf(add));
        return res.reverse().toString();
    }
}
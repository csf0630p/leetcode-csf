O(nk)/O(n)
class Solution {
    public String removeKdigits(String num, int k) {
        if (num.length() == k) return "0";
        while (k > 0) {
            int i = 0;
            for (; i < num.length() - 1 && num.charAt(i) <= num.charAt(i + 1); i++) {}
            num = new StringBuilder(num).deleteCharAt(i).toString();
            k--;
        }        
        while (num.length() > 0 && num.charAt(0) == '0')
            num = num.substring(1, num.length());
        if (num.isEmpty()) num = "0";
        return num;
    }
}

O(n)/O(n) use stack
// https://www.youtube.com/watch?v=jCTGSjvEyo8
    public String removeKdigits(String num, int k) {
        int remain = num.length() - k;
        char[] numArray = num.toCharArray(), res = new char[remain];
        int index = 0;
        for(int i = 0; i < numArray.length; i++) {
            // (1)  (n - i > remain - index): have enough remaining digits to be compared
            // (2)  (res[index - 1] > nums[i]): at this time, the (index-1) is the newest added digit,
            //      compare this digit with the current num, if the res is greater and you have enough 
            //      remaining digits to be compared, decrease the index(it ensures that the future added digit is 
            //      always smaller than before and the size is remain) until get the right and 'safe' position
            while((numArray.length - i > remain - index) && (index > 0 && numArray[i] < res[index - 1])) index--;
            if(index < remain) res[index++] = numArray[i];
        }
        
        // check leading zeroes
        index = -1;
        while(++index < remain) {
            if(res[index] != '0') break;
        }
        String s = new String(res).substring(index);        
        return s.length() == 0 ? "0" : s;
    }
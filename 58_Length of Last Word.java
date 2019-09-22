// 1.0
class Solution {
    public int lengthOfLastWord(String s) {
        // int last = s.lastIndexOf(" "), len = s.length();
        // return s.lastIndexOf(" ") < 0 ? s.length() : s.length() - s.lastIndexOf(" ") - 1;
        String[] arr = s.split(" ");
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i].length() > 0)
                return arr[i].length();
        }
        return 0;
    }
}

// 2.0
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] arr = s.toCharArray();
        int i = arr.length - 1;
        while (i >= 0 && arr[i] == ' ') i--;
        int pt = i;
        while (i >= 0 && arr[i] != ' ') i--;
        return pt - i;
    }
	
// 3.0
public int lengthOfLastWord(String s) {
    return s.trim().length()-s.trim().lastIndexOf(" ")-1;
}
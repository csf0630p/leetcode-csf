class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>() {
            {
                this.put('I', 1);
                this.put('V', 5);
                this.put('X', 10);
                this.put('L', 50);
                this.put('C', 100);
                this.put('D', 500);
                this.put('M', 1000);
            }
        };
        
        int len = s.length();
        int result = map.get(s.charAt(len - 1));
        for (int i = len - 2; i >= 0; i--) {
            if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1)))
                result += map.get(s.charAt(i));
            else
                result -= map.get(s.charAt(i));
        }
        return result;        
    }
}
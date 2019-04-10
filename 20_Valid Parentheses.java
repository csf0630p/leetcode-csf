class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ((ch == '(') || (ch == '[') || (ch == '{'))
                st.push(ch);
            if ((ch == ')') || (ch == ']') || (ch == '}')) {
                char ch2 = 0;
                switch (ch) {
                    case ')' : 
                        ch2 = '('; break;
                    case ']' : 
                        ch2 = '['; break;
                    case '}' : 
                        ch2 = '{'; break;                        
                }
                if (st.empty())
                    return false;
                else if (st.pop() != ch2) 
                    return false;
            }                
        }
        if (st.empty())
            return true;
        else         
            return false;
    }
}
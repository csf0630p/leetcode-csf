class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new LinkedList<>();
        dfs(res, s, 0, 0, '(', ')');
        return res;
    }
    
    void dfs(List<String> res, String s, int iStart, int jStart, char open, char close){
        // int count = 0;
        // for (int i = 0; i < s.length(); i++) {
        //     if (s.charAt(i) == open) count++;
        //     if (s.charAt(i) == close) count--;
        //     if (count < 0) break;
        // }
        // if (count == 0) {
        //     res.add(s);
        //     return;
        // }        
        int numOpen = 0, numClose = 0;
        for (int i = iStart; i < s.length(); i++) {
            if (s.charAt(i) == open) numOpen++;
            if (s.charAt(i) == close) numClose++;
            if (numOpen < numClose) {
                for (int j = jStart; j <= i; j++) {
                    if (s.charAt(j) == close && (j == jStart || s.charAt(j - 1) != close))
                        dfs(res, s.substring(0, j) + s.substring(j + 1, s.length()), i, j, open, close);
                }
                return;
            }
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (open == '(')
            dfs(res, reversed, 0, 0, ')', '(');
        else
            res.add(reversed);
    }
}
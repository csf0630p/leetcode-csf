//Method 1: dfs(System Stack) avg:O(n)/O(n) worst:O(n^2)/O(n^2)
class Solution {
    public String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) return "";
        char[] exp = expression.toCharArray();
        return String.valueOf(dfs(exp, 0, exp.length - 1));
    }
    
    private char dfs(char[] exp, int start, int end) {
        if (start == end) return exp[start];
        int i = start, depth = 0;
        for (; i < end; i++) {
            if (exp[i] == '?') {
                depth++;
                char con = exp[i - 1];
                for (int j = i + 1; j < end; j++) {
                    if (exp[j] == '?') depth++;
                    if (exp[j] == ':') depth--;
                    if (depth == 0) {
                        return (con == 'T') ? dfs(exp, i + 1, j - 1) : dfs(exp, j + 1, end);
                    }
                }
            }
        }
        return 0;
    }
}

//Method 2: binary tree avg:O(nlogn)/O(nlogn) worst:O(n^2)/O(n^2)
https://leetcode.com/problems/ternary-expression-parser/discuss/92173/Java-O(n)-using-Binary-Tree
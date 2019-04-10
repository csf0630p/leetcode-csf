class Solution {
    int m = 0, n = 0;
    
    public boolean exist(char[][] board, String word) {
        m = board.length;
        if (m == 0) return false;
        n = board[0].length;
        if (n == 0) return false;        
        for (int i = 0; i < m; i++) 
            for (int j = 0; j < n; j++) 
                if (exist(board, i, j, word, 0)) return true;               
        return false;
    }
    
    private boolean exist(char[][] board, int i, int j, String word, int start) {
        // if (start >= word.length()) return true;
        if ((i < 0) || (j < 0) || (i >= m) || (j >= n)) return false;
        boolean res = false;
        if (board[i][j] == word.charAt(start)) {
            if (start == word.length() - 1) return true;
            char temp = board[i][j];    //for restore
            board[i][j] = '#';  // avoid infinite loop
            res = exist(board, i + 1, j, word, start + 1) ||
                    exist(board, i - 1, j, word, start + 1) ||
                    exist(board, i, j + 1, word, start + 1) ||
                    exist(board, i, j - 1, word, start + 1);
            // if does not use compound expression, then we have to do complete 4 search, TLE
            board[i][j] = temp;
        }
        return res;
    }    
}
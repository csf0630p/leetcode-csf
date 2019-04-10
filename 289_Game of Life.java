class Solution {
    public void gameOfLife(int[][] board) {
        if ((board.length == 0) || (board[0].length == 0))
            return;
        int n = board.length, m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int count = -board[i][j]; //和下面注释句等效
                for (int ix = Math.max(0, i - 1); ix <= Math.min(n - 1, i + 1); ix++) {
                    for (int iy = Math.max(0, j - 1); iy <= Math.min(m - 1, j + 1); iy++) {
                        // if (!((ix == i) && (iy == j)))
                            if ((board[ix][iy] & 1) == 1)
                                count++;
                    }
                }
                if ((count == 2) || (count == 3)) 
                    board[i][j] += 2;
                if (count == 3)
                    board[i][j] += 4;
            }                
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // if (((board[i][j] & 3) == 3) || (board[i][j] & 4) == 4)
                if (board[i][j] >= 3) //和上句等效
                    board[i][j] = 1;
                else
                    board[i][j] = 0;
            }
        }
        
    }
}
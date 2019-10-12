// 1.0
class Solution {
    int n;
    
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new LinkedList<>();
        this.n = n;
        dfs(ans, new int[n], new boolean[n], new boolean[2 * n], new boolean[2 * n], 0);
        return ans;            
    }
    
    void dfs(List<List<String>> ans, int[] rows, boolean[] columns, boolean[] diag1, boolean[] diag2, int idx) {
        if (idx == n) {            
            List<String> oneSol = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < n; j++) line.append((j == rows[i]) ? "Q" : ".");
                oneSol.add(line.toString());
            }
            ans.add(oneSol);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (columns[i] || diag1[idx + i + 1] || diag2[idx + n - i]) continue;      
            rows[idx] = i;
            columns[i] = true;
            diag1[idx + i + 1] = true;
            diag2[idx + n - i] = true;
            dfs(ans, rows, columns, diag1, diag2, idx + 1);
            columns[i] = false;
            diag1[idx + i + 1] = false;
            diag2[idx + n - i] = false;            
        }
    }    
}
// 1.1
public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<List<String>>();
    helper(result, new ArrayList<String>(), 0, new boolean[n], new boolean[2*n], new boolean[2*n], n);
    return result;
}

private void helper(List<List<String>> result, List<String> board, int row, boolean[] cols, boolean[] d1, boolean[] d2, int n){
    if (row == n) {
        result.add(new ArrayList<String>(board));
    }
    for (int col=0; col<n; col++){
        int id1 = col - row + n;
        int id2 = col + row;
        if (!cols[col] && !d1[id1] && !d2[id2]){
            char[] r = new char[n];
            Arrays.fill(r, '.');
            r[col] = 'Q';
            board.add(new String(r));
            cols[col] = true;
            d1[id1] = true;
            d2[id2] = true;
            helper(result, board, row+1, cols, d1, d2, n);
            board.remove(board.size()-1);
            cols[col] = false;
            d1[id1] = false;
            d2[id2] = false;
        }
    }
}
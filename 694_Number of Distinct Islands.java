// https://aaronice.gitbooks.io/lintcode/graph_search/number-of-distinct-islands.html
class Solution {
    Set<String> dict = new HashSet<String>();
    
    public int numDistinctIslands(int[][] grid) {
		if (grid == null) return 0;
		int row = grid.length;
		if (row == 0) return 0;
        int col = grid[0].length;
		int count = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 1) {           
                    StringBuilder path = new StringBuilder();
					helper(grid, i, j, row, col, path, "");            
                    if (!dict.contains(path.toString())) {
                        count++;
                        dict.add(path.toString());
                    }
                }
			}
		}
        return count;        
    }
    
	private void helper(int[][] matrix, int x, int y, int m, int n, StringBuilder path, String dir) {
        if ((x >= 0) && (y >= 0) && (x < m) && (y < n))
            if (matrix[x][y] == 1) {
                matrix[x][y] = 0;
                path.append(dir);
                helper(matrix, x - 1, y, m, n, path, "u");
                helper(matrix, x, y - 1, m, n, path, "l");
                helper(matrix, x + 1, y, m, n, path, "d");
                helper(matrix, x, y + 1, m, n, path, "r");
                path.append("b"); //back
            }         
	}    
}
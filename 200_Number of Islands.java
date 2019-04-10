//dfs, 不需要回溯, 也就是不需要在搜索后恢复原来的状态
class Solution {
    public int numIslands(char[][] matrix) {
		if(matrix == null) 
			return 0;
		int row = matrix.length;
		if (row == 0)
            return 0;
        int col = matrix[0].length;
		int count = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == '1') {
                    count++;                    
					helper(matrix, i, j, row, col);                  
                }
			}
		}
        return count;
    }
    
	private void helper(char[][] matrix, int x, int y, int m, int n) {
        if ((x >= 0) && (y >= 0) && (x < m) && (y < n))
            if (matrix[x][y] == '1') {
                matrix[x][y] = '0';
                helper(matrix, x - 1, y, m, n);
                helper(matrix, x, y - 1, m, n);
                helper(matrix, x + 1, y, m, n);
                helper(matrix, x, y + 1, m, n);
            }         
	}
}

//bfs
class Solution {
    public int numIslands(char[][] matrix) {
		if(matrix == null) 
			return 0;
		int row = matrix.length;
		if (row == 0)
            return 0;
        int col = matrix[0].length;
		int count = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == '1') {
                    count++;                                     
					helper(matrix, i, j, row, col);                  
                }
			}
		}
        return count;
    }
    
	private void helper(char[][] matrix, int x, int y, int m, int n) {
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        qx.add(x); qy.add(y);
        matrix[x][y] = '0'; 
        final int[] rowOff = new int[]{-1, 1, 0, 0};
        final int[] colOff = new int[]{0, 0, -1, 1};
        while (!qx.isEmpty()) {
            int row = qx.poll(), col = qy.poll();  
            for (int k = 0; k < rowOff.length; k++) {
                int idx = rowOff[k];
                int idy = colOff[k];
                if (((row + idx) >= 0) && ((col + idy) >= 0) && 
                    ((row + idx) < m) && ((col + idy) < n))
                    if (matrix[row + idx][col + idy] == '1') {
                        qx.add(row + idx);
                        qy.add(col + idy);
                        matrix[row + idx][col + idy] = '0';                         
                    }                            
            }
        }
	}
}


//dp解法不可行
class countIslands {
	public int Islands(char[][] matrix) {
		if(matrix == null) {
			return 0;
		}
		int row = matrix.length
		int col = matrix[0].length;
		int count = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if (matrix[i][j] == '1') {
					if (helper(matrix, i, j)
						count++;
				}
			}
		}
	}
	private void helper(char[][] matrix, int row, int col) {
		if (row == 0 && col == 0)
			return true;
		else if (row == 0 && matrix[row][col - 1] == 0)
			return true;
		else if (col == 0 && matrix[row - 1][col] == 0)
			return true;
		else if (matrix[row][col - 1] == 0 && matrix[row - 1][col] == 0)
			return true;
		return false;
	}

}

class Solution {
    public int numIslands(char[][] matrix) {
		if(matrix == null) 
			return 0;
		int row = matrix.length;
		if (row == 0)
            return 0;
        int col = matrix[0].length;
		int count = 0;
		for(int i = 0; i < row; i++) 
        {
			for(int j = 0; j < col; j++) 
            {
				if (matrix[i][j] == '1') {
                    System.out.print(i + " " + j);
					if (helper(matrix, i, j)) {
                        System.out.print("\t" + i + " " + j);
						count++;	
                    }
                    System.out.println();
                }
			}
		}
        return count;
    }
	private boolean helper(char[][] matrix, int row, int col) {
		if (row == 0 && col == 0)
			return true;
		else if (row == 0) {
            if (matrix[row][col - 1] == '0')
			    return true;
        }
		else if (col == 0) {  
            if (matrix[row - 1][col] == '0')
			    return true;
        }
		else if (matrix[row][col - 1] == '0' && matrix[row - 1][col] == '0')
			return true;
		return false;
	}
}

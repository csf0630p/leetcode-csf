// 1.0 2d array O(n!*n)/O(n^2)
// https://leetcode.wang/leetCode-52-N-QueensII.html
class Solution {
    public int totalNQueens(int n) {
        List<Integer> ans = new ArrayList<>();
        backtrack(new ArrayList<Integer>(), ans, n);
        return ans.size();
    }

    private void backtrack(List<Integer> currentQueen, List<Integer> ans, int n) {
        if (currentQueen.size() == n) {
            ans.add(1);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!currentQueen.contains(col)) {
                if (isDiagonalAttack(currentQueen, col)) {
                    continue;
                }
                currentQueen.add(col);
                backtrack(currentQueen, ans, n);
                currentQueen.remove(currentQueen.size() - 1);
            }

        }

    }

    private boolean isDiagonalAttack(List<Integer> currentQueen, int i) {
        int current_row = currentQueen.size();
        int current_col = i;
        for (int row = 0; row < currentQueen.size(); row++) {
            if (Math.abs(current_row - row) == Math.abs(current_col - currentQueen.get(row))) {
                return true;
            }
        }
        return false;
    }
}

// 2.0 backtracking with 1d array O(n!)/O(n)
class Solution {
    int count = 0;
    int n;
    
    public int totalNQueens(int n) {
        this.n = n;
        dfs(new int[n], new boolean[n], new boolean[2 * n], new boolean[2 * n], 0);
        return this.count;
    }
    
    void dfs(int[] rows, boolean[] columns, boolean[] diag1, boolean[] diag2, int idx) {
        if (idx == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (columns[i] || diag1[idx + i + 1] || diag2[idx + n - i]) continue;            
            rows[idx] = i;
            columns[i] = true;
            diag1[idx + i + 1] = true;
            diag2[idx + n - i] = true;
            dfs(rows, columns, diag1, diag2, idx + 1);
            columns[i] = false;
            diag1[idx + i + 1] = false;
            diag2[idx + n - i] = false;            
        }
    }
}

// 2.1
class Solution {
    int count = 0;
    int n;
    
    public int totalNQueens(int n) {
        this.n = n;
        dfs(new boolean[n], new boolean[2 * n], new boolean[2 * n], 0);
        return this.count;
    }
    
    void dfs(boolean[] columns, boolean[] diag1, boolean[] diag2, int idx) {
        if (idx == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (columns[i] || diag1[idx + i + 1] || diag2[idx + n - i]) continue;            
            columns[i] = true;
            diag1[idx + i + 1] = true;
            diag2[idx + n - i] = true;
            dfs(columns, diag1, diag2, idx + 1);
            columns[i] = false;
            diag1[idx + i + 1] = false;
            diag2[idx + n - i] = false;            
        }
    }
}

// 2.9 backtracking & bitmap O(n!)/O(n)
shu = pie = na = 0
count = 0

def DFS(row):
    global count, shu, pie, na
    for col in range(n):
        j = row + col; k = n - 1 - row + col
        if ((shu >> col) | (pie >> j) | (na >> k)) & 1:         # 检查冲突
            continue
        if row == n - 1:
            count += 1
        else:
            shu ^= (1 << col); pie ^= (1 << j); na ^= (1 << k)  # 标记占用
            DFS(row + 1)
            shu ^= (1 << col); pie ^= (1 << j); na ^= (1 << k)  # 清除标记

DFS(0)

// 3.0 a & -a lowbit 操作 提取出 a 中最右边一个 1 的位置
// https://zhuanlan.zhihu.com/p/22846106 解法5
class Solution {
  public int backtrack(int row, int hills, int next_row, int dales, int count, int n) {
    /** 
     row: current row to place the queen
     hills: "hill" diagonals occupation [1 = taken, 0 = free]
     next_row: free and taken slots for the next row [1 = taken, 0 = free]
     dales: "dale" diagonals occupation [1 = taken, 0 = free]
     count: number of all possible solutions
     */

    // all columns available for this board, 
    // i.e. n times '1' in binary representation
    // bin(cols) = 0b1111 for n = 4, bin(cols) = 0b111 for n = 3
    // [1 = available]
    int columns = (1 << n) - 1;

    if (row == n)   // if all n queens are already placed
      count++;  // we found one more solution
    else {
      // free columns in the current row
      // ! 0 and 1 are inversed with respect to hills, next_row and dales
      // [0 = taken, 1 = free]
      int free_columns = columns & ~(hills | next_row | dales);

      // while there's still a column to place next queen
      while (free_columns != 0) {
        // the first bit '1' in a binary form of free_columns
        // on this column we will place the current queen
        int curr_column = - free_columns & free_columns;

        // place the queen 
        // and exclude the column where the queen is placed
        free_columns ^= curr_column;

        count = backtrack(row + 1,
                (hills | curr_column) << 1,
                next_row | curr_column,
                (dales | curr_column) >> 1,
                count, n);
      }
    }

    return count;
  }
  public int totalNQueens(int n) {
    return backtrack(0, 0, 0, 0, 0, n);
  }
}
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        if (m == 0) return 0;
        int n = dungeon[0].length;
        if (n == 0) return 0;
        
        int[][] f = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if ((i == m - 1) && (j == n - 1))
                    f[i][j] = Math.max(1, 1 - dungeon[i][j]);
                else if (i == m - 1)
                    f[i][j] = Math.max(1, f[i][j + 1] - dungeon[i][j]) ;
                else if (j == n - 1)
                    f[i][j] = Math.max(1, f[i + 1][j] - dungeon[i][j]) ;
                else
                    f[i][j] = Math.max(1, Math.min(f[i][j + 1], f[i + 1][j]) - dungeon[i][j]);
                // System.out.print(f[i][j] + " ");
            }
            // System.out.println();
        }
        return f[0][0];
    }
}
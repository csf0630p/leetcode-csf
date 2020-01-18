// 1.2 O(mnk)/O(mn)
// https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/discuss/453652/Manhattan-distance-instead-of-normal-goal-check
class Solution {
    public int shortestPath(int[][] grid, int k) {
        final int m = grid.length, n = grid[0].length;
        final int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] dic = new int[m][n];
        int res = Integer.MAX_VALUE;
        Queue<Integer> qx = new LinkedList<>(), qy = new LinkedList<>(), qk = new LinkedList<>(), qd = new LinkedList<>();
        qx.add(0); qy.add(0); qd.add(0); qk.add(k);
        while (!qx.isEmpty()) {
            int x = qx.poll(), y = qy.poll(), rem = qk.poll(), d = qd.poll();
            System.out.println(x + " " + y + " " + rem + " " + d + " ");
            if ((m + n - 2 - (x + y)) <= rem)
                if (d + m + n - 2 - (x + y) < res)
                    res = d + m + n - 2 - (x + y);
            for (int i = 0; i < dir.length; i++) {
                int nx = x + dir[i][0], ny = y + dir[i][1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                int nk = (grid[nx][ny] == 1 ? rem - 1 : rem), nd = d + 1;
                if (nk < 0) continue;
                if (dic[nx][ny] < nk) {
                    dic[nx][ny] = nk;
                    qx.add(nx); qy.add(ny); qd.add(nd); qk.add(nk);
                }
            }
        }
        return (res == Integer.MAX_VALUE ? -1 : res);        
    }
}

// 1.0
// https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/discuss/451796/Java-Straightforward-BFS-O(MNK)-time-or-O(MNK)-space
class Solution {
    public int shortestPath(int[][] grid, int k) {
        int step = 0, m = grid.length, n = grid[0].length;
        int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] seen = new int[m][n]; // min obstacles elimination from (0,0) to (x, y)
        for (int i = 0; i < m; i++) {
            Arrays.fill(seen[i], Integer.MAX_VALUE);
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});
        seen[0][0] = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                if (cur[0] == m - 1 && cur[1] == n - 1) {
                    return step;
                }
                for (int[] dir : DIRS) {
                    int x = dir[0] + cur[0];
                    int y = dir[1] + cur[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }
                    int o = grid[x][y] + cur[2];
                    if (o >= seen[x][y] || o > k) {
                        continue;
                    }
                    seen[x][y] = o;
                    q.offer(new int[]{x, y, o});
                }
            }
            ++step;
        }
        return -1;  
    }
}

// 1.1
class Solution {
    public int shortestPath(int[][] grid, int k) {
        int step = 0, m = grid.length, n = grid[0].length;
        int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] seen = new int[m][n]; // min obstacles elimination from (0,0) to (x, y)
        // for (int i = 0; i < m; i++) {
        //     Arrays.fill(seen[i], Integer.MAX_VALUE);
        // }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, k + 1});
        seen[0][0] = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                // System.out.println(Arrays.toString(cur));				
                if (cur[0] == m - 1 && cur[1] == n - 1) {
                    return step;
                }
                for (int[] dir : DIRS) {
                    int x = dir[0] + cur[0];
                    int y = dir[1] + cur[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }
                    int o = - grid[x][y] + cur[2];
                    if (o <= seen[x][y] || o < 0) {
                        continue;
                    }
                    seen[x][y] = o;
                    q.offer(new int[]{x, y, o});
                }
            }
            ++step;
        }
        return -1;  
    }
}
class Solution {
    class Pair{
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null) return;
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++)
            for (int j = 0; j < rooms[0].length; j++) 
                if (rooms[i][j] == 0)
                    q.add(new Pair(i, j));   
        Pair[] offset = new Pair[]{new Pair(-1, 0), 
                                   new Pair(1, 0), 
                                   new Pair(0, -1),
                                   new Pair(0, 1)};
        while (!q.isEmpty()) {
            Pair pt = q.poll();
            for (Pair id : offset) {
                int idx = id.x + pt.x;
                int idy = id.y + pt.y;
                if ((idx < 0) || (idx >= rooms.length) || 
                    (idy < 0) || (idy >= rooms[0].length) || rooms[idx][idy] == -1)
                    continue;
                if ((rooms[idx][idy] == Integer.MAX_VALUE) || (rooms[idx][idy] > (rooms[pt.x][pt.y] + 1))) {
                    rooms[idx][idy] = rooms[pt.x][pt.y] + 1;     
                    q.add(new Pair(idx, idy));
                }
            }
        }
    }
}
//bfs+松弛 (spfa?)
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if ((maze.length == 0) || (maze[0].length == 0))
            return -1;        
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        qx.add(start[0]); qy.add(start[1]);
        maze[start[0]][start[1]] = -1;
        int res = Integer.MAX_VALUE;
        while (!qx.isEmpty()) {
            int[] pos = new int[]{qx.poll(), qy.poll()};
            if ((pos[0] == destination[0]) && (pos[1] == destination[1]))
                res = ((-maze[pos[0]][pos[1]] - 1) < res) ? 
                        (-maze[pos[0]][pos[1]] - 1) : res ;
            final int[] xOff = new int[]{-1, 1, 0, 0};
            final int[] yOff = new int[]{0, 0, -1, 1};
            for (int k = 0; k < 4; k++) {
                int[] ori = new int[]{pos[0], pos[1]};
                int count = -1;
                while ((pos[0] >= 0) && (pos[0] < maze.length) &&
                       (pos[1] >= 0) && (pos[1] < maze[0].length) &&
                       (maze[pos[0]][pos[1]] != 1)){
                    pos[0] += xOff[k];
                    pos[1] += yOff[k];
                    count++;
                }
                pos[0] -= xOff[k];
                pos[1] -= yOff[k];                     
                if ((maze[pos[0]][pos[1]] == 0) ||
                    ((maze[ori[0]][ori[1]] - count) > maze[pos[0]][pos[1]])){
                    maze[pos[0]][pos[1]] = maze[ori[0]][ori[1]] - count;
                    qx.add(pos[0]); qy.add(pos[1]);
                }
                pos = ori;
            }            
        }
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }
}
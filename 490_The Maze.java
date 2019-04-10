//dfs
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if ((maze.length == 0) || (maze[0].length == 0))
            return false;
        maze[start[0]][start[1]] = -1;
        return dfs(maze, start, destination);
    }
    
    public boolean dfs(int[][] maze, int[] pos, int[] des) {
        if ((pos[0] == des[0]) && (pos[1] == des[1]))
            // {System.out.println(pos[0] + " " + pos[1] + " a");
            return true;
        // if (maze[pos[0]][pos[1]] == -1)
        //     {System.out.println(pos[0] + " " + pos[1] + " b");            
        //     return false;}
        final int[] xOff = new int[]{-1, 1, 0, 0};
        final int[] yOff = new int[]{0, 0, -1, 1};
        for (int k = 0; k < 4; k++) {
            int[] ori = new int[]{pos[0], pos[1]};
            while ((pos[0] >= 0) && (pos[0] < maze.length) &&
                   (pos[1] >= 0) && (pos[1] < maze[0].length) &&
                   (maze[pos[0]][pos[1]] != 1)){
                pos[0] += xOff[k];
                pos[1] += yOff[k];
            }
            pos[0] -= xOff[k];
            pos[1] -= yOff[k];                     
            // System.out.println(pos[0] + " " + pos[1] + " c");
            if (maze[pos[0]][pos[1]] != -1) {
                maze[pos[0]][pos[1]] = -1;
                if (dfs(maze, pos, des))
                    // {System.out.println(pos[0] + " " + pos[1] + " d");                
                    return true;
            }
            // maze[pos[0]][pos[1]] = 0;
            pos = ori;
        }
        // System.out.println(pos[0] + " " + pos[1] + " e");
        return false;
    } 
}

class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if ((maze.length == 0) || (maze[0].length == 0))
            return false;
        maze[start[0]][start[1]] = -1;
        return dfs(maze, start, destination);
    }
    
    public boolean dfs(int[][] maze, int[] pos, int[] des) {
        if ((pos[0] == des[0]) && (pos[1] == des[1]))
            return true;
        final int[] xOff = new int[]{-1, 1, 0, 0};
        final int[] yOff = new int[]{0, 0, -1, 1};
        for (int k = 0; k < 4; k++) {
            int[] ori = new int[]{pos[0], pos[1]};
            while ((pos[0] >= 0) && (pos[0] < maze.length) &&
                   (pos[1] >= 0) && (pos[1] < maze[0].length) &&
                   (maze[pos[0]][pos[1]] != 1)){
                pos[0] += xOff[k];
                pos[1] += yOff[k];
            }
            pos[0] -= xOff[k];
            pos[1] -= yOff[k];                     
            if (maze[pos[0]][pos[1]] != -1) {
                maze[pos[0]][pos[1]] = -1;
                if (dfs(maze, pos, des))            
                    return true;
            }
            pos = ori;
        }
        return false;
    } 
}
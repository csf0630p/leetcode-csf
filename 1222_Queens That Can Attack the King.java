class Solution {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        int[][] offsets = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        List<List<Integer>> res = new LinkedList<>();
        for (int[] offset : offsets) {
            boolean flag = false;
            int dx = king[0] + offset[0], dy = king[1] + offset[1];
            while (dx >= 0 && dx < 8 && dy >= 0 && dy < 8) {
                for (int[] queen : queens) {
                    if (queen[0] == dx && queen[1] == dy) {
                        res.add(new ArrayList<Integer>(Arrays.asList(queen[0], queen[1])));                        
                        flag = true;
                        continue;
                    }
                    if (flag) break;
                }
                if (flag) break;
                dx += offset[0]; dy += offset[1];
            }
        }        
        return res;
    }
}
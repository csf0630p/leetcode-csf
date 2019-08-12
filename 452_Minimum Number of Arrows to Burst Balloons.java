//same as 435, difference : overlaps include [1,2] & [2,3], O(nlogn)/O(n)
class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;        
        Arrays.sort(points, (a, b) -> (a[1] - b[1]));
        int cnt = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) continue;
            end = points[i][1];
            cnt++;
        }
        return cnt;        
    }
}
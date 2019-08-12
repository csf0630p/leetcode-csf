// greedy O(nlogn)/O(n)
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;        
        Arrays.sort(intervals, (a, b) -> (a[1] - b[1]));
        int cnt = 1, end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) continue;
            end = intervals[i][1];
            cnt++;
        }
        return intervals.length - cnt;
    }
}

// same efficiency
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;        
        Arrays.sort(intervals, (a, b) -> ((a[1] == b[1]) ? b[0] - a[0] : a[1] - b[1]));
        int cnt = 1, end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) continue;
            end = intervals[i][1];
            cnt++;
        }
        return intervals.length - cnt;
    }
}
// 1.0 greedy, O(n^2)/O(n)
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        List<int[]> res = new LinkedList<>();
        for (int[] p : people) {
            res.add(p[1], p);
        }
        return res.toArray(new int[res.size()][]);
    }
}

// 2.0 merge sort, O(nlogn)
// https://leetcode.com/problems/queue-reconstruction-by-height/discuss/143403/O(nlogn)-modified-merge-sort-solution-with-detailed-explanation
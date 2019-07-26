// O(n)/O(n)
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) return new int[][]{};
        List<int[]> sec = new LinkedList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            int startMax = Math.max(A[i][0], B[j][0]), endMin = Math.min(A[i][1], B[j][1]);
            if (startMax <= endMin) {
                sec.add(new int[]{startMax, endMin});                
            }
            if (endMin == A[i][1]) i++;
            if (endMin == B[j][1]) j++;
        }
        return sec.toArray(new int[sec.size()][2]); // int[0][0] is also ok
    }
}
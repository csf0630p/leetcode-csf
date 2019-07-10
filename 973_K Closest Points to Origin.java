// 1.0 O(nlogn)/O(n)
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, (a, b) -> ((a[0] * a[0]) + (a[1] * a[1])) - ((b[0] * b[0]) + (b[1] * b[1])));
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i][0] = points[i][0];
            res[i][1] = points[i][1];
        }
        return res;
    }
}
// 1.1
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, (a, b) -> ((a[0] * a[0]) + (a[1] * a[1])) - ((b[0] * b[0]) + (b[1] * b[1])));
		return Arrays.copyOfRange(points, 0, K);
	}

// 2.0 heap, O(nlogk)/O(k)
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(K, (a, b) -> ((a[0] * a[0]) + (a[1] * a[1])) - ((b[0] * b[0]) + (b[1] * b[1])));
        for (int[] id : points)
            pq.add(id);
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++)
            res[i] = pq.poll();       
        return res;
    }
}

// 2.1 max heap, O(nlogk)/O(k)
// https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(K, (a, b) -> -(((a[0] * a[0]) + (a[1] * a[1])) +-((b[0] * b[0]) + (b[1] * b[1]))));
        for (int[] id : points) {
            if (pq.size() < K)
                pq.add(id);
            else {
                int[] pop = pq.peek();             
                if (((id[0] * id[0]) + (id[1] * id[1])) < ((pop[0] * pop[0]) + (pop[1] * pop[1]))) {
                    pq.poll();
                    pq.add(id); //if re-implement replace method, more efficient
                }                    
            }
        }
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++)
            res[i] = pq.poll();       
        return res;
    }
}

//3.0 quickselect, O(n) (worst O(n^2))/O(k)
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
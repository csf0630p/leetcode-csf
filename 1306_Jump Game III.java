// 1.0 bfs, without judge 0 : 0ms, with : 3ms, O(n)/O(n)
class Solution {
    public boolean canReach(int[] arr, int start) {
        // boolean hasZero = false;
        // for (Integer idx : arr) {
        //     if (idx == 0) {
        //         hasZero = true;
        //         break;
        //     }
        // }
        // if (!hasZero) return false;
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (arr[cur] == 0) return true;
            int[] next = new int[]{cur + arr[cur], cur - arr[cur]};
            for (int idx : next) {
                if (idx < arr.length && idx >= 0 && !visited[idx]) {                    
                    visited[idx] = true;
                    q.add(idx);
                }
            }
        }
        return false;
    }
}
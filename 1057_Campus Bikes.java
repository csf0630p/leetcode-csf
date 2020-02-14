// https://ttzztt.gitbooks.io/lc/sort/bucket-sort/campus-bikes.html
// 1.0 Code: Bucket Sort T: O(M *N), S: O(M * N)
	public int[] assignBikes(int[][] workers, int[][] bikes) {
        List<int[]>[] buckets = new List[2001];
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dis = Math.abs(bikes[j][0] - workers[i][0]) + Math.abs(bikes[j][1] - workers[i][1]);
                if (buckets[dis] == null) {
                    buckets[dis] = new ArrayList<>();
                }
                buckets[dis].add(new int[] {i, j});
            }
        }
        int[] res = new int[workers.length];
        Arrays.fill(res, -1);
        Set<Integer> assignedBike = new HashSet<>();
        for (int i = 0; i < buckets.length && assignedBike.size() < workers.length; i++) {
            if (buckets[i] != null) {
                for (int[] pair : buckets[i]) {
                    if (res[pair[0]] < 0 && !assignedBike.contains(pair[1])) {
                        res[pair[0]] = pair[1];
                        assignedBike.add(pair[1]);
                    }
                }
            }
        }
        return res;
    }
	
// 2.0 Code: PQ T: O(nm * lognm)
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int w = workers.length, b = bikes.length;

        int [] wo = new int[w], bi = new int [b];
        Arrays.fill(wo, -1);
        Arrays.fill(bi, -1);
        PriorityQueue<int []> pq = new PriorityQueue<>(new Comparator<int[]>(){
           @Override 
            public int compare(int [] a, int [] b){
                return a[0] != b[0] ? a[0] - b[0]
                    : (a[1] != b[1] ? a[1] - b[1]
                    : (a[2] - b[2]));
            }
        });

        // push into pq
        for (int i = 0; i < w; i ++){
            for(int j = 0; j < b; j++){
                int[] worker = workers[i], bike = bikes[j];
                int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                pq.offer(new int []{dist, i, j});
            }
        }

        // retrieve the ans
        int assigned = 0;
        while(!pq.isEmpty() && assigned < w){
            int [] entry = pq.poll();
            if (wo[entry[1]] == -1 && bi[entry[2]] == -1){
                wo[entry[1]] = entry[2];
                bi[entry[2]] = entry[1];
                assigned++; 
            }
        }

        return wo;
    }
}
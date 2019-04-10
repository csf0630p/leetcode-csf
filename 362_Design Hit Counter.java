//http://www.cnblogs.com/grandyang/p/5605552.html
//method 1: queue   hit O(1), get O(TIME), space O(TIME)
class HitCounter {
    private Queue<Integer> q;
    final int TIME = 300;

    /** Initialize your data structure here. */
    public HitCounter() {
        q = new LinkedList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        q.add(timestamp);
        if (timestamp - q.peek() >= TIME)
            q.poll();
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!q.isEmpty() && (timestamp - q.peek() >= TIME))
            q.poll();
        return q.size();
    }
}

//method 3: array mod	hit O(1), get O(TIME), space O(TIME)
class HitCounter {
    private int[] memo, count;
    final int TIME = 300;

    /** Initialize your data structure here. */
    public HitCounter() {
        memo = new int[TIME];
        count = new int[TIME];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int id = timestamp % TIME;
        if (memo[id] != timestamp) {
            memo[id] = timestamp;
            count[id] = 1;
        } else {
            count[id]++;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int res = 0;
        for (int i = 0; i < TIME; i++) 
            if (timestamp - memo[i] < TIME)
                res += count[i];
        return res;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
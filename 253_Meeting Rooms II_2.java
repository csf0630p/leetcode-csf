//method 1a: use TreeMap to store, if start then +1, else -1, scan and get maximum. O(nlogn)/O(n)
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (Interval id : intervals) {
            map.put(id.start, map.getOrDefault(id.start, 0) + 1);
            map.put(id.end, map.getOrDefault(id.end, 0) - 1);
        }
        int res = 0, room = 0;
        for (Integer id : map.values()) {
            room += id;
            res = Math.max(res, room);
        }
        return res;
    }
}

//method 1b: use bucket to store. O(length)/O(length)
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return 0; 
        int min = 0; int max = 0; 
        for(int i=0; i<intervals.length; i++){ 
            min = Math.min(min, intervals[i].start); 
            max = Math.max(max, intervals[i].end); 
        }           
        int[] count = new int[max-min+1]; 
        for(int i=0; i<intervals.length; i++){ 
            count[intervals[i].start]++; 
            count[intervals[i].end]--; 
        } 
        int maxroom = 0, num = 0; 
        for(int i=0; i<count.length; i++){ 
            num += count[i]; 
            maxroom = Math.max(maxroom, num); 
        } 
        return maxroom; 
    }
}

//method 2: split into two arrays, sort, then scan. O(nlogn)/O(n)
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        List<Integer> starts = new ArrayList<>(), ends = new ArrayList<>();
        for (Interval id : intervals) {
            starts.add(id.start);
            ends.add(id.end);
        }
		// also can use public static <T> void sort(List<T> list, Comparator<? super T> c)
        // starts.sort((a, b) -> (a - b));
        // ends.sort((a, b) -> (a - b));
        Collections.sort(starts); Collections.sort(ends);
        int res = 0, j = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (starts.get(i) < ends.get(j))
                res++;
            else 
                j++;                            
        }
        return res;
    }
}

//method 3: sort with the starts, then use heap. O(nlogn)/O(n)
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (Interval id : intervals) {
            if (!q.isEmpty() && (id.start >= q.peek()))
                q.poll();
            q.offer(id.end);
        }
        return q.size();
    }
}
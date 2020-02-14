//1.0 hashmap + heap, O(nlogk)/O(n)
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (a, b) -> a[1] - b[1]);
        for (Map.Entry<Integer, Integer> e : frequencyMap.entrySet()) {
            int key = e.getKey(), frequency = e.getValue();
            if (pq.size() < k) {
                pq.offer(new int[]{key, frequency});
            } else if (frequency > pq.peek()[1]) {
                pq.poll();
                pq.offer(new int[]{key, frequency});
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        Iterator<int[]> id = pq.iterator();
        while (id.hasNext()) {
            res.addFirst(id.next()[0]);
        }
        return res;
    }
}

//2.0 hashmap + bucket sort, O(n)/O(n)
//https://leetcode.com/problems/top-k-frequent-elements/discuss/81602/Java-O(n)-Solution-Bucket-Sort
public List<Integer> topKFrequent(int[] nums, int k) {

	List<Integer>[] bucket = new List[nums.length + 1];
	Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

	for (int n : nums) {
		frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
	}

	for (int key : frequencyMap.keySet()) {
		int frequency = frequencyMap.get(key);
		if (bucket[frequency] == null) {
			bucket[frequency] = new ArrayList<>();
		}
		bucket[frequency].add(key);
	}

	List<Integer> res = new ArrayList<>();

	for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
		if (bucket[pos] != null) {
			res.addAll(bucket[pos]);
		}
	}
	return res;
}

// 2.1 avoid generic arrays
class Solution 
    public List<Integer> topKFrequent(int[] nums, int k) {

        ArrayList<List<Integer>> bucket = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) bucket.add(new ArrayList<>());
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> e : frequencyMap.entrySet()) {
            int key = e.getKey(), frequency = e.getValue();
            bucket.get(frequency).add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = nums.length; pos >= 0 && res.size() < k; pos--) {
            if (bucket.get(pos) != null) {
                res.addAll(bucket.get(pos));
            }
        }
        return res;
    }
}
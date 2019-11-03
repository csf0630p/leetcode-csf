// 1.0 hashset O(n)/O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            //n - 1 是否存在
            if (set.contains(num - 1)) continue;
            int count = 1;
            while (set.contains(num + 1)) {
                count++;
                num += 1;
            }
            max = Math.max(max, count);            
        }
        return max;
    }
}

// 2.0 hashmap, merge intervals O(n)/O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (map.containsKey(cur)) continue;
            int left = map.getOrDefault(cur - 1, 0),
                right = map.getOrDefault(cur + 1, 0);
            int sum = left + 1 + right;
            map.put(cur, -1); //any value is ok
            map.put(cur - left, sum);
            map.put(cur + right, sum);
            max = Math.max(sum, max);            
        }
        return max;
    }
}
//method 1: hashmap 70ms
class Solution {
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        int res = 0, i = 0;
        for (int j = 0; j < tree.length; ++j) {
            count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);            
            while (count.size() > 2) {
                // System.out.println(count);
                count.put(tree[i], count.get(tree[i]) - 1);
                // System.out.println(count);
                if (count.get(tree[i]) == 0) count.remove(tree[i]);
                // System.out.println(count);
                i++;
                // System.out.println();
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}

//method 2: linkedhashmap 35ms
class Solution {
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> fruitLastPosition = new LinkedHashMap<>(2, 0.75f, true);
        int start = 0;
        int maxFruit = 0;
        for (int i = 0; i < tree.length; i++) {
            fruitLastPosition.put(tree[i], i);
            if (fruitLastPosition.size() == 3) {
                //System.out.println(fruitLastPosition);
                maxFruit = Math.max(maxFruit, i - start);
                start = fruitLastPosition.remove(fruitLastPosition.keySet().iterator().next()) + 1;
                //System.out.println(fruitLastPosition);
            }
        }
        return Math.max(maxFruit, tree.length - start);
    }
}

//method 3: 10ms
//https://leetcode.com/problems/fruit-into-baskets/discuss/170745/Problem%3A-Longest-Subarray-With-2-Elements
class Solution {
    public int totalFruit(int[] tree) {
        int res = 0, cur = 0, count_b = 0, a = 0, b = 0;
        for (int c :  tree) {
            cur = c == a || c == b ? cur + 1 : count_b + 1;
            count_b = c == b ? count_b + 1 : 1;
            if (b != c) {a = b; b = c;}
            res = Math.max(res, cur);
        }
        return res;
    }
}
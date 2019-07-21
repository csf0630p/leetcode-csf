//O(nlogn)/O(n)
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> order = new HashMap<>();
        int m = arr1.length, n = arr2.length;
        for (int i = 0; i < n; i++) {
            order.put(arr2[i], i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(arr1[i]);
        }        
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer p1, Integer p2) {     
                if (order.containsKey(p1) && order.containsKey(p2))
                    return order.get(p1) - order.get(p2);
                else if (!order.containsKey(p1) && order.containsKey(p2))
                    return 1;
                else if (order.containsKey(p1) && !order.containsKey(p2))
                    return -1;
                else 
                    return p1 - p2;
            }
        });
        int[] res = new int[m];
        for (int i = 0; i < m; i++)
            res[i] = list.get(i);
        return res;
    }
}

//O(1001)
//https://leetcode.com/problems/relative-sort-array/discuss/334570/JavaPython-3-O(1001)-code-similar-to-791-Custom-Sort-String.
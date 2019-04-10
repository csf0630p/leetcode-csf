class Solution {
    public int totalFruit(int[] tree) {
        int max = 0, i = 0, j = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
         while (j < tree.length) {
            while (map.size() < 3 && j < tree.length){
                // System.out.println(j+" "+ map.size());
                if (map.containsKey(tree[j])){
                    map.put(tree[j], map.get(tree[j])+1);
                }
                else if(map.size() == 2)
                    break;
                else if(map.size() < 2)
                    map.put(tree[j], 1);
                j++;
            }
            max = Math.max(max, j - i);
            //  while (i < j && map.size() > 1) {
            //     map.put(tree[i], map.get(tree[i]) - 1);
            //     if (map.get(tree[i]) == 0) {
            //         map.remove(tree[i]);
            //     }
            //     i++;
            // }

        }
        return max;
    }
}

class Solution {
    public int totalFruit(int[] tree) {
        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < tree.length; i++){
            int j = i;
            while (map.size() < 3 && j < tree.length){
                // System.out.println(j+" "+ map.size());
                if (map.containsKey(tree[j])){
                    map.put(tree[j], map.get(tree[j])+1);
                }
                else if(map.size() == 2)
                    break;
                else if(map.size() < 2)
                    map.put(tree[j], 1);
                j++;
            }
            max = Math.max(max, j - i);
            map.clear();
            // i = Math.max(j - i, j - 1);
        }
        return max;
    }
}
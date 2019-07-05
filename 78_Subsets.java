// recursive
// Each recursion level focuses on all the following elements. 
// We scan through all the following elements and decide whether to choose or not choose that element. 
// (Every level split into N branches.)
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}

// Each recursion level focuses on one element, we need to decide choose or not choose this element. (Every level split into 2 branches.)
// can be implemented by bit manipulation

// iterative
public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());       
        Arrays.sort(S); // not necessary
        for(int i : S) {
            List<List<Integer>> tmp = new ArrayList<>();
            for(List<Integer> sub : res) {
                List<Integer> a = new ArrayList<>(sub);
                a.add(i);
                tmp.add(a);
            }
            res.addAll(tmp);
        }
        return res;
    }
}

// bit manipulation
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        if (nums == null || nums.length == 0) return list;
        for (int i = 0; i < 1 << nums.length; i++) {
			
            List<Integer> subset = new LinkedList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((i & (1 << j)) >> j) == 1) subset.add(nums[j]);
            }   
            int temp = i;			
			/* both are right */
            // int temp = i;
            // for (int j = 0; j < nums.length; j++) {
            //     if ((temp & 1) == 1) subset.add(nums[j]);
            //     temp >>= 1;
            // }                          			
            list.add(subset);
        }
        return list;
    }
}
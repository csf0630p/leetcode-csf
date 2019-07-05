// recursive
// Each recursion level focuses on all the following elements. 
// We scan through all the following elements and decide whether to choose or not choose that element. 
// (Every level split into N branches.)
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start){
		list.add(new ArrayList<>(tempList));
		for(int i = start; i < nums.length; i++){
			if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
			tempList.add(nums[i]);
			backtrack(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	} 

// iterative
// https://www.cnblogs.com/grandyang/p/4310964.html
// 重复时, 只能在上一个循环生成的后面加
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();		
        Arrays.sort(nums);
        result.add(new ArrayList<Integer>());
        int begin = 0;
        for(int i = 0; i < nums.length; i++){
            if(i == 0 || nums[i] != nums[i - 1]) begin = 0;
            int size = result.size();
            for(int j = begin; j < size; j++){
                List<Integer> cur = new ArrayList<Integer>(result.get(j));
                cur.add(nums[i]);
                result.add(cur);
            }
            begin = size;
        }
        return result;
    }
}
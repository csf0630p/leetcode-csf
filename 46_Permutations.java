//method 1 : recursive, dictionary order
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        permuteHelper(nums, new int[nums.length], 0, new boolean[nums.length], ans);
        return ans;
    }

    private void permuteHelper(int[] nums, int[] cur, int index, boolean[] used, List<List<Integer>> ans) {
        if (index == cur.length) {
            List<Integer> permutation = new ArrayList();
            for (int n : cur) {
                permutation.add(n);
            }
            ans.add(permutation);
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            cur[index] = nums[i];
            permuteHelper(nums, cur, index + 1, used, ans);
            used[i] = false;
        }
    }
}

//method 1.1
	public List<List<Integer>> permute(int[] nums) {
	   List<List<Integer>> list = new ArrayList<>();
	   // Arrays.sort(nums); // not necessary
	   backtrack(list, new ArrayList<>(), nums);
	   return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
	   if(tempList.size() == nums.length){
		  list.add(new ArrayList<>(tempList));
	   } else{
		  for(int i = 0; i < nums.length; i++){ 
			 if(tempList.contains(nums[i])) continue; // element already exists, skip
			 tempList.add(nums[i]);
			 backtrack(list, tempList, nums);
			 tempList.remove(tempList.size() - 1);
		  }
	   }
	} 

//wrong
class Solution {
	List<List<Integer>> result = new LinkedList<List<Integer>>();
	
    public List<List<Integer>> permute(int[] nums) {
		Set<Integer> num = new HashSet<>();
        for (int i : nums) 
            num.add(i);
		List<Integer> res = new LinkedList<>();
		dfs(num, res);
		return result;
    }
	
	void dfs(Set<Integer> num, List<Integer> res) {
		if (num.size() == 0) {
			result.add(res);
			return;
		}	
		for (int i : num) {
			res.add(i);
			num.remove(i);
			dfs(num, res);
			res.remove(res.size() - 1);
			num.add(i);
		}
	}	
}

//method 2 : non-Dictionary Iteration, Insert separately from last results
//time : O(n!*n)
class Solution {
    public List<List<Integer>> permute(int[] nums) {
	    LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
	    res.add(new ArrayList<Integer>());
	    for (int n : nums) {
	        int size = res.size();
	        LinkedList<List<Integer>> newResult = new LinkedList<List<Integer>>();
	        for (; size > 0; size--) {
	            List<Integer> r = res.pollFirst();	            
//	            for (int i = 0; i <= r.size(); i++) {
	            for (int i = r.size(); i >= 0; i--) {	            	
	                List<Integer> t = new ArrayList<Integer>(r);
	                t.add(i, n);	//O(n), whatever arraylist/linkedlist
	                newResult.add(t);
	            }
	        }
	        res = newResult;
	    }
	    return res;
    }
}
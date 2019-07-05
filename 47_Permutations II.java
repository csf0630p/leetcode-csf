//method 1 : recursive, dictionary order
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        Arrays.sort(nums);
        permuteHelper(nums, new int[nums.length], 0, new boolean[nums.length], ans);
        return ans;
    }

    private void permuteHelper(int[] nums, int[] cur, int index, boolean[] used, List<List<Integer>> ans) {
        if (index >= nums.length) {
            List<Integer> permutation = new ArrayList();
            for (int n : cur) {
                permutation.add(n);
            }
            ans.add(permutation);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && used[i - 1])) {
                continue;
            }
            used[i] = true;
            cur[index] = nums[i];
            permuteHelper(nums, cur, index + 1, used, ans);
            used[i] = false;
        }
    }
}

//1.1
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
             // if(used[i] || i > 0 && nums[i] == nums[i-1] && used[i - 1]) { 
				// System.out.print(i);
				// for (Boolean id : used) System.out.print(id ? "T" : "F"); System.out.println();			 
				// continue; both are right, slowly
			 // }
                used[i] = true; 
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false; 
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}

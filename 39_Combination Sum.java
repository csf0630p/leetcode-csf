class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else{ 
            for(int i = start; i < nums.length; i++){
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}

//https://www.cnblogs.com/grandyang/p/4419259.html
//recursive without extra methods
// Each recursion level focuses on one element, we need to decide choose or not choose this element. (Every level split into 2 branches.)
class Solution {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vector<vector<int>> res;
        sort(candidates.begin(), candidates.end());
        for (int i = 0; i < candidates.size(); ++i) {
            if (candidates[i] > target) break;
            if (candidates[i] == target) {res.push_back({candidates[i]}); break;}
            vector<int> vec = vector<int>(candidates.begin() + i, candidates.end());
            vector<vector<int>> tmp = combinationSum(vec, target - candidates[i]);
            for (auto a : tmp) {
                a.insert(a.begin(), candidates[i]);
                res.push_back(a);
            }
        }
        return res;
    }
};

//iterative with DP
class Solution {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vector<vector<vector<int>>> dp;
        sort(candidates.begin(), candidates.end());
        for (int i = 1; i <= target; ++i) {
            vector<vector<int>> cur;
            for (int j = 0; j < candidates.size(); ++j) {
                if (candidates[j] > i) break;
                if (candidates[j] == i) {cur.push_back({candidates[j]}); break;}
                for (auto a : dp[i - candidates[j] - 1]) {
                    if (candidates[j] > a[0]) continue;
                    a.insert(a.begin(), candidates[j]);
                    cur.push_back(a);
                }
            }
            dp.push_back(cur);
        }
        return dp[target - 1];
    }
};

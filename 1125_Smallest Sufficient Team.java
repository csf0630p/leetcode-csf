// DP
// https://leetcode.com/problems/smallest-sufficient-team/discuss/334572/Python-DP-Solution
class Solution {
    public int[] smallestSufficientTeam(String[] skills, List<List<String>> people) {
        int sLen = skills.length, pLen = people.size();
        
        Map<String, Integer> skmap = new HashMap<>();
        for(int i = 0; i < sLen; i++)
            skmap.put(skills[i], i);
        
        Set<Integer>[] skillArr = new Set[1 << sLen];
        skillArr[0] = new HashSet<>();
        
        for(int ppl = 0; ppl < pLen; ppl++){
            int pplSkill = 0;
            for(String sks : people.get(ppl)){
                pplSkill |= 1 << (skmap.get(sks));
            }
            
            for(int k = 0; k < skillArr.length; k++){
                if(skillArr[k] == null) continue;
                Set<Integer> currSkills = skillArr[k];
                int combined = k | pplSkill;
                if(combined == k) continue;
                if(skillArr[combined] == null || skillArr[combined].size() > currSkills.size() + 1){
                    Set<Integer> cSkills = new HashSet<>(currSkills);
                    cSkills.add(ppl);
                    skillArr[combined] = cSkills;
                }
            }
        }
        
        Set<Integer> resSet = skillArr[(1 << sLen) - 1];
        int[] res = new int[resSet.size()];
        int pos = 0;
        for(Integer n : resSet)
            res[pos++] = n;
        
        return res;
    }
}

// dfs with bitwise & pruning
// https://leetcode.com/problems/smallest-sufficient-team/discuss/334669/Java-DFS-search-with-Pruning-using-Bitwise-tricks-4ms-beating-98
class Solution {
    List<Integer> res = new ArrayList<>();
    int skillsNumber = 0;
    int[] pe;
    
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> idx = new HashMap<>(); 
        this.skillsNumber = req_skills.length;
        for (int i = 0; i < skillsNumber; i++) {
            idx.put(req_skills[i], i);
        }//skills are represented by 0, 1, 2....
        this.pe = new int[people.size()];
        for (int i = 0; i < pe.length; i++) {
            for (String p : people.get(i)) {
                int skill = idx.get(p);
                pe[i] += 1 << skill;
            }
        } // each person is transferred to a number, of which the bits of 1 means the guy has the skill
        search(0, new ArrayList<Integer>());  
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }
    
    public void search(int cover, List<Integer> curSol) { 
        if (cover == (1 << skillsNumber) - 1) {  // when all bits are 1, all skills are coverred
            if (res.size() == 0 || curSol.size() < res.size()) {
                res = new ArrayList<>(curSol);
            }
            return;
        }
        if (res.size() != 0 && curSol.size() >= res.size()) return;    //pruning
        int zeroBit = 0;
        while (((cover >> zeroBit) & 1) == 1) zeroBit++;   
        for (int i = 0; i < pe.length; i++) {
            int per = pe[i];
            if (((per >> zeroBit) & 1) == 1) {
                curSol.add(i); // when a person can cover a zero bit in the current number, we can add him
                search(cover|per, curSol);
                curSol.remove(curSol.size() - 1);  //search in a backtracking way
            }
        } 
    }
}

// TLE
class Solution {
    List<List<String>> people;
    int min = Integer.MAX_VALUE >> 2;
    int n;
    boolean[] used;
    int[] res;
    
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
		// Map<String> map = new HashSet<>();
        // for (int i = 0; i < req_skills.length; i++) {
        //     map.put(req_skills[i], i);
        // }
        Set<String> set = new HashSet<>();
        for (String s : req_skills) {
            set.add(s);
        }
        this.people = people;
        this.n = people.size();
        this.used = new boolean[n];
        dfs(set, 0, used);
        return res;
    }
    
    void dfs(Set<String> remaining, int person, boolean[] used) {
        if (remaining.size() == 0) {
            int sum = 0;
            for (Boolean b : used)
                if (b) sum++;
            if (sum < min) {
                min = sum;
                res = new int[sum];
                int pt = 0;
                for (int i = 0; i < n; i++)
                    if (used[i]) res[pt++] = i;
                return;
            }
        }
        for (int i = person; i < n; i++) {
            used[i] = true;
            List<String> skills = people.get(i);
            Set<String> original = new HashSet<>(remaining);
            for (String s : skills)
                if (remaining.contains(s))
                    remaining.remove(s);
            
            dfs(remaining, i + 1, used);
            
            used[i] = false;
            remaining = original;           
        }
        return;
    }
}
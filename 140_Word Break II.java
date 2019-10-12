// dfs+memo O(2^n)/O(2^n)
class Solution {
    HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();
    // DFS function returns an array including all substrings derived from s.
    List<String> wordBreak(String s, List<String> wordDict) {
        if (map.containsKey(s)) return map.get(s);
        LinkedList<String> res = new LinkedList<String>();     
        if (s.length() == 0) {
            res.add("");
            return res;
        }               
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = wordBreak(s.substring(word.length()), wordDict);
                for (String sub : sublist) 
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
            }
        }       
        map.put(s, res);
        return res;
    }
}

// bfs, wrong
class Solution {    
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        Queue<String> qSol = new LinkedList<>();
        Queue<boolean[]> qVisited = new LinkedList<>();
        List<String> ans = new LinkedList<>();
        // int[] visited = new int[s.length()];
        queue.add(0);
        qSol.add("");
        qVisited.add(new boolean[s.length()]);
        while (!queue.isEmpty()) {
            int start = queue.poll();
            String stSol = qSol.poll();
            boolean[] visited = qVisited.poll();
            if (!visited[start]) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        stSol += " " + s.substring(start, end);
                        System.out.println("stSol:" + stSol);
                        qSol.add(stSol);
                        visited[start] = true;
                        newV = new boolean[s.length()];
                        for (int i = 0; i < s.length(); i++) newV = visited[i];
                        qVisited.add(newV);
                        if (end == s.length()) {
                            // StringBuilder sb = new StringBuilder();
                            // for (String str : stSol) sb.append(" " + str);
                            // System.out.println("sb:" + sb);
                            // ans.add(sb.toString().substring(1, sb.length()));
                            ans.add(stSol);
                            System.out.println("ans:" + ans);
                        }
                        
                    }
                }
                
            }
        }
        return ans;
    }    
}
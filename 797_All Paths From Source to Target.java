// 1.0 dfs
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {     
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (graph == null || graph.length == 0) return res;
        Stack<Integer> path = new Stack<Integer>();
        path.push(0);
        dfs(graph, 0, res, path);
        return res;
    }
    
    void dfs(int[][] graph, int cur, List<List<Integer>> res, Stack<Integer> path) {
        int n = graph.length;
        if (cur == n - 1) {
            res.add(new ArrayList<Integer>(path));
        } else {
            for (int i = 0; i < graph[cur].length; i++) {
                path.push(graph[cur][i]);
                dfs(graph, graph[cur][i], res, path);
                path.pop();
            }
        }
    }
}

//2.0 bfs
//http://www.ciaoshen.com/algorithm/leetcode/2019/03/20/leetcode-all-paths-from-source-to-target.html
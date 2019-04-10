/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

    class Pair {
        int val;
        int level;
        int order;
        Pair(int val, int level, int order) {
            this.val = val;
            this.level = level;
            this.order = order;
        }
        // public String toString() {
        //     return String.format("%d %d %d", val, level, order);
        // }
    }
        
class Solution {
	//lambda 1
    // TreeSet<Pair> set = new TreeSet<>((Pair p1, Pair p2) ->
    //                                  ((p1.order == p2.order) ? 
    //                                     ((p1.level == p2.level) ?
    //                                      1 : (p1.level - p2.level))
    //                                   : (p1.order - p2.order)) 
    //                                  );
	
	//lambda 2									
    TreeSet<Pair> set = new TreeSet<>((Pair p1, Pair p2) ->
                                      {if (p1.order != p2.order)
                                          return p1.order - p2.order;
                                       else if (p1.level != p2.level)
                                          return p1.level - p2.level;
                                       else 
                                          return 1;
                                      });   									 
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();        
        if (root == null)
            return res;
        dfs(root, 0, 0);

        List<Integer> line = null;             
        int temp = Integer.MAX_VALUE;          
        for (Pair id : set) {
            if (id.order == temp) {
                line.add(id.val);
            } else {
                line = new ArrayList<>();                
                line.add(id.val);
                res.add(line);
            }
            temp = id.order;
        }
        return res;
    }
    
    private void dfs(TreeNode node, int level, int order) {
        set.add(new Pair(node.val, level, order));        
        // System.out.println(set);        
        if (node.left != null)
            dfs(node.left, level + 1, order - 1);
        if (node.right != null)
            dfs(node.right, level + 1, order + 1);        
    }
}

// method 2 BFS: 1ms
class Solution {
    private int min = 0, max = 0;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null)    
            return list;
        computeRange(root, 0);
        for(int i = min; i <= max; i++) 
            list.add(new ArrayList<>());
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> idx = new LinkedList<>();
        idx.add(-min);
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            int i = idx.poll();
            list.get(i).add(node.val);
            if(node.left != null){
                q.add(node.left);
                idx.add(i - 1);
            }
            if(node.right != null){
                q.add(node.right);
                idx.add(i + 1);
            }
        }
        return list;
    }
    private void computeRange(TreeNode root, int idx){
        if(root == null)    
            return;
        min = Math.min(min, idx);
        max = Math.max(max, idx);
        computeRange(root.left, idx - 1);
        computeRange(root.right, idx + 1);
    }
}
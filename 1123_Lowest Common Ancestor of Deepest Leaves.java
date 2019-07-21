/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 //O(n)/O(h)
    class Pair{
        TreeNode node;
        int d;
        Pair(TreeNode node, int d) {
            this.node = node;
            this.d = d;
        }
    }
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Pair p = getLca(root, 0);
        return p.node;
    }
    private Pair getLca(TreeNode root, int d) {
        if(root == null) return new Pair(null, d); 
        Pair l = getLca(root.left, d + 1);
        Pair r = getLca(root.right, d + 1);
        if(l.d == r.d) {
            return new Pair(root, l.d);
        }else {
            return  l.d > r.d ? l : r;
        }
    }
	
//O(n^2)/O(h)
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root==null || height(root.right)==height(root.left))return root;
        return lcaDeepestLeaves(height(root.left)>height(root.right)?root.left:root.right);
    }
    
    public int height(TreeNode root){
        if(root==null)return 0;
        return 1 + Math.max(height(root.left),height(root.right));
    }

//O(n)/O(n)
    HashMap<TreeNode, Integer> heights = new HashMap<TreeNode,Integer>();
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root==null || height(root.right)==height(root.left))return root;
        return lcaDeepestLeaves(height(root.left)>height(root.right)?root.left:root.right);
    }
    
    public int height(TreeNode root){
        if(root==null)return 0;
        if(heights.containsKey(root))return heights.get(root);
        heights.put(root,1 + Math.max(height(root.left),height(root.right)));
        return heights.get(root);
    }
    
 
 // O(n)/O(n)
class Solution {
    class Info {
        int depth = 0;
        TreeNode parent = null;
        int mark = 0;
        Info (int d, TreeNode p, int m) {
            this.depth = d; this.parent = p; this.mark = m;
        }
    }
    
    int maxDepth = 0;
    Map<TreeNode, Info> table = new HashMap<>();
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) return null;     
        getDepth(root, null, 0);
        //find deepest nodes
        List<TreeNode> deepNodes = new ArrayList<>();
        for (Map.Entry<TreeNode, Info> e : table.entrySet()) {
            TreeNode k = e.getKey();
            Info v = e.getValue();
            if (v.depth == maxDepth) {
                deepNodes.add(k);   
            }            
        }
        // get result from paths when looking for parents
        TreeNode res = null;
        for (int i = 0; i < deepNodes.size(); i++) {
            TreeNode pt = deepNodes.get(i);
            // System.out.print(pt.val + "!");
            boolean flag = true;
            while (pt != null) {                
                // if (table.get(pt).parent == pt) flag = false;
                table.get(pt).mark++;
                // System.out.println(table.get(pt).mark + ",");
                if (i == deepNodes.size() - 1 && table.get(pt).mark == deepNodes.size()) {
                    res = pt;
                    return res;
                }
                pt = table.get(pt).parent;                
                // System.out.print(pt.val + "!");
            }             
        }
        return res;
    }
    
    void getDepth(TreeNode node, TreeNode parent, int d) {
        table.put(node, new Info(d, parent, 0));
        if (d > maxDepth) maxDepth = d;
        if (node.left != null) getDepth(node.left, node, d + 1);
        if (node.right != null) getDepth(node.right, node, d + 1);
    }
}
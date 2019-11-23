/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int count = 0;
    
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return count;
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int l = dfs(root.left), r = dfs(root.right);
        count += Math.abs(l) + Math.abs(r);
        return l + r + root.val - 1;        
    }
}
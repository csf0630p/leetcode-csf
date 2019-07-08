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
    int maxRes = Integer.MIN_VALUE >> 2;
    
    public int maxPathSum(TreeNode root) {
        if (root == null) return maxRes;
        dfs(root);
        return maxRes;
    }
    
    int dfs(TreeNode root) {
        int max = root.val;
        int lMax = Integer.MIN_VALUE >> 2, rMax = Integer.MIN_VALUE >> 2;
        if (root.left != null) lMax = dfs(root.left);
        if (root.right != null) rMax = dfs(root.right);
        int[] choices = new int[]{max, max + lMax, max + rMax, max + lMax + rMax};
        for (int i = 0; i < 4; i++) maxRes = Math.max(maxRes, choices[i]);
        for (int i = 0; i < 3; i++) max = Math.max(max, choices[i]);
        return max;
    }
}
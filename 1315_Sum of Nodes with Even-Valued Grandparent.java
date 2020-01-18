// 1.0 dfs O(n)/O(h)
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
    public int sumEvenGrandparent(TreeNode root) {
        return dfs(root, 0, 0); // 0 means no parent or grandparent
    }
    
    int dfs(TreeNode node, int grandparent, int parent) {
        int sum = 0;
        if (grandparent != 0 && grandparent % 2 == 0) sum += node.val;
        if (node.left != null) sum += dfs(node.left, parent, node.val);
        if (node.right != null) sum += dfs(node.right, parent, node.val);
        return sum;
    }
}

// 1.1
// https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/discuss/477048/JavaC%2B%2BPython-1-Line-Recursive-Solution
    public int sumEvenGrandparent(TreeNode root) {
        return helper(root, 1, 1);
    }

    public int helper(TreeNode node, int p, int gp) {
        if (node == null) return 0;
        return helper(node.left, node.val, p) + helper(node.right, node.val, p) + (gp % 2 == 0 ? node.val : 0);
    }
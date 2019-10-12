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
    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> res = new LinkedList<>();
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {        
        if (root == null) return res;
        path.add(root.val);
        dfs(root, sum);
        return res;
    }
    
    void dfs(TreeNode node, int sum) {
        // if (node == null) return;
        if (node.left == null && node.right == null && node.val == sum) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (node.left != null) {
            path.addLast(node.left.val);
            dfs(node.left, sum - node.val);
            path.removeLast();
        }
        if (node.right != null) {
            path.addLast(node.right.val);
            dfs(node.right, sum - node.val);
            path.removeLast();
        }                   
    }
}
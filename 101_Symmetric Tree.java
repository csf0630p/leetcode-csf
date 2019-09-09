/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 1.0 BFS
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return true;
        Queue<TreeNode> leftChild = new LinkedList<>();
        leftChild.add(root.left);
        Queue<TreeNode> rightChild = new LinkedList<>();
        rightChild.add(root.right);
        while (!leftChild.isEmpty()) {
            int len = leftChild.size();
            for (int i = 0; i < len; i++) {
                TreeNode nodeF = leftChild.poll();
                TreeNode nodeR = rightChild.poll();
                if (nodeF == null && nodeR == null) continue;
                if (nodeF == null && nodeR != null ||
                    nodeF != null && nodeR == null ||
                    nodeF.val != nodeR.val) return false;
                // nodeF.val == nodeR.val
                leftChild.add(nodeF.left);
                leftChild.add(nodeF.right);
                rightChild.add(nodeR.right);
                rightChild.add(nodeR.left);              
            }            
        }
        return true;
    }
}

// 2.0 DFS
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }
    
    boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
}
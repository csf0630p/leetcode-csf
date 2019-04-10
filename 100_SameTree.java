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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null)||(q == null)) {
            if (p != q) 
                return false;
            else 
                return true;
        }
        if (p.val != q.val) 
            return false;
        if ((isSameTree(p.left, q.left)) && 
            (isSameTree(p.right, q.right)))
            return true;
        else return false;
    }
}
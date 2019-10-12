// 1.0
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) return true;
        return ((root.left != null) ? hasPathSum(root.left, sum - root.val) : false) || 
            ((root.right != null) ? hasPathSum(root.right, sum - root.val) : false) ;
    }
}

// 1.1
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null) return sum == root.val;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}

// wrong, [null] & 0 is false;
	public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) 
            return (sum == 0) ? true : false;
        else
            return hasPathSum(root.left, sum - root.val) || 
                hasPathSum(root.right, sum - root.val);
    }
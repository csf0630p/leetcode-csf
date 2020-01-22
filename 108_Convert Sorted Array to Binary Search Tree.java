/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // 1.0 pre-order generate, O(n)/O(logn)
 class Solution {    
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) return null;
        return inorderHelper(nums, 0, nums.length - 1);        
    }

    public TreeNode inorderHelper(int[] nums, int start, int end){
        if(start > end) return null;        

        int mid = start + (end - start) / 2;
        TreeNode treenode = new TreeNode(nums[mid]);        
        treenode.left = inorderHelper(nums, start, mid - 1);
        treenode.right = inorderHelper(nums, mid + 1, end);

        return treenode;
    }    
}
 
 // 2.0 in-order generate, O(n)/O(logn)
class Solution {
    private int pt = 0;
    
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) return null;
        return inorderHelper(nums, 0, nums.length - 1);        
    }

    public TreeNode inorderHelper(int[] nums, int start, int end){
        if(start > end) return null;        

        int mid = start + (end - start) / 2;
        TreeNode left = inorderHelper(nums, start, mid - 1);

        TreeNode treenode = new TreeNode(nums[pt]);
        treenode.left = left;
        pt++;

        TreeNode right = inorderHelper(nums, mid + 1, end);
        treenode.right = right;

        return treenode;
    }    
}
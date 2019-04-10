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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(0, preorder.length - 1, 0, inorder.length - 1, preorder, inorder);
    }
    
    TreeNode dfs(int preStart, int preEnd, int inStart, int inEnd, int[] preorder, int[] inorder) {
        // System.out.printf("%d %d %d %d\n", preStart, preEnd, inStart, inEnd);
        if ((preStart == preEnd) && (inStart == inEnd))
            return new TreeNode(preorder[preStart]);    //this optimize from 10 to 9ms
        if ((preStart > preEnd) || (inStart > inEnd))
            return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int i = inStart;
        while(preorder[preStart] != inorder[i])
            i++;
        int j = i - inStart;
        root.left = dfs(preStart + 1, preStart + j, inStart, i - 1, preorder, inorder);
        root.right = dfs(preStart + j + 1, preorder.length - 1, i + 1, inEnd, preorder, inorder);
        return root;
    }
}

//method 2: use Arrays.copyOfRange, space complexity O(n), method 1 is O(log n)/O(h)
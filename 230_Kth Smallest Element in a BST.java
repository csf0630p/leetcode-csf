class Solution {
    int count = 0, k = 0, res = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }
    
    void dfs(TreeNode node) {
        if (node == null || this.count >= k) return;
        dfs(node.left);
        count++;
        if (count == k) this.res = node.val;
        dfs(node.right);
    }
}
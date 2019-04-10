/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int maxDepth(TreeNode* root) {
        if (root == NULL) return 0;
        int iDep_l = maxDepth(root->left);
        int iDep_r = maxDepth(root->right);
        return ((iDep_l > iDep_r) ? iDep_l + 1 : iDep_r + 1);
    }
};
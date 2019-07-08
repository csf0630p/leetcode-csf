/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // method 1: bfs
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> cur = new LinkedList<>();
        Queue<TreeNode> fwd = new LinkedList<>();
        LinkedList<Integer> curVal = new LinkedList<>();
        LinkedList<Integer> fwdVal = new LinkedList<>();
        cur.add(root);
        curVal.add(root.val);
        while (!cur.isEmpty()) {
            TreeNode node = cur.poll();
            if (node.left != null) {
                fwd.add(node.left);
                fwdVal.add(node.left.val);
            }
            if (node.right != null) {
                fwd.add(node.right);
                fwdVal.add(node.right.val);
            }                
            if (cur.isEmpty()) {
                res.addFirst(curVal);
                cur = fwd;
                curVal = fwdVal;
                fwd = new LinkedList<>();
                fwdVal = new LinkedList<>();
            }
        }
        return res;
    }
}

//1.1
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> level = new LinkedList<>();
        LinkedList<Integer> levelVal = new LinkedList<>();
        level.add(root);
        while (!level.isEmpty()) {
			int size = level.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = level.poll();
				levelVal.add(node.val);
				if (node.left != null) {
					level.add(node.left);
					levelVal.add(node.left.val);
				}
				if (node.right != null) {
					level.add(node.right);
					levelVal.add(node.right.val);
				}  				
			}             
			res.addFirst(levelVal);        
        }
        return res;
    }
}

//1.2 use iterator
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        LinkedList<TreeNode> cur = new LinkedList<>();
        LinkedList<TreeNode> fwd = new LinkedList<>();
        LinkedList<Integer> curVal = new LinkedList<>();
        LinkedList<Integer> fwdVal = new LinkedList<>();
        cur.add(root);
        curVal.add(root.val);
        Iterator it = cur.iterator();
        while (it.hasNext()) {
            TreeNode node = (TreeNode)it.next();
            if (node.left != null) {
                fwd.add(node.left);
                fwdVal.add(node.left.val);
            }
            if (node.right != null) {
                fwd.add(node.right);
                fwdVal.add(node.right.val);
            }                
            if (!it.hasNext()) {
                res.addFirst(curVal);
                cur = fwd;
                curVal = fwdVal;
                fwd = new LinkedList<>();
                fwdVal = new LinkedList<>();
                it = cur.iterator();
            }
        }
        return res;
    }
}

//method 2: dfs
class Solution {
public:
    vector<vector<int>> levelOrderBottom(TreeNode* root) {
        vector<vector<int>> res;
        levelorder(root, 0, res);
        return vector<vector<int>> (res.rbegin(), res.rend());
    }
    void levelorder(TreeNode* node, int level, vector<vector<int>>& res) {
        if (!node) return;
        if (res.size() == level) res.push_back({});
        res[level].push_back(node->val);
        if (node->left) levelorder(node->left, level + 1, res);
        if (node->right) levelorder(node->right, level + 1, res);
    }
};
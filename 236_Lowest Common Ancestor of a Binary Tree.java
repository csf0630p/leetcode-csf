/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // 1.0 dfs
class Solution {
    TreeNode res = null;
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {        
        dfs(root, p, q);
        return res;
    }
    
    //case 0: not found; 1: found p; 2: found q; 3: found both;
    private int dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return 0;
        int flag = 0;
        if (node.val == p.val) flag |= 1;
        if (node.val == q.val) flag |= 2;
        flag |= dfs(node.left, p, q);
        flag |= dfs(node.right, p, q);
        if (res == null && flag == 3) res = node;
        // System.out.println(node.val + " " + flag);
        return flag;
    }
}

// 1.1 dfs
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)  return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null)   return root;
        return left != null ? left : right;
    }
	
// 2.0 dfs twice, compare their paths

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/65236/JavaPython-iterative-solution
// 3.0 dfs, iterative
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        parent.put(root, null);
        stack.push(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }
}

//https://www.hrwhisper.me/algorithm-lowest-common-ancestor-of-a-binary-tree/
/*二叉树最近公共祖先详解（LCA问题详解）
BST
BT
带有父节点信息bt
多次查询的BT: Tarjan离线算法
LCA转RMQ
*/
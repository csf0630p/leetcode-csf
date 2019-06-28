// method 1: dfs
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if ((root.left == null) && (root.right == null)) return 1;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}

// method 2: bfs, more efficient 
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int res = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if ((node.left == null) && (node.right == null)) return res;
                if (node.left != null) q.offer(node.left);          
                if (node.right != null) q.offer(node.right);                     
            }
            res++;
        }
        return res;
    }
}





class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // root.val = 1;
        int res = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if ((node.left == null) && (node.right == null)) {
                    return res;
                }
                if (node.left != null) {
                    // node.left.val = node.val + 1;
                    q.offer(node.left);
                    // if (node.left.val > res) res = node.left.val;                
                }
                if (node.right != null) {
                    // node.right.val = node.val + 1;
                    q.offer(node.right);
                    // if (node.right.val > res) res = node.right.val;                
                }
            }
            res++;
        }
        return res;
    }
}

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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        // int l = 0, r = 0;
        if ((root.left == null) && (root.right == null)) return 1;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        // if (root.left != null) l = minDepth(root.left);
        // if (root.right != null) r = minDepth(root.right);
        // if ((l == 0) && (r == 0)) 
        //     return 1;
        // else if ((l == 0) || (r == 0))
        //     return 2;
        // else
        //     return Math.min(l, r) + 1;
    }
}

// 0 0 1
// 0 1 2
// 1 1 2
// 1 2 2

// 1
// 2   x
// 3   x   4   x
// 5
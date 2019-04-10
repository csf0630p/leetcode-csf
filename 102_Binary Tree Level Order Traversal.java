/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //method 1: BFS, 2 queue   O(n)/O(n)
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        Queue<TreeNode> next = new LinkedList<>();
        List<Integer> resLine = new ArrayList<>();
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            resLine.add(temp.val);
            if (temp.left != null)
                next.offer(temp.left);
            if (temp.right != null)
                next.offer(temp.right);
            if (q.isEmpty()) {
                res.add(resLine);
                resLine = new ArrayList<>();
                q = next;
                next = new LinkedList<>();
            }                
        }
        return res;
    }
}

//method 2: BFS, 1 queue	O(n)/O(n)
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<Integer> resLine = new ArrayList<>();
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                TreeNode temp = q.poll();
                resLine.add(temp.val);
                if (temp.left != null)
                    q.offer(temp.left);
                if (temp.right != null)
                    q.offer(temp.right);                
            }
            res.add(resLine);
            resLine = new ArrayList<>();            
        }
        return res;
    }
}

//method 3: DFS   O(n)/O(n)
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        levelHelper(res, root, 0);
        return res;
    }
    
    public void levelHelper(List<List<Integer>> res, TreeNode node, int height) {
        if (node == null) 
            return;
        if (height >= res.size()) {
            res.add(new LinkedList<Integer>());
        }
        res.get(height).add(node.val);
        levelHelper(res, node.left, height+1);
        levelHelper(res, node.right, height+1);
    }
}
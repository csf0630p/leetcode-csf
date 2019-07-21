/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {
    Stack<TreeNode> st;

    public BSTIterator(TreeNode root) {
        st = new Stack<>();
        helper(root);
        // if (!st.isEmpty()) System.out.println(st.peek().val + " " + st.size());
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode res = st.pop();
        helper(res.right);
        // if (!st.isEmpty()) System.out.println(st.peek().val + " " + st.size());
        return res.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !st.isEmpty();
    }
    
    public void helper(TreeNode it) {
        while (it != null) {
            st.push(it);
            it = it.left;
        }
        return;
    }
}


/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
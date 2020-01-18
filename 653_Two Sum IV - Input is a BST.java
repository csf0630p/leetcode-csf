// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/discuss/106059/JavaC%2B%2B-Three-simple-methods-choose-one-you-like
// 1.0 HashSet O(n)/O(n)

// 2.0 in-order BST traversal to array O(n)/O(n)

// 3.0 binary search O(nh)/O(h)

// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/discuss/106110/Java-Code-O(n)-time-O(lg(n))-space-using-DFS-%2B-Stack
// 4.0 dfs+stack O(n)/O(h)
    public boolean findTarget(TreeNode root, int k) {
        Deque<TreeNode> stackL = new ArrayDeque<>();  // iterator 1 that gets next smallest value
        Deque<TreeNode> stackR = new ArrayDeque<>();  // iterator 2 that gets next largest value
        
        for(TreeNode cur = root; cur != null; cur = cur.left)  
            stackL.push(cur);
        for(TreeNode cur = root; cur != null; cur = cur.right)  
            stackR.push(cur);
            
        while(stackL.size() != 0 && stackR.size() != 0 && stackL.peek() != stackR.peek()){
            int tmpSum = stackL.peek().val + stackR.peek().val;
            if(tmpSum == k)  return true;
            else if(tmpSum < k)
                for(TreeNode cur = stackL.pop().right; cur != null; cur = cur.left) 
                    stackL.push(cur);
            else
                for(TreeNode cur = stackR.pop().left; cur != null; cur = cur.right) 
                    stackR.push(cur);
        }
        
        return false;
    }

//TLE, O(2^n?)/O(h)
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root, k);
    }
    
    boolean dfs(TreeNode p1, TreeNode p2, int k) {
        if (p1 != p2 && p1.val + p2.val == k) return true;
        boolean temp = false;
        if (p1 == p2) {
            if (p1.left != null) {
                temp |= dfs(p1.left, p2, k);
            }           
            if (p2.right != null) {
                temp |= dfs(p1, p2.right, k);
            }                
        } else if (p1.val + p2.val > k) {
            if (p1.left != null) {
                temp |= dfs(p1.left, p2, k);
            }
            if (p2.left != null) {
                temp |= dfs(p1, p2.left, k);
            }            
        } else { // sum > k
            if (p1.right != null) {
                temp |= dfs(p1.right, p2, k);
            }
            if (p2.right != null) {
                temp |= dfs(p1, p2.right, k);
            }             
        }
        return temp;
    }
}
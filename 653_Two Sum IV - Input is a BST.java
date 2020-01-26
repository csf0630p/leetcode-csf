// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/discuss/106059/JavaC%2B%2B-Three-simple-methods-choose-one-you-like
// 1.0 HashSet O(n)/O(n), also work for common BT
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }
    
    public boolean dfs(TreeNode root, HashSet<Integer> set, int k){
        if(root == null)return false;
        if(set.contains(k - root.val))return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }
}

// 2.0 in-order BST traversal to array O(n)/O(n)
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        for(int i = 0, j = nums.size()-1; i<j;){
            if(nums.get(i) + nums.get(j) == k)return true;
            if(nums.get(i) + nums.get(j) < k)i++;
            else j--;
        }
        return false;
    }
    
    public void inorder(TreeNode root, List<Integer> nums){
        if(root == null)return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

// 3.0 binary search O(nh)/O(h)
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root,  k);
    }
    
    public boolean dfs(TreeNode root,  TreeNode cur, int k){
        if(cur == null)return false;
        return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
    }
    
    public boolean search(TreeNode root, TreeNode cur, int value){
        if(root == null)return false;
        return (root.val == value) && (root != cur) 
            || (root.val < value) && search(root.right, cur, value) 
                || (root.val > value) && search(root.left, cur, value);
    }

// 3.1
class Solution {
    TreeNode root;
    //DFS each node, and try to search the target 'node' such that 'node'.val = k-node.val
    //make sure you don't pick the node itself, like if k = 2 and node.val = 1, don't return node itself!
    public boolean findTarget(TreeNode node, int k) {
        if(this.root==null) this.root = node;//set variable for the root of this tree
        if(node==null) return false;
        if(search(node, k-node.val)) return true;//make sure you don't find the node itself!
        return findTarget(node.left,k)||findTarget(node.right,k);//DFS traverse
    }
    public boolean search(TreeNode node, int k){
        TreeNode current = root;//search from the root node
        while(current!=null){
            if(k>current.val) current = current.right;
            else if(k<current.val) current = current.left;
            else return current!=node?true:false;//you can't find the node itself!
        }
        return false;
    }
}

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
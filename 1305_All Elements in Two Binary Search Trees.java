// 1.0 in-order traversal both trees and add to result list, sort it. O(nlogn)/O(n)

// 2.0 two pointers, like 2-way merge sort, add smaller element to result in each step, and in-order traversal.
// O(n)/O(n)
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
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Deque<TreeNode> stack1 = new LinkedList<>(), stack2 = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        TreeNode p1 = root1, p2 = root2;
        if (p1 != null) {
            while (p1.left != null) {
                stack1.push(p1);
                p1 = p1.left;
            }
        }
        if (p2 != null) {
            while (p2.left != null) {
                stack2.push(p2);
                p2 = p2.left;
            }
        }        
        while (p1 != null || p2 != null) {
            int temp1 = (p1 == null) ? (int)1e9 : p1.val, temp2 = (p2 == null) ? (int)1e9 : p2.val;
            // System.out.println(temp1 + " " + temp2);
            // System.out.println(res);
            if (temp1 > temp2) {
                res.add(p2.val);
                if (p2.right != null) {
                    p2 = p2.right;
                    while (p2.left != null) {
                        stack2.push(p2);
                        p2 = p2.left;
                    }                    
                } else {
                    p2 = stack2.isEmpty() ? null : stack2.pop();
                }
            } else {
                res.add(p1.val);
                if (p1.right != null) {
                    p1 = p1.right;
                    while (p1.left != null) {
                        stack1.push(p1);
                        p1 = p1.left;
                    }                    
                } else {
                    p1 = stack1.isEmpty() ? null : stack1.pop();
                }
            }
        }
        return res;
    }
}

// 2.01
class Solution {
    private TreeNode getNext(TreeNode p, Deque<TreeNode> s) {
        while (p.left != null) {
            s.push(p);
            p = p.left;
        }
        return p;
    }
    
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Deque<TreeNode> stack1 = new LinkedList<>(), stack2 = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        TreeNode p1 = root1, p2 = root2;
        if (p1 != null) p1 = getNext(p1, stack1);
        if (p2 != null) p2 = getNext(p2, stack2);      
        while (p1 != null || p2 != null) {
            int temp1 = (p1 == null) ? (int)1e9 : p1.val, temp2 = (p2 == null) ? (int)1e9 : p2.val;
            if (temp1 > temp2) {
                res.add(p2.val);
                if (p2.right != null) {
                    p2 = p2.right;
                    p2 = getNext(p2, stack2);                 
                } else {
                    p2 = stack2.isEmpty() ? null : stack2.pop();
                }
            } else {
                res.add(p1.val);
                if (p1.right != null) {
                    p1 = p1.right;
                    p1 = getNext(p1, stack1);               
                } else {
                    p1 = stack1.isEmpty() ? null : stack1.pop();
                }
            }
        }
        return res;
    }
}

//2.02
class Solution {
    private TreeNode getNext(TreeNode p, Deque<TreeNode> s) {
        while (p.left != null) {
            s.push(p);
            p = p.left;
        }
        return p;
    }
    
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Deque<TreeNode> stack1 = new LinkedList<>(), stack2 = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        TreeNode p1 = root1, p2 = root2;
        if (p1 != null) p1 = getNext(p1, stack1);
        if (p2 != null) p2 = getNext(p2, stack2);      
        while (p1 != null || p2 != null) {
            int temp1 = (p1 == null) ? (int)1e9 : p1.val, temp2 = (p2 == null) ? (int)1e9 : p2.val;
            if (temp1 > temp2) {
                Deque<TreeNode> tmpStack = stack1; stack1 = stack2; stack2 = tmpStack;
                TreeNode tmpp = p1; p1 = p2; p2 = tmpp;
            }
            res.add(p1.val);
            if (p1.right != null) {
                p1 = p1.right;
                p1 = getNext(p1, stack1);               
            } else {
                p1 = stack1.isEmpty() ? null : stack1.pop();
            }
        }
        return res;
    }
}

//2.1
// https://leetcode.com/problems/all-elements-in-two-binary-search-trees/discuss/469952/Java-Inorder-traversal-and-store-to-list
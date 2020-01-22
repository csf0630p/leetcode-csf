// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/discuss/35476/Share-my-JAVA-solution-1ms-very-short-and-concise.
// 1.0 fast-slow pointers to find mid point, O(nlogn)/O(logn)
	public TreeNode sortedListToBST(ListNode head) {
		if(head == null) return null;
		//if(head.next==null)return new TreeNode(head.val);
		ListNode slow = head, fast = head, slowPre = null;
		while(fast.next != null && fast.next.next != null){
		  slowPre = slow;
		  slow = slow.next;
		  fast = fast.next.next;
		}

		if(slowPre != null) slowPre.next = null;
		if(slow == null) return null;
		TreeNode node = new TreeNode(slow.val);
		if(head != slow)node.left = sortedListToBST(head);
		node.right = sortedListToBST(slow.next);
		return node;
	}

// 2.0 transfer linkedlist to array, then generate BST, O(n)/O(n)

// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/discuss/35472/Share-my-O(1)-space-and-O(n)-time-Java-code
// 3.0 first traversal to get size, second in-order generate, O(n)/O(logn)
class Solution {
    private ListNode node;

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null){
            return null;
        }

        int size = 0;
        ListNode runner = head;
        node = head;

        while(runner != null){
            runner = runner.next;
            size ++;
        }

        return inorderHelper(0, size - 1);
    }

    public TreeNode inorderHelper(int start, int end){
        if(start > end){
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode left = inorderHelper(start, mid - 1);

        TreeNode treenode = new TreeNode(node.val);
        treenode.left = left;
        node = node.next;

        TreeNode right = inorderHelper(mid + 1, end);
        treenode.right = right;

        return treenode;
    }
}
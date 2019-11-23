/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//1.0 iterative O(n)/O(1)
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode fwd = null;
        for (ListNode cur = head; cur != null; cur = fwd) {
            fwd = cur.next;
            cur.next = pre;
            pre = cur;
        }
        return pre;        
    }
}

//2.0 recursive
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 // 1.0 one pointer traversal
public class Solution {
    public boolean hasCycle(ListNode head) {        
        ListNode p = head;
        while (p != null) {
            if (p.next == p)
                return true;
            ListNode temp = p.next;
            p.next = p;
            p = temp;
        }
        return false;
    }
}

 // 2.0 two pointers (fast/slow)
     public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = slow.next;
        while (slow != null && fast != null) {            
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
        }
        return false;
    }
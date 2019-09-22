/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 // 1.0 scan twice
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null) return head;
        ListNode pt = head, tail = pt;
        int len = 0;
        while (pt != null) {
            tail = pt;
            pt = pt.next;
            len++;
        }
        // if (len == 0) return null;
        k = k % len;
        if (k == 0) return head;
		tail.next = head;
        pt = head;
        for (int i = 0; i < len - k; i++) {
            tail = pt;
            pt = pt.next;
        }        
        tail.next = null;
        return pt;
    }
}

// 1.1
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) return head;
        ListNode pt = head;
        int len = 1;
        while (pt.next != null) {
            pt = pt.next;
            len++;
        }
        k = k % len;
        if (k == 0) return head;
		pt.next = head;
        for (int i = 0; i < len - k; i++) {
            pt = pt.next;
        }
        head = pt.next;
        pt.next = null;
        return head;
    }

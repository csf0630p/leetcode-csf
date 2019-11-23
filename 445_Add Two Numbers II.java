/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 //1.0 stack O(n)/O(n)
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<>(), stack2 = new LinkedList<>();
		// 用ArrayDeque 替代 LinkedList
        ListNode p1 = l1, p2 = l2;        
        while (p1 != null) {
            stack1.push(p1.val);
            p1 = p1.next;
        }
        while (p2 != null) {
            stack2.push(p2.val);
            p2 = p2.next;
        }        
        int value = 0, carry = 0;
        ListNode prev = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            value = (stack1.isEmpty() ? 0 : stack1.pop()) + (stack2.isEmpty() ? 0 : stack2.pop()) + carry;
            if (value >= 10) {
                value -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            ListNode node = new ListNode(value);
            node.next = prev;
            prev = node;
        }
        return prev;
    }
}
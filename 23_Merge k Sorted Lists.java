/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 // 1.0 heap O(nlogk)/O(n)+O(k)
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;        
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> a.val - b.val);
        ListNode res = new ListNode(0), pt = res;
        for (ListNode node : lists) {
            if (node != null) pq.add(node);
        }
        System.out.println(pq.size());
        while (!pq.isEmpty()) {
            ListNode top = pq.poll();
            pt.next = top;
            pt = pt.next;
            if (top.next != null) pq.add(top.next);            
        }
        return res.next;
    }
}
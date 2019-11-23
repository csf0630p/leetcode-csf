/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
// 1.0 HashMap<OldNode, NewNode>, O(n)/O(n)
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node p = head, p2 = null, prev = null, newHead = null;
        while (p != null) {            
            p2 = new Node(p.val, null, null);
            if (newHead == null) newHead = p2;
            if (prev != null) prev.next = p2;
            prev = p2;
            map.put(p, p2);
            p = p.next;
        }
        p = head; p2 = newHead;
        while (p != null) {
            p2.random = map.get(p.random);
            p = p.next;
            p2 = p2.next;
        }
        return newHead;
    }
}

// 1.1 use hashmap to make each node's next

// 2.0 Without HashMap, double length linked list, O(n)/?
// https://www.cnblogs.com/grandyang/p/4261431.html
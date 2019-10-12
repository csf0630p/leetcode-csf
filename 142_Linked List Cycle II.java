// https://leetcode.wang/leetcode-142-Linked-List-CycleII.html
// 1.0 record with hashmap/modify original linkedlist... O(n)/O(n)


// 2.0 math O(n)/O(1)
slow : t = x + k1 * n + y
fast : 2*t = x + k2 * n + y
delta : t = (k2 - k1) * n
so (k2 - k1) * n = x + k1 * n + y
x = (k2 - 2*k1) * n - y
x = (k2 - 2*k1 - 1) * n + (n - y)

public ListNode detectCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    ListNode meet = null;
    while (fast != null) {
        if (fast.next == null) {
            return null;
        }
        slow = slow.next;
        fast = fast.next.next;
        //到达相遇点
        if (fast == slow) {
            meet = fast;
            while (head != meet) {
                head = head.next;
                meet = meet.next;
            }
            return head;
        }
    }
    return null;
}
// 1.0 scan for two lists' length, then scan again. O(n)/O(1)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0, i = 0;
        ListNode pt = headA, pt2 = headB;
        while (pt != null) {
            lenA++;
            pt = pt.next;
        }
        while (pt2 != null) {
            lenB++;
            pt2 = pt2.next;
        }
        pt = headA;
        pt2 = headB;       
        if (lenA < lenB) {
            while (i < lenB - lenA) {
                pt2 = pt2.next;
                i++;
            }
        } else if (lenA > lenB) {
            while (i < lenA - lenB) {
                pt = pt.next;
                i++;
            }
        }
        while (pt != null) {
            if (pt == pt2) return pt;
            pt = pt.next;
            pt2 = pt2.next;
        }
        return null;
    }
}

// 1.1 scan twice, because m+n == n+m. O(n)/O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pt = headA, pt2 = headB;
        while (pt != null || pt2 != null) {
            if (pt != null && pt2 != null && pt == pt2) return pt;
            pt = (pt == null) ? headB : pt.next;
            pt2 = (pt2 == null) ? headA : pt2.next;
        }
        return null;
    }
	
// 1.2 record by +1, compare sum before and after +1. O(n)/O(1)
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
        return null;
    }

    //步骤 1 
    ListNode tailA = headA;
    int lenA = 0;
    int sumA = 0;

    while (tailA != null) {
        sumA += tailA.val;
        tailA = tailA.next;
        lenA++;
    }

    //步骤 2
    ListNode tailB = headB;
    while (tailB != null) {
        tailB.val = tailB.val + 1;
        tailB = tailB.next;
    }

    //步骤 3
    tailA = headA;
    int sumA2 = 0;
    while (tailA != null) {
        sumA2 += tailA.val;
        tailA = tailA.next;
    }

    //步骤 4
    tailB = headB;
    while (tailB != null) {
        tailB.val = tailB.val - 1;
        tailB = tailB.next;
    }


    if (sumA == sumA2) {
        return null;
    } else {
        for (int i = 0; i < lenA - (sumA2 - sumA); i++) {
            headA = headA.next;
        }
        return headA;
    }

}

// Brute Force, O(mn)/extra O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pt = headA;
        while (headB != null) {
            while (pt != null) {
                if (pt == headB) return pt;
                pt = pt.next;
            }
            headB = headB.next;
            pt = headA;
        }
        return null;
    }

// HashMap, O(n)/extra O(n)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }	
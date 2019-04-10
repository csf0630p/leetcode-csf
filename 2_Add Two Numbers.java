/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //get two length
        int len1 = 0;
        ListNode temp = l1;
        while (temp != null) {
            len1++;
            temp = temp.next;
        }
        int len2 = 0;
        temp = l2;
        while (temp != null) {
            len2++;
            temp = temp.next;
        }
        //if l1 < l2, swap them
        if (len1 < len2) {
            temp = l1; l1 = l2; l2 = temp;
            int tmp = len1; len1 = len2; len2 = tmp;
        }
        // goto l2.end and extend it to align
        temp = l2;
        while (temp.next != null) 
            temp = temp.next;    
        for (int i = 0; i < (len1 - len2); i++) {
            temp.next = new ListNode(0);
            temp = temp.next;
        }           
        // add them
        ListNode res = null, resHead = null;
        int sum = 0, add = 0;     
        while (l1 != null) {                  
            sum = l1.val + l2.val + add;
            if (sum >= 10) {
                sum -= 10;
                add = 1;
            } else {
                add = 0;
            }
            if (res == null) {
                res = new ListNode(sum);
                resHead = res;
            } else {
                res.next = new ListNode(sum);
                res = res.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if (add > 0) {
            res.next = new ListNode(add);
            res = res.next;            
        }
        return resHead;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //get two length
        int len1 = 0;
        ListNode temp = l1;
        while (temp != null) {
            len1++;
            temp = temp.next;
        }
        int len2 = 0;
        temp = l2;
        while (temp != null) {
            len2++;
            temp = temp.next;
        }
        //if l1 < l2, swap them
        if (len1 < len2) {
            temp = l1; l1 = l2; l2 = temp;
            int tmp = len1; len1 = len2; len2 = tmp;
        }
        // goto l2.end and extend it to align
        temp = l2;
        while (temp.next != null) {
            temp = temp.next;
        }
        // System.out.println(temp.val);        
        for (int i = 0; i < (len1 - len2); i++) {
            temp.next = new ListNode(0);
            // System.out.println(temp.val + " " + temp.next.val); 
            temp = temp.next;
        }
        // System.out.println(l2.next.val + " " + l2.next.next.val);           
        // add them
        ListNode res = null, resHead = null;
        int sum = 0, add = 0;     
        while (l1 != null) {
            // System.out.println(l2.val + " " + l2.next.val);                  
            sum = l1.val + l2.val + add;
            if (sum >= 10) {
                sum -= 10;
                add = 1;
            } else {
                add = 0;
            }
            if (res == null) {
                res = new ListNode(sum);
                resHead = res;
            } else {
                res.next = new ListNode(sum);
                res = res.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if (add > 0) {
            res.next = new ListNode(add);
            res = res.next;            
        }
        return resHead;
    }
}
package linkedlist;

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
public class Solution {
	
	class ListNode { 
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
    public ListNode detectCycle(ListNode head) {
        ListNode p = null, q = null;
        p = q = head;
        
        while (p != null && q != null) {
        	// p moves 1 step, q moves two steps
        	p = p.next;
        	q = q.next;
        	
        	if (q != null) {
        		q = q.next;
        	}
        	
        	if (p == q && p != null) { 
        		return p;
        	}
        }
        
        return null;
    }
}
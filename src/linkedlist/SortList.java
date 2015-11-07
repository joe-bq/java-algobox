package linkedlist;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
} 

public class SortList {
	public static void main(String[] args) {
		ListNode head = new ListNode(5);
		ListNode p = head;
		p.next = new ListNode(3);
		p = p.next;
		p.next = new ListNode(8);
		p = p.next;
		p.next = new ListNode(2);
		p = p.next;
		p.next = new ListNode(10);
		p = p.next;
		p.next = new ListNode(7);
		
		
		SortList solution = new SortList();
		// solution.sortList2(head);
		
		/*
		head = solution.sortList3(head);
		p = head;
		while (p != null) {
			System.out.print(p.val + " ");
			p = p.next;
		}
		*/
		
		p = head;
		while (p != null && p.next != null) p = p.next;
		head = solution.sortList_quicksortWithList(head, p);
		p = head;
		while (p != null) {
			System.out.print(p.val + " ");
			p = p.next;
		}
	}
		
	public static class SortedList {
		ListNode head;
		ListNode tail;
	
		public SortedList(ListNode h, ListNode t) { 
			head = h;
			tail = t;
		}
	}
	
	public ListNode sortList_quicksort(ListNode head, ListNode tail) {
		SortedList r = sortList_partition(head, tail);
			
		if (r.head != null && r.head != head) {
			sortList_quicksort(head, r.head);
		}
		
		if (r.tail != null && r.tail != tail) {
			sortList_quicksort(r.tail, tail);
		}
		
		return head;
	}
			
	public SortedList sortList_partition(ListNode head, ListNode tail) {
		if (head == null) return new SortedList(null, tail != null ? tail.next : null);
		if (tail == null) return new SortedList(head != null ? head.next : null,  null);
		if (head == tail) return new SortedList(head, tail);

		ListNode pend = null;
		ListNode q = head.next;
		ListNode rr = head;
		ListNode r = head.next;
			
		
			int temp = head.val;
			for (; rr != tail; r = r.next, rr = rr.next) { 
				if (r.val < temp) {
					
					pend = pend == null ? head.next : pend.next;
					int t = r.val;
					r.val = pend.val;
					pend.val = t;

					q = q.next;
				}
			}
			
			// we cannot just moves, we need to do the copy
			// moves head between pend and q
						
			// we do the move
			ListNode p = null;
			if (pend != null) {
				p = head;
				while (p != pend) { 
					p.val = p.next.val;
					p = p.next;
				}
				pend.val = temp;
			}
			
			return new SortedList(p, q);
	}
	
	public static class SortedList2 { 
		ListNode ph, pe;
		ListNode qh, qe;
		
		public SortedList2(ListNode ph, ListNode pe, ListNode qh, ListNode qe) {
			this.ph = ph;
			this.pe = pe;
			this.qh = qh;
			this.qe = qe;
		}
	}
	
	public ListNode sortList_quicksortWithList(ListNode head, ListNode tail) {
		if (head != tail) {
			SortedList2 r = sortList_partitionWithList(head, tail);
			
			head = r.ph;
			ListNode pivot = r.pe != null ? r.pe.next : null;
			
			if (r.ph != null && r.ph != r.pe) 
				head = sortList_quicksortWithList(r.ph, r.pe);
			if (r.qh != r.qe) {
				
				ListNode n = sortList_quicksortWithList(r.qh, r.qe);
				if (pivot != null) {
					pivot.next = n;
				}
			}
		}

		return head;
	}
	
	public SortedList2 sortList_partitionWithList(ListNode head, ListNode tail) {

		ListNode ph = new ListNode(0), pe = null;
		ListNode qh = new ListNode(0), qe = null;
		
		ListNode p = ph, q = qh;
		
		int temp = head.val;
		for (ListNode rr = head, r = head.next; rr != tail; r = r.next, rr = rr.next) {
			if (r.val < temp) { 
				// append to p
				p.next = r;
				p = p.next;
				pe = p;
			} else { 
				q.next = r;
				q = q.next;
				qe = q;
			}
		}
		/* break the link solution 1, head reaches p, if p reaches head, then we have circle.. first we save the tail.next in case tail overwritten */
		/*
		ListNode t = tail.next;
		if (pe != null) { // if p non-empty and p not at the tail -- we won't create circle at head/tail
			p.next = head;
		}
		
		q.next = t;
		if (qe != null) {
			head.next = qh.next;
		} else { 
			head.next = t;
		}
		*/
		
		/* break the link solution 2, head reaches p, if p reaches head, then we have circle.. first break head to tail.next; */
		head.next = tail.next;
		ListNode t = tail.next;
		
		if (pe != null) { // if p non-empty and p not at the tail -- we won't create circle at head/tail -- commented  && p != tail
			p.next = head;
		}
		
		q.next = t; // still q.next = tail.next;
		if (qe != null) { 
			head.next = qh.next;
		}

		/*
		if (qe != null) {
			head.next = qh.next;
			q.next = tail.next;
		} else {
			head.next = tail.next;
		}
		*/
		
		return new SortedList2(ph.next, pe == null ? ph.next : pe, qh.next, qe == null ? qh.next : qe);
	}
	
	/* one solution is to merge sort ListNode, we need to first find the middle node and then do the split then recursive */
	public ListNode sortList(ListNode head) {
		return sortList3(head);
	}
	
	/* I have with shell sort, but that is not luck */
	public ListNode sortList3(ListNode head) {
		
		int length = 0;
		ListNode p = head;
		ListNode q = head;
		ListNode r = null;
		
		/* one fast way to get length of a list
		while (p != null && q != null) { 
			length++;
			p = p.next;
			if (q != null) {
				q = q.next;
				length++;
			}
		}*/
		
		for (; p != null; length++, p = p.next);
		
		for (int segsize = 1; segsize < length; segsize <<= 1) {
			// we can create a advance method
			// p = advance(p, segsize);
			
			p = head;
			q = null;
			while (p != null) {
				q = p;
				for (int i = 0; i < segsize && q != null; i++, q = q.next);	
				r = q != null ? q.next : null;
				if (p == head) {
					head = merge(p, q, segsize, r);
				}
				else {
					merge(p, q, segsize, r);
				}
				
				p = r;
			}
		}
		
		return head;
	}
	
	/* this is the best merging operation */
	public ListNode merge6(ListNode p, ListNode q) {
		ListNode h = new ListNode(0);
		ListNode merged = h;
		while (p != null && q != null) {
			if (p.val < q.val) { 
				merged.next = p;
				p = p.next;
				merged = merged.next;
			} else { 
				merged.next = q;
				q = q.next;
				merged = merged.next;
			}
		}
		
		if (p != null || q != null) { 
			merged.next = p != null ? p : q;
		}
		
		return h.next;
	}

	
	/* this is not working... */
	public ListNode merge(ListNode p, ListNode q, int segsize, ListNode n) { 
		ListNode head = null;
		
		if (p == null) { 
			return q;
		}
		
		if (q == null) { 
			return p;
		}
		
		if (p.val < q.val) { 
			head = p;
		} else { 
			head = q;
		}
		
		int pc = 0;
		int qc = 0;
		
		ListNode r = null;
		while (pc < segsize && qc < segsize && p != null && q != null) { 
			if (p.val < q.val){ 
				r = p.next;
				p.next = q;
				p = r;
				pc++;
			} else { 
				r = q.next;
				q.next = p;
				q = r;
				qc++;
			}
		}
		
		if (pc == segsize || p == null) { 
			r = q;
			while (q != null && qc++ < segsize) { 
				r = q;
				q = q.next;
			}
			
			r.next = n;
		}
		else { 
			r = p;
			while (p != null && pc++ < segsize) { 
				r = p;
				p = p.next;
			}
			
			r.next = n;
		}
		
		return head;
	}
	
	
	/* another solution is to have some-what middle pointer, HAS NOT TESETED YET*/
	public ListNode sortList2(ListNode head) {
		ListNode middle = head;
		ListNode middleprev = head;
		ListNode middlenext = head.next;
		int index = 0;
		int middleindex = 0;
		ListNode end = head;
		if (head != null) {
			ListNode p = head.next;
			
			while (p != null) 
			{
				

			ListNode q = null;
			ListNode prev = null;
			if (p.val < middle.val) { 
				q = head;
				prev = head;
				middleindex++;
			} else {
				q = middlenext;
				prev = middle;
			}
			
				while (p.val > q.val) {
					prev = q;
					q = q.next;
				}
				
				// remove p 
				end.next = p.next;
				// end = end.next;
				
				// insert p at q
				if (p != q) {
					p.next = q;
				}
				prev.next = p;
				
				if (q == head) {
					head = p;
				}
				
				// move end and p
				end = end.next;
				p = null;
				if (end != null) { 
					p = end.next;
				}
				
				index++;
				int midpos = index / 2;
				if (midpos > middleindex) {
					middleindex = midpos;
					middleprev = middle;
					middle = middle.next;
					middlenext = middle.next;
				} else if (midpos < middleindex) { 
					middleindex = midpos;
					//middleprev = ?;
					middle = middleprev;
					ListNode x = head;
					while (x.next != middle) {
						x = x.next;
					}
					middleprev = x;
					middlenext = middle.next;
				}
			}
		}
		return head;
	}
	
	
	/* merge two list, each list ends with nil */
	public ListNode merge(ListNode p, ListNode q) { 
		if (p == null) {
			return q;
		}
		
		if (q == null) { 
			return p;
		}
		
		ListNode head = null;
		if (p.val < q.val) {
			head = p;
		} else { 
			head = q;
		}
		
		ListNode r = null;
		while (p.next != null && q.next != null) { 
			if (p.val < q.val) {
				while (p.next.val < q.val) p = p.next;
				
				if (p.next != null) {
					r = q.next; 
					q.next = p.next;
					p.next = q;
					p = q;
					q = r;
				}
				
			} else  {
				while (q.next.val < p.val) q = q.next; // < or <=
				
				if (q.next != null) { 
					r = p.next;
					p.next = q.next;
					q.next = p;
					q = p;
					p = r;
				}
			}
		}
		
		if (p.val < q.val) {
			r = p.next;
			p.next = q;
			p = r;
		} else { 
			r = q.next;
			q.next = p;
			q = r;
		}
		
		return head;
	}
	
	/* merge two list, each list ends with nil - with prev pointer, we can save one post condition check */
	public ListNode merge2(ListNode p, ListNode q) { 
		if (p == null) {
			return q;
		}
		
		if (q == null) { 
			return p;
		}
		
		ListNode head = null;
		if (p.val < q.val) {
			head = p;
		} else { 
			head = q;
		}
		
		ListNode r = null;
		ListNode pp = null;
		ListNode pq = null;
		
		while (p != null && q != null) { 
			pp = p;
			pq = q;
			if (p.val < q.val) {
				while (p != null && p.val < q.val) {
					pp = p;
					p = q.next;
				}
				
				pp.next = q;
				r = q.next;
				q.next = p;
				q = r;
				
				/*
				if (p != null) {
					r = p.next;
					p.next = q;
					p = r;	
				}
				*/
			} else  {
				while (q != null && q.val < p.val) {
					pq = q;
					q = q.next;
				}
				
				pq.next = p;
				r = p.next;
				p.next = q;
				q = r;
				/*
				if (q != null) {
					r = q.next;
					q.next = p;
					q = r;	
				}
				*/
			}
		}
		
		if (p == null) { 
			pp.next = q;
		} else {
			pq.next = p;
		}
		
		return head;
	}
	
	
	/* merge two list, each list ends with nil - we can create a non-nil head, which sometimes can save us some null checking.  */
	public ListNode merge3(ListNode p, ListNode q) { 
		if (p == null) {
			return q;
		}
		
		if (q == null) { 
			return p;
		}
		
		ListNode head = new ListNode(0);
		
		ListNode r = null;
		ListNode pp = null;
		ListNode pq = null;
		
		while (p != null && q != null) { 
			pp = p;
			pq = q;
			if (p.val < q.val) {
				if (head.next == null) {
					head.next = p;
				}
				
				while (p != null && p.val < q.val) {
					pp = p;
					p = q.next;
				}
				
				pp.next = q;
				r = q.next;
				q.next = p;
				q = r;
				
				/*
				if (p != null) {
					r = p.next;
					p.next = q;
					p = r;	
				}
				*/
			} else  {
				if (head.next == null) {
					head.next = q;
				}
				
				while (q != null && q.val < p.val) {
					pq = q;
					q = q.next;
				}
				
				pq.next = p;
				r = p.next;
				p.next = q;
				q = r;
				/*
				if (q != null) {
					r = q.next;
					q.next = p;
					q = r;	
				}
				*/
			}
		}
		
		if (p == null) { 
			pp.next = q;
		} else {
			pq.next = p;
		}
		
		return head.next;
	}
	
	
	/* merge two list, each list ends with nil - we can create a non-nil head, which sometimes can save us some null checking. seems that we don't need to do the last checking part. 
	 * So there is no need to keep the so-called pp and pq ?? */
	public ListNode merge4(ListNode p, ListNode q) { 
		if (p == null) {
			return q;
		}
		
		if (q == null) { 
			return p;
		}
		
		ListNode head = p.val < q.val ? p : q;

		ListNode r = null;
		ListNode pp = null;
		ListNode pq = null;
		
		while (p != null && q != null) { 
			pp = p;
			pq = q;
			if (p.val < q.val) {
				
				while (p != null && p.val < q.val) {
					pp = p;
					p = q.next;
				}
				
				pp.next = q;
				r = q.next;
				q.next = p;
				q = r;
				
				/*
				if (p != null) {
					r = p.next;
					p.next = q;
					p = r;	
				}
				*/
			} else  {
				
				while (q != null && q.val < p.val) {
					pq = q;
					q = q.next;
				}
				
				pq.next = p;
				r = p.next;
				p.next = q;
				q = r;
				/*
				if (q != null) {
					r = q.next;
					q.next = p;
					q = r;	
				}
				*/
			}
		}
		
		return head.next;
	}
	
	public ListNode merge5(ListNode p, ListNode q) { 
		if (p == null) {
			return q;
		}
		
		if (q == null) { 
			return p;
		}
		
		ListNode head = p.val < q.val ? p : q;

		ListNode r = null;
		ListNode pp = null;
		ListNode pq = null;
		
		while (p != null && q != null) { 
			pp = p;
			pq = q;
			if (p.val < q.val) {
				while (p != null && p.val < q.val) {
					pp = p;
					p = q.next;
				}
				
				pp.next = q;
				r = q.next;
				q.next = p;
				q = r;
				
				/*
				if (p != null) {
					r = p.next;
					p.next = q;
					p = r;	
				}
				*/
			} else  {
				
				while (q != null && q.val < p.val) {
					pq = q;
					q = q.next;
				}
				
				pq.next = p;
				r = p.next;
				p.next = q;
				q = r;
				/*
				if (q != null) {
					r = q.next;
					q.next = p;
					q = r;	
				}
				*/
			}
		}
		
		return head.next;
	}
	
}


/* we cann do both move and copy at the same time, either all copy or all moving, otherwise, conflict occurs , test data, 5,3,8,2 */
/*
public static class SortedList {
	ListNode head;
	ListNode tail;
	ListNode newHead;
	
	public SortedList(ListNode h, ListNode t, ListNode newHead) { 
		head = h;
		tail = t;
		this.newHead = newHead;
	}
}

public SortedList sortList_partition(ListNode head, ListNode tail) {
	if (head == null) return new SortedList(null, tail != null ? tail.next : null, tail != null ? tail : null);
	if (tail == null) return new SortedList(head != null ? head.next : null,  null, head != null ? head : null);
	if (head == tail) return new SortedList(head, tail, head);

	ListNode p = head.next, pend = null;
	ListNode q = head.next;
	ListNode rr = head;
	ListNode r = head.next;
		
	
		int temp = head.val;
		for (; rr != tail; r = r.next, rr = rr.next) { 
			if (r.val < temp) { 		
				if (pend != null) {
					pend = pend.next;
					
					int t = r.val;
					r.val = pend.val;
					pend.val = t;
				} else { 
					pend = head.next;
					int t = r.val;
					r.val = pend.val;
					pend.val = t;
				}
				
				q = q.next;
			}
		}
		
		// we cannot just moves, we need to do the copy
		// moves head between pend and q
		
		if (head.next != q) {
			head.next = q;
			pend.next = head;	
		}
		
		// we do the move

		return new SortedList(pend, q, p);
}
*/
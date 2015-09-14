package array;

/*
Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
 */

public class Solution {
	public void merge(int A[], int m, int B[], int n) { 
		int p = m - 1, q = n - 1, r = m + n;
		
		while (p >= 0 && q >= 0) { 
			if (A[p] > B[q]) { 
				A[--r] = A[p--];
			} else if (A[p] < B[q]) { 
				A[--r] = B[q--];
			} else {
				A[--r] = A[p--];
				A[--r] = B[q--];
			}
		}
		
		while (q >= 0) {
			A[--r] = B[q--];
		}
		
	}
	
	public static void main(String[] args) {
		int[] A = new int[] { 1, 5, 8, 10, 15, 0, 0 ,0, 0, 0 };
		int[] B = new int[] { 2, 3, 7, 17, 19 };
		
		Solution solution = new Solution();
		solution.merge(A, 5, B, 5);
		
		
		for (int i = 0; i < 10; i++) {
			System.out.print(A[i]);
			System.out.print(" ");
		}
		
		
	}

}

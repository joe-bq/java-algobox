package sort.quick;

import static java.util.Arrays.*;

import java.util.Arrays;

/*
 * Find kth largest element in an array 
 * 
 * the original problem is listed below
 * 
 * 	https://leetcode.com/problems/kth-largest-in-an-array/
 */

public class FindKthLargestSolution {
	public static void main(String[] args) {
		int[] nums = new int[] { 3, 1, 2, 4};
		FindKthLargestNaiiveSolution solution = new FindKthLargestNaiiveSolution();
		
		int number = solution.findKthLargest(nums, 2);
		System.out.println("Found " + number);
		
		nums = new int[] { -1, -1 };
		number = solution.findKthLargest(nums, 2);
		System.out.println("Found " + number);
		
		nums = new int[] { 99, 99 };
		number = solution.findKthLargest(nums, 1);
		System.out.println("Found " + number);
		
		nums = new int[] { 3, 2, 1, 5, 6, 4 };
		number = solution.findKthLargest(nums, 2);
		System.out.println("Found " + number);
	}
	
	// First though solution is to use the partition introduced in the Quick sort
	public int findKthLargest(int[] nums,  int k ) { 
		return findKthLargest(nums, k, 0, nums.length-1);
		}
	
	public int findKthLargest(int[] nums, int k, int p, int q) {
		int r = partition(nums, p, q);
//		if (k <= 0) {
//			return nums[k];
//		}
		
		if (r + k == q+1) {
			return nums[r];
		} else if (r + k > q + 1) {
			return findKthLargest(nums, k-q+r-1, p, r-1);
		} else { // r + k > p
			return findKthLargest(nums, k, r + 1, q);
		}
	}
	
	/*
	 * There are two implementaion of the quick sort partition algorithm
	 * one is he Lomuto partition schema
	 * another is the Hoarse partition schema.
	 * 
	 * here we used the Lomuto schema, tha hoarse schema has inner loop which advance both ends towards middle
	 */
	public int partition(int[] nums, int p, int q) { 
		int r = p;
		int pivot = nums[q];
		
		while (p < q) { 
			if (nums[p] <= pivot) { 
				int temp = nums[p];
				nums[p] = nums[r];
				nums[r] = temp;
				r++;
			}
			p++;
		}
		
		int temp = nums[q];
		nums[q] = nums[r];
		nums[r] = temp;
		
		return r;
	}
}

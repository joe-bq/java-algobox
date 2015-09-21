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
public class FindKthLargestNaiiveSolution {
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
		Arrays.sort(nums);;
		return nums[nums.length - k];
	}
}

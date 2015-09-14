package searches;

import java.util.*;
import java.math.*;



public class ContainsNearbyAlmostDuplicate {
	public static void main(String[] args) {
		int[] array = new int[] { -1, 2147483647};
		// int[] array = new int[] {99, 99};
		// int[] array = new int[] {1};
		
		ContainsNearbyAlmostDuplicate solution  = new ContainsNearbyAlmostDuplicate();
		boolean contains =  solution.containsNearbyAlmostDuplicate(array, 1, 2147483647);
		
		if (contains) { 
			System.out.println("Contains");
		} else { 
			System.out.println("Does not contains");
		}
		
//		boolean contains = solution.
	}
	
	// Reference the implementation 
	// https://leetcode.com/discuss/48670/o-n-python-using-buckets-with-explaination-10-lines
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		HashMap<Integer, Integer> buckets = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) { 
			// t == 0 is a special case where we only have to check the bucket that i is in.
			int bucketNum, offset;
			
			if (t > 0) {
				bucketNum = nums[i]/t;
				offset = 1;
			} else {
				bucketNum = 1;
				offset = 0;
			}
			
			for (int j = bucketNum - offset; j <= bucketNum + offset; j++) {
				if (buckets.containsKey(j)) {
					if (Math.abs((long)(buckets.get(j) - nums[i])) <= (long)t) { 
						return true;
					}
				}
			}
			
			// update the bucket
			buckets.put(bucketNum,  nums[i]);
			
			// cut down the unnecessary buckets 
			if (buckets.size() > k) {
				if (t > 0) { 
					buckets.remove(nums[i-k]/t);
				} else {
					buckets.remove(nums[i-k]);
				}
				
			}
		}
		return false;
	}
}

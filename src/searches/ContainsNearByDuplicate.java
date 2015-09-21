package searches;
import java.util.*;
// the problem lies: 
//   https://leetcode.com/problems/contains-duplicate-ii/
public class ContainsNearByDuplicate {
	public static void main(String[] args) {
		int[] array =new int[] { 1, 3, 5, 6, 3, 9, 10};
//		int[] array = new int[] { 99, 99 };
//		int[] array = new int[] { 1 };
		ContainsNearByDuplicate solution = new ContainsNearByDuplicate();
		boolean contains = solution.containsNearByDuplicate(array, 2);
		if (contains) { 
			System.out.println("Contains");
		} else {
			System.out.println("Does not contains");
		}
	}
	
	// well this simple solution exceeds the time limit
	/*
	public boolean containsNearByDuplicate(int[] nums, int k) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; i + j < nums.length && j < k; j++) { 
				if (nums[i] == nums[i+j]) { 
					return true;
				}
			}
		}
		
		
		return false;
	}
	*/
	
	public boolean containsNearByDuplicate(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < nums.length ; i++) {
			if (map.containsKey(nums[i])) { 
				int diff = i - map.get(nums[i]);
				if (diff <= k) return true;
			}
			
			map.put(nums[i], i);
		}
		
		return false;
	}
}

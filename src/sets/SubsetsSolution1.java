package sets;

import java.util.ArrayList;
import java.util.List;

/*
Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */

/*

so, group each elements as a sub-group, and then combines each cross sub-group to form elements.

to create sub-group, we can do that for length = 0 to n;

to create cross sub-group, then we  use combinations techniques.


Again, this is not a permutation problems.

this is a dynamic/recursive solution.

 */


public class SubsetsSolution1 {

	public List<List<Integer>> subsetsWithDup(int[] num) { 
		return null;
	}
	
	public List<List<Integer>> fullRange(int[] values, int counts[], int length) {
		if (length == 0) {
			List<List<Integer>> empty = new ArrayList<List<Integer>>();
			empty.add(new ArrayList<Integer>());
			return empty;
		}
		
		List<List<Integer>> fullsets = new ArrayList<List<Integer>>();
		
		List<List<Integer>> subrange = fullRange(values, counts, length - 1);
		for (int i = 0; i <= counts[length-1]; i++){
			for (List<Integer> set : subrange) {
				ArrayList<Integer> newSet = new ArrayList<Integer>();
				newSet.addAll(set);
				for (int j = 0; j < i; j++) { 
					newSet.add(values[length-1]);
				}
				
				fullsets.add(newSet);
			}
		}
				
		return fullsets;
	}
	
	public static void main(String[] args) { 
		int[] num = new int[] { 1, 2, 2};
		int[] values = new int[num.length+1];
		int[] counts = new int[num.length+1];
		
		for (int i = 0; i < num.length+1; i++) {
			values[i] = counts[i] = 0;
		}
		
		int length = 0;
		
	    if (num.length > 0) {
	    	values[0] = num[0];
	    	counts[0] = 1;
	    	length = 1;
	    }
	    
	    for (int i = 1; i < num.length; i++) {
	    	if (num[i] == num[i-1]) {
	    		counts[length -1]++;
	    	} else {
	    		values[length] = num[i];
	    		counts[length++]++;
	    	}
	    }
	    
	    
	    SubsetsSolution1 solution = new SubsetsSolution1();
	    List<List<Integer>> fullsets = solution.fullRange(values, counts, length);
	    
	    for (List<Integer> set : fullsets) {
	    	System.out.print("[");
	    	for (int i : set) { 
	    		System.out.print(i);
	    	}
	    	System.out.println("]");
	    }
	    
	}
}

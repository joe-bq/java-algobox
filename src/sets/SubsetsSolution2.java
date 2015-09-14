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
public class SubsetsSolution2 {

	
	public List<List<Integer>> subsetsWithDup(int[] num) { 
		
		sort(num);
		
		int[] values = new int[num.length];
		int[] counts = new int[num.length];
		int length = 0;
		
		if (num.length > 0) {
			values[0] = num[0];
			counts[0] = 1;
			length = 1;
		}
		
		for (int i = 1; i < num.length; i++) {
			if (num[i] == num[i-1]) {
				counts[length-1]++;
			}
			else {
				values[length] = num[i];
				counts[length] = 1;
				length++;
			}
		}
		
		// non-recursive version
		int[] bookkeeper = new int[length + 1];
		
		
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		
		for (int i = 0; i < length; i++) { 
			bookkeeper[i] = 0;
		}
		
		
		boolean  end = false;
		while (bookkeeper[length] == 0) {
			for (int i = 0; i <= counts[0]; i++) { 
				bookkeeper[0] = i;
				ret.add(produce(num, bookkeeper, length));
			}
			
			int next = 1;
			while (next < length && bookkeeper[next] == counts[next]) {
				bookkeeper[next++] = 0;
			}
			
			bookkeeper[next]++;
		}

		
		// generates.. 
		return ret;
	}
	
	public List<Integer> produce(int[] num, int[] count, int length) { 
		
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) { 
			for (int j = 0; j < count[i]; j++) { 
				result.add(num[i]);
			}
		}
		
		return result;
	}
	
	/*
		int i = 0;
		List<Integer> combination = new ArrayList<Integer>();
		outer:
		for (; i < length; i++) {
			for (int j = 0; j < counts[i]; j++) {
				for (int p = 0; p < j; p++) { 
					combination.add(values[j]);
				}
				
				i++;
				continue outer;
			}
		}
	 */
	
	public int[] sort(int[] num) {
		for (int i = 0; i < num.length; i++) {
			for (int j = i + 1; j < num.length; j++) {
				if (num[i] > num[j]) { 
					int temp = num[i];
					num[i] = num[j];
					num[j] = temp;
				}
			}
		}
		
		return num;
	}
	
	
	public static void main(String[] args) { 
		/*
		int length = 3;
		int[] counts = {1, 2, 3};
		int[] keeper = new int[3];
		for (int i = 0; i < 3; i++) { 
			keeper[i] = 0;
		}
		
		boolean end = false;
		while (!end) { 
			for (int i = 0; i <= counts[0]; i++) { 
				keeper[0] = i;
				
				for (int j = 0; j < length; j++) {
					System.out.print(keeper[j]);
				}
				
				System.out.println();
			}
			
			int next = 1;
			while (next < length && keeper[next] == counts[next]) {
				keeper[next++] = 0;
			}
			
//			for (int j = 0; j < length; j++) {
//				System.out.print(keeper[j]);
//			}
//			
//			System.out.println();
			
			if (length == next) {
				end = true;
				break;
			}
			
			keeper[next]++;
			
		}
		*/
		
		int length = 3;
		int[] nums = { 1, 2, 2 };
		
		SubsetsSolution2 solution = new SubsetsSolution2();
		List<List<Integer>> sets = solution.subsetsWithDup(nums);
		for (List<Integer> list : sets) {
			
			System.out.print("[");;
			for (int i : list) { 
				System.out.print(i);
			}
			System.out.print("]");
		}
		
	}
}

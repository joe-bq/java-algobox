package triangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

 */

/* 
 * 
 * in this Solution , we are going to use the DP methods, the Dynamic allocation methods 
 */

public class Solution2 {
	
	public int mimimumTotal(List<List<Integer>> triangle) {
		
		int[] minimums = new int[triangle.size()];
		
		int length = minimums.length;
		for (int i = 0; i < minimums.length; i++) {
			minimums[i] = triangle.get(minimums.length - 1).get(i);
		}
		
		for (int i = length - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) { 
				int rlow = j-1 > 0 ? j-1: 0;
				int rhigh = j+1 < i ? j+1 : i;
				
				int min = Integer.MAX_VALUE;
				for (int k = rlow; k <= rhigh; k++) { 
					if (min > minimums[k]) {
						min = minimums[k];
					}
				}
				
				minimums[j] = min + triangle.get(i).get(j);
			}
		}
		
		return minimums[0];
	}

	public static void main(String[] args) {
		List<List<Integer>> triangle = new ArrayList<List<Integer>>(4);
		
		triangle.add(Arrays.asList(2));
		triangle.add(Arrays.asList(3, 4));
		triangle.add(Arrays.asList(6,5,7));
		triangle.add(Arrays.asList(4, 1, 8, 3));
		
		Solution2 solution = new Solution2();
		System.out.println("minimums value is " + solution.mimimumTotal(triangle));
	}
}

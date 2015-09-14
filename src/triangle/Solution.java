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
public class Solution {

	private int[] _breadcrumb;
	private int[] _minimumBreadCrumb;
	
	public int mimimumTotal(List<List<Integer>> triangle) { 
		_breadcrumb = new int[triangle.size()];
		_minimumBreadCrumb = new int[triangle.size()];
		int length = _breadcrumb.length;
		
		for (int i = 0; i < _breadcrumb.length; i++) { 
			_breadcrumb[i] = 0;
		}
		

		return triangle.get(0).get(0) + mininumTotalRanged(triangle, 1);
	}
	
	public int mininumTotalRanged(List<List<Integer>> triangle, int i) {
		if (i >= _breadcrumb.length) return 0;
		int	rlow = _breadcrumb[i - 1] - 1 > 0 ? _breadcrumb[i-1] - 1 : 0;
		int	rhigh = _breadcrumb[i-1] + 1 < i ? _breadcrumb[i-1] + 1 : i;

		int minimum = Integer.MAX_VALUE;
		for (int j = rlow; j <= rhigh; j++) { 
			_breadcrumb[i] = j;
			int result = triangle.get(i).get(j) + mininumTotalRanged(triangle, i + 1);
			if (result < minimum) { 
				minimum = result;
			}
		}
		
		return minimum;
	}
	
	
	public static void main(String[] args) { 
		List<List<Integer>> triangle = new ArrayList<List<Integer>>(4);
		int[] a = new int[] { 2};
		
		triangle.add(Arrays.asList(2));
		triangle.add(Arrays.asList(3, 4));
		triangle.add(Arrays.asList(6,5,7));
		triangle.add(Arrays.asList(4, 1, 8, 3));
		
		Solution solution = new Solution();
		int result = solution.mimimumTotal(triangle);
		System.out.println("Total is " + result);


	}
}

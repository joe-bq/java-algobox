package array;



/*
 * Leet code program,
 * 	https://leetcode.com/problems/rotate-array/
 * we have three in general solution , two are in-place and the one requires additional spaces. ? -- wrong, both requires extra spaces
 * 
 * solution 1:
 * 	using circular array index calculation methods - in-place
 * solution 2:
 * 	create a sub-array (k length), copy overflow to sub-array and then move 
 * solution 3:
 *  Move one by one and one iteration, which is notoriously slow.
 * 
 */
public class RotateArraySolution {
	public static void main(String[] args) {
		int[] arrays = new int[] { 1,2, 3, 4, 5, 6, 7};
		RotateArraySolution solution = new RotateArraySolution();
		/*
		solution.rotate(arrays, 3);
		
		for (int i : arrays) { 
			System.out.print(""+i+",");
		}
		
		System.out.println();
		arrays = new int[] { 1,2, 3, 4, 5, 6, 7};
		solution.rotate(arrays, 4);
		
		for (int i : arrays) { 
			System.out.print(""+i+",");
		}
		
		System.out.println();
		arrays = new int[] { 1,2, 3, 4, 5, 6, 7};
		solution.rotate(arrays, 3);
		
		for (int i : arrays) { 
			System.out.print(""+i+",");
		}
		System.out.println();
		arrays = new int[] { 1,2, 3, 4, 5, 6};
		solution.rotate(arrays, 2);
		
		for (int i : arrays) { 
			System.out.print(""+i+",");
		}
		*/
		
		System.out.println();
		arrays = new int[] { 1};
		solution.rotate(arrays, 0);
		
		for (int i : arrays) { 
			System.out.print(""+i+",");
		}
	}
	
	/*
	 * 
	 * attempt to partition the array into two parts, x, y and judge x.length <=> y.length, do swap on , this is not an easy solution.
	public void rotate(int[] nums, int k) { 
		
		if (k <= nums.length - k) { 
			// swap x, and y, and move the middle 
			for (int i = 0; i < k; i++) { 
				int temp = nums[i];
				nums[i] = nums[i+k];
				nums[i+k] = temp;
			}
			
			// recursive... on x-y...
		}
	}
	
	*/
	
	/* this is the best solution that I can think of */
	public void rotate(int[] nums, int k) {
		if (k <= 0) return;
		
		int j = nums.length-1;
		int temp = nums[j];
		
		int i = 0;
		int g = (int) nums.length / gcd(nums.length, k);
		// get the common divisor, least common denominator, then you will know after how much iteration, you will need to enter another loop.
		while (i < nums.length) { 
			int t = nums[j];
			nums[j] = temp;
			temp = t;
			j = (j + k) % nums.length;
			i++;
			
			if (i % g == 0) {
				nums[j] = temp; // temp to store.
				j = (j + nums.length - 1) % nums.length;
				temp = nums[j];
			}
		}
		
		nums[j] = temp;
	}
	
	public int gcd(int a, int b) { 
		while (a != b) { 
			if (a > b) {
				a = a - b;
			} else {
				b = b - a;
			}
		}
		
		return a;
	}
	
	public int lcd(int a, int b) {
		int gcd = gcd(a, b);
		return a * b / gcd;
	}
	
	
	/* 
	 * Copy with circular index - actually we don't need the else branch
	 */
	
	/*
	public void rotate(int[] nums, int k) {
		// we have to consider overlapping 
		// now we divied the last k in two group 
		//          nums.length-k, ... i, nums.length-1
		// if we do the rotate backwards,  suppose j > i && j < nums.length-1, and (j+k) % nums.length > nums.length-k, then we corrupt the array
		// we can judge if we move left/right by 
		
		int[] newArray = new int[nums.length];
		
		if (k <= nums.length / 2) {
			// moving back to head
			for (int i = 0; i < nums.length; i++) { 
				int s = nums.length-1-i;
				int t = (nums.length-1-i+k) % nums.length;
				newArray[t] = nums[s];
			}
			
			for (int i = 0; i < nums.length; i++) { 
				nums[i] = newArray[i];
			}
		} else {
			// moving head to back
			k = nums.length - k;
			for (int i = 0; i < nums.length; i++) { 
				int s = i;
				int t = (nums.length + i - k)% nums.length;
				newArray[t] = nums[s];
			}
			
			for (int i = 0; i < nums.length; i++) { 
				nums[i] = newArray[i];
			}
		}
	}
	*/
	
	
	/*
		we create an extra array which copies to the sub-array then we do the move and rotate.
	*/
	
	/*
	public void rotate(int[] nums, int k) {
		int[] subArrays = new int[k];
		// k = 3, length = 7, i == 3..7; 
		// 
		for (int i = nums.length-k; i < nums.length; i++) {
			subArrays[i+k-nums.length] = nums[i];
		}
		
		// move the array k steps forwards, we need to do that in reverse way 
		/*for (int i = 0; i < nums.length-k; i++) {
			nums[i+k] = nums[i];
		}
		* /
		for (int i = nums.length-1; i >= nums.length-1-k; i--) { 
			nums[i] = nums[i-k];
		}
		// copy the values back 
		for (int i = 0;i < k; i++) {
			nums[i] = subArrays[i];
		}
	}
	 */
}

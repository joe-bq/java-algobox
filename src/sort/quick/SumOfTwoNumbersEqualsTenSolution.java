package sort.quick;

import java.util.Arrays;
import static java.lang.System.out;

public class SumOfTwoNumbersEqualsTenSolution {
	public static void main(String[] args) {
		int[] numbers = new int[] { 1, 3, 3, 7, 9 };
		
		SumOfTwoNumbersEqualsTenSolution solution = new SumOfTwoNumbersEqualsTenSolution();
		solution.findAllSumTenNumbers(numbers);
	
	}
	public void findAllSumTenNumbers(int[] nums) { 
		// we need to do sorting first
		Arrays.sort(nums);
		
		int p = 0;
		int q = nums.length - 1;
		
		while (p < q) {
			if (nums[p] + nums[q] == 10) {
				out.println("Found " + nums[p] + " " + nums[q]);
				p++;
				q--;
			} else if (nums[p] + nums[q] > 10) {
				q--;
			} else { 
				p++;
			}
		}
	}
}

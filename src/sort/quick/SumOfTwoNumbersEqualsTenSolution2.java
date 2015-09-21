package sort.quick;

import java.util.Arrays;
import static java.lang.System.out;

public class SumOfTwoNumbersEqualsTenSolution2 {
	public static void main(String[] args) {
		int[] numbers = new int[] { 1, 3, 3, 7, 9 };
		
		SumOfTwoNumbersEqualsTenSolution2 solution = new SumOfTwoNumbersEqualsTenSolution2();
		solution.findAllSumTenNumbers(numbers);
	
	}
	public void findAllSumTenNumbers(int[] nums) { 
		// we need to do sorting first
		Arrays.sort(nums);
		
		int[] complements = new int[nums.length];
		
		for (int i = 0; i < nums.length; i++) {
			complements[nums.length - i - 1] = 10 - nums[i];
		}
		
		int i = 0, j = 0;
		// . there is  a condition suppose, that we have 5, 5, in the middle
		while (i < nums.length && j < nums.length && nums[i] <= 5) {
			if (nums[i] == complements[j]) { 
				out.println("Found "+ nums[i] + " "  + (10 - nums[i]));
				i++;
				j++;
			} else if (nums[i] > complements[j]) { 
				j++;
			} else {
				i++;
			}
		}
	}
}
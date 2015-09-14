package array;

/*
Given a no-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list. 
 */
public class PlusOneSolution {

	public int[] plusOne(int[] digits) { 
		
		int length = digits.length;

		int i = length - 1;
		while (i >= 0 && digits[i] == 9) {
			digits[i--] = 0;
		}
		
		if (i == -1) {
			int[] carried = new int[length + 1];
			carried[0] = 1;
			for (int j = 1; j < length + 1; j++) { 
				carried[j] = 0;
			}
			digits = carried;
		} else {
			digits[i]++;
		}
		
		return digits;
	}
	
	
	public static void main(String[] args) { 
		int[] digits = new int[] { 8, 9, 9};
		PlusOneSolution solution = new PlusOneSolution();
		digits = solution.plusOne(digits);
		
		for (int i = 0; i < digits.length; i++) { 
			System.out.print(digits[i]);
		}
		
		System.out.println();
		digits = new int[] {9, 9, 9};
		digits = solution.plusOne(digits);
		for (int i = 0; i < digits.length; i++) { 
			System.out.print(digits[i]);
		}
		
		System.out.println();
		digits = new int[] {1, 0 , 1};
		digits = solution.plusOne(digits);
		for (int i = 0; i < digits.length; i++) { 
			System.out.print(digits[i]);
		}
	}
	
}

package dp;


/*
 * Numer of digit One
 * 
 * the leet code problem 
 * 	https://leetcode.com/problems/number-of-digit-one/
 * Given an integer n, cou7nmt the total number of digit 1 appearing in all no-negative integers less or equal to n
 */
public class NumberOfDigitOneSolution {
	public static void main(String[] args) {
		int number = 342;
		
		NumberOfDigitOneSolution solution = new NumberOfDigitOneSolution();
//		System.out.println("342 = " + solution.countDigitOne(number));
//		
//		number = 302;
//		System.out.println("302 = " + solution.countDigitOne(number));
//		
//		number = 100;
//		System.out.println("100 = " + solution.countDigitOne(number));
//		
//		number = 200;
//		System.out.println("200 = " + solution.countDigitOne(number));
		
		
		number= 11;
		System.out.println("11 = " + solution.countDigitOne(number));
		
		number= 13;
		System.out.println("13 = " + solution.countDigitOne(number));
		
		number= 99;
		System.out.println("99 = " + solution.countDigitOne(number));
		
		number= 100;
		System.out.println("100 = " + solution.countDigitOne(number));
		
		number= 116;
		System.out.println("116 = " + solution.countDigitOne(number));
		
		number= 999;
		System.out.println("999 = " + solution.countDigitOne(number));
		
		number= 2;
		System.out.println("2 = " + solution.countDigitOne(number));
		
		
	}
	
	public int countDigitOne(int n) {
		
	/*
	    this part is the analysis part
	    
		int zero = 0;
		int unitPlaceOnes = 1; // 1
		int tensPlaceOnes = 12; // 1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 31, 41, 51, 61, 71, 81, 91
		int hundredsPlaceOnes = 11 * tensPlaceOnes + 1; // 1, 10, 11, 21, ... 91, 100, 101, 110, 111, 191, 201, 210, 211, 221,.., 301, 310, 311, 321, ..., 901, 910, 911, 921, .. 991  
		int thousandsPlaceOnes = 11 * hundredsPlaceOnes + 1; // 1000, 1001, 1010, 1011, 1100, 1101, 1110, 1111,  2000, 2001, 2010, 2011, 2100, 2101, 2110, 2111,, ...
	*/
		/*
		int[] countOnes = new int[32];
		countOnes[0] = 0;
		countOnes[1] = 1;
		countOnes[2] = countOnes[1] * 11 + 1;
		countOnes[3] = countOnes[2] * 11 + 1;
		*/
		
		/*
		int[] countOnes = new int[32];
		countOnes[0] = 0;
		countOnes[1] = 1;
		countOnes[2] = countOnes[1] * 19 + 1; // 10 ... 19 == 10x + 1 (where x = 1) == 11
		countOnes[3] = countOnes[2] * 19 + 1; 
		*/
		
		int[] countOnes = new int[32];
		countOnes[0] = 0;
		countOnes[1] = 1;
		countOnes[2] = countOnes[1] * 10 + 10; 
		countOnes[3] = countOnes[2] * 10 + 100; 
		
		int[] powers = new int[32];
		powers[0] = 1;
		powers[1] = 10;
		powers[2] = 100;
		powers[3] = 1000;
		
		int countN = 0;
		int digit = 0;
		int number = n;
		while (number > 0) {
			int remains = number % 10;
			if (digit > 0 && countOnes[digit] == 0) {
				// get digit 
				countOnes[digit] = countOnes[digit-1] * 10 + (int)Math.pow(10, digit-1);
				powers[digit] = (int)Math.pow(10, digit);
			}
			
			/*
			if (remains > 0) {
				if (remains == 1) { 
					int tail = n % (int)Math.pow(10, digit);
					countN += countOnes[digit] + tail + 1;
				} else if (remains > 1) {
					countN += (remains - 1) * countOnes[digit] + 10 * countOnes[digit] + 1;
				}
			}
			*/
			
			if (remains > 0) {
				if (remains == 1) { 
					int tail = n % (int)Math.pow(10, digit);
					countN += countOnes[digit] + tail + 1;
				} else if (remains > 1) {
//					countN += remains * countOnes[digit] + (int)Math.pow(10, Math.max(digit-1, 0));
					countN += remains * countOnes[digit] + powers[digit];
				}
			}
			
			digit++;
			number /= 10;
		}
		// so the general rules of how to count ones is as follow
		
		return countN;
	}
}

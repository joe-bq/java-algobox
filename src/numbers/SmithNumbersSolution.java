package numbers;

import java.util.ArrayList;

/*
Description

While skimming his phone directory in 1982, Albert Wilansky, a mathematician of Lehigh University,noticed that the telephone number of his brother-in-law H. Smith had the following peculiar property: The sum of the digits of that number was equal to the sum of the digits of the prime factors of that number. Got it? Smith's telephone number was 493-7775. This number can be written as the product of its prime factors in the following way: 
4937775= 3*5*5*65837

The sum of all digits of the telephone number is 4+9+3+7+7+7+5= 42,and the sum of the digits of its prime factors is equally 3+5+5+6+5+8+3+7=42. Wilansky was so amazed by his discovery that he named this kind of numbers after his brother-in-law: Smith numbers. 
As this observation is also true for every prime number, Wilansky decided later that a (simple and unsophisticated) prime number is not worth being a Smith number, so he excluded them from the definition. 
Wilansky published an article about Smith numbers in the Two Year College Mathematics Journal and was able to present a whole collection of different Smith numbers: For example, 9985 is a Smith number and so is 6036. However,Wilansky was not able to find a Smith number that was larger than the telephone number of his brother-in-law. It is your task to find Smith numbers that are larger than 4937775!
Input

The input file consists of a sequence of positive integers, one integer per line. Each integer will have at most 8 digits. The input is terminated by a line containing the number 0.
Output

For every number n > 0 in the input, you are to compute the smallest Smith number which is larger than n,and print it on a line by itself. You can assume that such a number exists.
Sample Input

4937774
0
Sample Output

4937775
Source

Mid-Central European Regional Contest 2000
 */


public class SmithNumbersSolution {

	/* boolean 
	 */
	private ArrayList<Integer> _primeNumbers = new ArrayList<Integer>();
	private int _lastIndex = -1;
	
	/* Constructor
	 * 
	 */
	
	public SmithNumbersSolution() {
		_primeNumbers.add(2);
		_primeNumbers.add(3);
		_lastIndex = 1;
	}
	
	public boolean isSmithNumber(int number) { 
		ArrayList<Integer> factors = decomposePrimeNumbers(number);
		ArrayList<Integer> digits = decomposeDigits(number);
		
		int factorTotal = 0;
		int digitsTotal = 0;
		for (int i : factors) { 
			ArrayList<Integer> subDigits = decomposeDigits(i);
			for (int j : subDigits) {
				factorTotal += j;
			}
		}
		
		for (int i : digits) { 
			digitsTotal += i;
		}
		
		return factorTotal == digitsTotal;
	}
	
	public boolean isPrimeNumber(int number) {
		
		//buildPrimeNumbers(number);
		
		boolean isPrime = true;
//		for (int i = 0; i <= _lastIndex && _primeNumbers.get(i) <= number / 2; i++) {
		for (int i = 0; i< _lastIndex && i <= number / _primeNumbers.get(i); i++) {
			int divider = _primeNumbers.get(i);
			if (number % divider == 0)  {
				isPrime = false;
				break;
			}
		}
		
		return isPrime;
	}
	
	public ArrayList<Integer> decomposePrimeNumbers(int number) {
		
		buildPrimeNumbers(number);
		
		ArrayList<Integer> factors = new ArrayList<Integer>();
		
//		while (!isPrimeNumber(number)) { // there is no need test if the number is a prime.
//			for (int i = 0; number >= 2 && i <= _lastIndex && _primeNumbers.get(i) <= number / 2;) { 
//			for (int i = 0; number >= 2 && i < _lastIndex && i <= number / _primeNumbers.get(i);) { // e.g. 97 / 5 = 19 % 2, then above 5, the range of integer that we need to check if is a factor of number (97) is within (5, 19]), why ? out of >=20, you only get remainder factor of x < 5....
			for (int i = 0; number >= 2 && i < _lastIndex && number >= _primeNumbers.get(i);) {
				int divider = _primeNumbers.get(i);
				if (number % divider == 0) {
					factors.add(divider);
					number /= divider;
				} else 	{
					i++;
				}
			}
//		}
		return factors;
	}
	
	public ArrayList<Integer> decomposeDigits(int number) {
		
		ArrayList<Integer> factors = new ArrayList<Integer>();
		while (number > 0) { 
			int remainder = number % 10;
			if (remainder != 0) {
				factors.add(0, remainder);	
			}
			
			number /= 10;
		}
		
		return factors;
	}
	
	private void buildPrimeNumbers(int number) { 
		while (number / 2 > _primeNumbers.get(_lastIndex)) {
			_primeNumbers.add(nextPrimeNumber());
			_lastIndex++;
		}
	}
	
	private int nextPrimeNumber() {
		int next = _primeNumbers.get(_lastIndex) + 1;
		//buildPrimeNumbers(next);
		while (!isPrimeNumber(next)) {
			next++;
		}

		return next;
	}
	
	public static void main(String[] args) {
		int number = 4937775;
		SmithNumbersSolution solution = new SmithNumbersSolution();
		System.out.println("4937775 is Smith Number " + solution.isSmithNumber(number));
	}
}

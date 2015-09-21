package sets;

import java.util.HashMap;

import static java.util.Arrays.*;
import static java.lang.System.*; 

/* this is a leet code problem and you can find the leet code from 
 * 	https://leetocde.com/problems/valid-anagram/
 */
public class ValidAnagramSolution2 {
	public static void main(String[] args) { 
		String s = "anagram";
		String t = "nagaram";
		
		
		ValidAnagramSolution2 solution = new ValidAnagramSolution2();
		out.println("t isAnagram of t == " + solution.isAnagram(s, t));
	}
	
	public boolean isAnagram(String s, String t) { 
		char[] sChars = s.toCharArray();
		char[] tChars = t.toCharArray();
		
		// by default the value will be initialized to -, no need to do initialization again
		int[] charArray = new int[256];
		
		for (char ch : sChars) {
			charArray[ch]++;
		}
		
		for (char ch : tChars) {
			if (charArray[ch] == 0) 
				return false;
			charArray[ch]--;
		}
		
		return true;
	}
}

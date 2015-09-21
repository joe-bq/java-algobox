package sets;

import java.util.Arrays;
import java.util.HashMap;

import static java.util.Arrays.*;
import static java.lang.System.*; 

/* this is a leet code problem and you can find the leet code from 
 * 	https://leetocde.com/problems/valid-anagram/
 */
public class ValidAnagramSolution3 {
	public static void main(String[] args) { 
		String s = "anagram";
		String t = "nagaram";
		
		
		ValidAnagramSolution3 solution = new ValidAnagramSolution3();
		out.println("t isAnagram of t == " + solution.isAnagram(s, t));
	}
	
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) return false;
		
		char[] sArrays = s.toCharArray();
		char[] tArrays = t.toCharArray();
		
		Arrays.sort(sArrays);
		Arrays.sort(tArrays);;
		
		for (int i = 0; i < sArrays.length; i++) { 
			if (sArrays[i] != tArrays[i]) return false;
		}
		
		return true;
	}
}

package sets;
import java.util.HashMap;
import static java.util.Arrays.*;
import static java.lang.System.*; 

/* this is a leet code problem and you can find the leet code from 
 * 	https://leetocde.com/problems/valid-anagram/
 */
public class ValidAnagram {
	public static void main(String[] args) { 
		String s = "anagram";
		String t = "nagaram";
		
		
		ValidAnagram solution = new ValidAnagram();
		out.println("t isAnagram of t == " + solution.isAnagram(s, t));
	}
	
	
	public boolean isAnagram(String s, String t) { 
		char[] sChars = s.toCharArray();
		char[] tChars = t.toCharArray();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		
		for (char sChar : sChars) { 
			if (map.containsKey(sChar)) { 
				map.put(sChar, map.get(sChar)+1);
			} else {
				map.put(sChar, 1);
			}
		}
		for (char tChar : tChars) {
			if (map.containsKey(tChar)) {
				map.put(tChar, map.get(tChar)-1);
				if (map.get(tChar) == 0) {
					map.remove(tChar);
				}
			} else  {
				return false;
			}
		}
		if (!map.isEmpty()) {
			return false;
		}
		
		return true;
	}
}

package map;
import java.util.*;


/*
"abcdc", "accbd" => true
"ab", "a" => false
*/
public class StringSimilar {

	public static void main(String[] args) {
		String a = "abcdc";
		String b = "accdb";
		
		StringSimilar solution = new StringSimilar();
		if (solution.isSimilar(a, b)) { 
			System.out.println(a + "==" + b);
		}
		
		a = "ab";
		b = "a";
		if (!solution.isSimilar(a, b)) { 
			System.out.println(a + "!=" + b);
		}
		
	}
	
	private boolean isSimilar(String a, String b) { 
		char[] aChars = a.toCharArray();
		char[] bChars = b.toCharArray();
		
		HashMap<Character, Integer> aMap = new HashMap<Character, Integer>();
		
		for (char aChar : aChars) { 
			if (aMap.containsKey(aChar)) {
				aMap.put(aChar, aMap.get(aChar) + 1);
			} else {
				aMap.put(aChar, 1);
			}
		}
		
		for (char bChar : bChars) { 
			if (aMap.containsKey(bChar)) { 
				aMap.put(bChar, aMap.get(bChar) - 1);
				if (aMap.get(bChar) == 0) {
					aMap.remove(bChar);
				}
			} else { 
				return false;
			}
		}
		
		if (aMap.size() > 0) { 
			return false;
		}
		
		return true;
	}
	
	// the naive solution
	private boolean isSimilar1(String a, String b) { 
		char[] aChars = a.toCharArray();
		char[] bChars = b.toCharArray();
		
		HashMap<Character, Integer> aMap = new HashMap<Character, Integer>();
		HashMap<Character, Integer> bMap = new HashMap<Character, Integer>();
		
		for (char aChar : aChars) { 
			if (aMap.containsKey(aChar)) {
				aMap.put(aChar, aMap.get(aChar) + 1);
			} else {
				aMap.put(aChar, 1);
			}
		}
		
		for (char bChar : bChars) { 
			if (bMap.containsKey(bChar)) {
				bMap.put(bChar, bMap.get(bChar) + 1);
			} else {
				bMap.put(bChar, 1);
			}
		}
		
		if (aMap.size() == bMap.size()) { 
			for (char aKey : aMap.keySet()) { 
				if (!bMap.containsKey(aKey)) {
					return false;
				}
				
				if (bMap.get(aKey) != aMap.get(aKey)) { 
					return false;
				}
			}
		} else { 
			return false;
		}
		
		return true;
	}
	
	// a slightly improved version
	private boolean isSimilar2(String a, String b) { 
		char[] aChars = a.toCharArray();
		char[] bChars = b.toCharArray();
		
		HashMap<Character, Integer> aMap = new HashMap<Character, Integer>();
		
		for (char aChar : aChars) { 
			if (aMap.containsKey(aChar)) {
				aMap.put(aChar, aMap.get(aChar) + 1);
			} else {
				aMap.put(aChar, 1);
			}
		}
		
		for (char bChar : bChars) {
			if (aMap.containsKey(bChar)) {
				aMap.put(bChar, aMap.get(bChar) - 1);
				if (aMap.get(bChar) < 0) { 
					return false;
				}
			} else { 
				return false;
			}
		}
		
		for (char aKey : aMap.keySet()) { 
			if (aMap.get(aKey) != 0) { 
				return false;
			}
		}
				
		return true;
	}
}

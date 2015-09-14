package breakword;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
    	Set<String> roots = new HashSet<String>();
    	for (String word : dict) { 
    		if (s.contains(word)) {
    			roots.add(word);
    		}
    	}
    	
    	return wordBreakFiltered(s, roots);
    }
    
    public boolean wordBreakFiltered(String s, Set<String> roots) {
    	if ("".equals(s)) return true;
    	for (String word : roots) { 
    		if (s.startsWith(word)) { 
    			if (wordBreakFiltered(s.substring(word.length()), roots)) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public static void main(String[] args) { 
    	Solution solution = new Solution();
    	System.out.println("can leetcode be breaken up with [leet, code] " + solution.wordBreak("leetcode", new HashSet<String>(Arrays.asList("leet", "code"))));
    }
}
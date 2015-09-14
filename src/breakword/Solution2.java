package breakword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Solution2 {
	
	/* Private fields */
	private List<String> _breadcrumb = new ArrayList<String>();
	private List<String> _sentences = new ArrayList<String>();
	
	public List<String> wordBreak(String s, Set<String> dict) {
		Set<String> roots = new HashSet<String>();
		for (String word : dict) { 
			roots.add(word);
		}
		
		return wordBreakFiltered(s, roots);
	}
	
	public List<String> wordBreakFiltered(String s, Set<String> roots) {
		if ("".equals(s)) _sentences.add(trail());
		
		for (String word : roots) {
			if (s.startsWith(word)) {
				_breadcrumb.add(word);
				wordBreakFiltered(s.substring(word.length()), roots);
				_breadcrumb.remove(_breadcrumb.size() - 1);
			}
		}
		
		return _sentences;
		
	}
	
	/* Privates methods */
	private String trail() {
		StringBuilder builder = new StringBuilder();
		Iterator<String> iter = _breadcrumb.iterator();
		while (iter.hasNext()) { 
			builder.append(iter.next());
			if (iter.hasNext()) {
				builder.append(" ");
			}
		}
		
		return builder.toString();
	}
	
	public static void main(String[] args) {
		Solution2 solution = new Solution2();
		List<String> result = solution.wordBreak("catsanddog", new HashSet<String>(Arrays.asList("cat", "cats", "and", "sand", "dog")));
		for (String word : result) { 
			System.out.println(word);
		}
	}
}

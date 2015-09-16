package sets;

import java.util.*;


/* this is a reference implementation from 
 * 		https://leetcode.com/discuss/41329/standard-dfs-java-solution
 */
public class SubsetsSolution2WithRecursion {
	public static void main(String[] args) {
		int[] nums = {1, 1, 2, 2, 3, 3};
		SubsetsSolution2WithRecursion solution = new SubsetsSolution2WithRecursion();
		List<List<Integer>> result = solution.subsetsWithDup(nums);
		
		for (List<Integer> list : result) {
			
			System.out.print("[");;
			for (int i : list) { 
				System.out.print(i);
			}
			System.out.print("]");
		}

	}
	
	public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result= new ArrayList<>();
        dfs(nums,0,new ArrayList<Integer>(),result);
        return result;
    }

    public void dfs(int[] nums,int index,List<Integer> path,List<List<Integer>> result){
        result.add(path);
        for(int i=index;i<nums.length;i++){
        	// the key is how to avoid duplicates subset
        	// one way to generate duplicate is as such (given that you have 3 of 1s in sequence (1, 1, 1, 2, ...)
        	// what you want to generate is 
        	//  1,
        	//  1, 1
        	//  1, 1, 1
        	// but if you don't manage your code well, you will be generating 
        	//  1, _, 1
        	// which duplicates with 
        	//  1, 1, _
        	// the solution here is only the first adjacent dup 1 can append to the path
        	// this is done by this condition
        	//   if (n > index && num[i] == num[i-1]) continue
        	// 
            if(i>index&&nums[i]==nums[i-1]) continue;
            List<Integer> nPath= new ArrayList<>(path);
            nPath.add(nums[i]);
            dfs(nums,i+1,nPath,result);
        }
    }
}

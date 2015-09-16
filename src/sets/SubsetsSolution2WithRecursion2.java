package sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class SubsetsSolution2WithRecursion2 {
	public static void main(String[] args) {
		int[] nums = {1, 1, 2, 2, 3, 3};
		SubsetsSolution2WithRecursion2 solution = new SubsetsSolution2WithRecursion2();
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
        subsetsWithDupCore(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }
	
	// instead of 
	//    public List<List<Integer>> subsetsWithDupCore(int[] nums, int index)
	// move the return value into the parameter
	// 
	public void subsetsWithDupCore(int[] nums, int index, List<Integer> path, List<List<Integer>> result) { 
		if (index >= nums.length) return;
		
		int count = 0;
		for (int i = index; i < nums.length && nums[i] == nums[index]; i++, count++) {
		}
		
		for (int i = 0; i <= count; i++) {
			ArrayList<Integer> newPath = new ArrayList<Integer>(path);
			for (int j = 0; j < i; j++) { 
				newPath.add(nums[index]);
			}
			
			subsetsWithDupCore(nums, index + count, newPath, result);
			result.add(newPath);
		}
	}
}

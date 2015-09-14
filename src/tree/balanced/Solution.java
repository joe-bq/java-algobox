package tree.balanced;

/*

 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }

public class Solution {
    public boolean isBalanced(TreeNode root) {
        
    }
}
*/


public class Solution {
	
	class TreeNode { 
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int x) { val = x; } 
	}
	
	public boolean isBalanced(TreeNode root) {
		if (root == null) return true;
		
		if (isBalanced(root.left) && isBalanced(root.right)) 
		{
			int left = height(root.left);
			int right = height(root.right);
			if ((int)Math.abs(left - right) <= 1) {
				return true; 
			}
		}
		
		return false;
	}
	
	public int height(TreeNode root) {
		if (root == null) return 0;
		
		int left = height(root.left);
		int right = height(root.right);
		return 1 + (left > right ? left : right);
	}
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		TreeNode root = solution.new TreeNode(0);
		TreeNode p = solution.new TreeNode(1);
		TreeNode q = solution.new TreeNode(2);
		root.left = p;
		root.right = q;
		p = solution.new TreeNode(3);
		root.left.left = p;
		q = solution.new TreeNode(4);
		root.left.right = q;
		root.right.left = solution.new TreeNode(5);
		root.left.left.left = solution.new TreeNode(6);
		
		System.out.println("the tree is a balanced tree: " + solution.isBalanced(root));
		
	}
}

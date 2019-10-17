/*
Binary Tree Maximum Path Sum

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting 
node to any node in the tree along the parent-child connections. The path must 
contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

https://leetcode.com/problems/binary-tree-maximum-path-sum/
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  long gmax = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    maxSum(root);
    return (int) gmax;
  }
  
  int maxSum(TreeNode root) {
    if (root == null) {
      return Integer.MIN_VALUE;
    }
    long lsum = maxSum(root.left);
    long rsum = maxSum(root.right);
    long lmax = Math.max(root.val, lsum + root.val);
    long rmax = Math.max(root.val, rsum + root.val);
    long max = Math.max(lmax, rmax);
    gmax = Math.max(gmax, max);
    gmax = Math.max(gmax, lsum + root.val + rsum);
    return (int) max;
  }
}

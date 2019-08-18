/*
Convert Sorted Array to Binary Search Tree

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of 
the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
 
 https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
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
  public TreeNode sortedArrayToBST(int[] nums) {
    return rec(nums, 0, nums.length - 1);
  }
  
  TreeNode rec(int[] nums, int left, int right) {
    if (left > right) {
      return null;
    }
    int mid = (left + right) / 2;
    int val = nums[mid];
    TreeNode root = new TreeNode(val);
    root.left = rec(nums, left, mid - 1);
    root.right = rec(nums, mid + 1, right);
    return root;
  } 
}

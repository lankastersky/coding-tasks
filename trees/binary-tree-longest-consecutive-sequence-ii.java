/*
Binary Tree Longest Consecutive Sequence II

Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] 
are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be
in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:

Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
 

Example 2:

Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 
Note: All the values of tree nodes are in the range of [-1e7, 1e7].

https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/
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
  
  class Result {
    int incr;
    int decr;
    Result(int i, int d) {
      incr = i;
      decr = d;
    }
  }
  
  int max = 0;
  
  public int longestConsecutive(TreeNode root) {
    rec(root);
    return max;
  }
  
  Result rec(TreeNode root) {
    if (root == null) {
      return new Result(0,0);
    }
    int incr = 1;
    int decr = 1;
    if (root.left != null) { 
      Result lr = rec(root.left);
      if (root.left.val + 1 == root.val) {
        decr = lr.decr + 1;
      } else if (root.left.val - 1 == root.val) {
        incr = lr.incr + 1;
      }
    }
    if (root.right != null) {
      Result rr = rec(root.right);
      if (root.right.val + 1 == root.val) {
        decr = Math.max(decr, rr.decr + 1);
      } else if (root.right.val - 1 == root.val) {
        incr = Math.max(incr, rr.incr + 1);
      }
    }
    max = Math.max(max, incr - 1 + decr);
    return new Result(incr, decr);
  }
}

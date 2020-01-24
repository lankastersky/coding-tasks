/*
Binary Tree Longest Consecutive Sequence

Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in
the tree along the parent-child connections. The longest consecutive path need to 
be from parent to child (cannot be the reverse).

Example 1:
Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3
Explanation: Longest consecutive sequence path is 3-4-5, so return 3.

Example 2:
Input:

   2
    \
     3
    / 
   2    
  / 
 1

Output: 2 
Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.

https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
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
  // Top-to-bottom
//   int ans = 1;
//   public int longestConsecutive(TreeNode root) {
//     if (root == null) {
//       return 0;
//     }
//     dfs(root, null, 0);
//     return ans;
//   }
  
//   void dfs(TreeNode root, Integer val, int length) {
//     if (root == null) {
//       return;
//     }
//     if (val != null && root.val == val + 1) {
//       length++;
//       ans = Math.max(ans, length);
//     } else {
//       length = 1;
//     }
//     dfs(root.left, root.val, length);
//     dfs(root.right, root.val, length);
//   }
  
  // Bottom-to-top
  int ans = 0;
  public int longestConsecutive(TreeNode root) {
    dfs(root);
    return ans;
  }
  
  // public int dfs(TreeNode root) {
  //   if (root == null) {
  //     return 0;
  //   }
  //   int L = dfs(root.left) + 1;
  //   int R = dfs(root.right) + 1;
  //   if (root.left != null && root.val + 1 != root.left.val) {
  //     L = 1;
  //   }
  //   if (root.right != null && root.val + 1 != root.right.val) {
  //     R = 1;
  //   }
  //   ans = Math.max(ans, Math.max(L, R));
  //   return Math.max(L, R);
  // }

  public int dfs(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int L = dfs(root.left);
    int R = dfs(root.right);
    if (root.left != null && root.val + 1 == root.left.val) {
      L++;
    } else {
      L = 1;
    }
    if (root.right != null && root.val + 1 == root.right.val) {
      R++;
    } else {
      R = 1;
    }
    ans = Math.max(ans, Math.max(L, R));
    return Math.max(L, R);
  }
}

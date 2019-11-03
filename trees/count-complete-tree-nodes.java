/*
Count Complete Tree Nodes

Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled,
and all nodes in the last level are as far left as possible. It can have between 1 and 
2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6

https://leetcode.com/problems/count-complete-tree-nodes/
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
  // Based on https://leetcode.com/articles/count-complete-tree-nodes/
  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int h = getHeight(root, true);
    int total = (1 << h) - 1;
    int lastLevel = 1 << h - 1;
    int left = 0;
    int right = lastLevel - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (exists(root, mid, h)) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return (total - lastLevel) + left;
  }

  boolean exists(TreeNode root, int pos, int h) {
    int left = 0;
    int lastLevel = 1 << (h - 1);
    int right = lastLevel - 1;
    while (h > 1) {
      int mid = (right + left) / 2;
      if (pos > mid) {
        root = root.right;
        left = mid + 1;
      } else {
        root = root.left;
        right = mid;
      }
      h--;
    }
    return root != null;
  }
  
  // O(log^2(n)) Based on https://leetcode.com/problems/count-complete-tree-nodes/discuss/61948/Accepted-Easy-Understand-Java-Solution
//   public int countNodes(TreeNode root) {
//     return dfs(root);
//   }
  
//   int dfs(TreeNode root) {
//     if (root == null) {
//       return 0;
//     }
//     int left = getHeight(root.left, true);
//     int right = getHeight(root.right, false);
//     if (left == right) {
//       return (1 << (left + 1)) - 1;
//     }
//     return dfs(root.left) + dfs(root.right) + 1;
//   }
  
   int getHeight(TreeNode root, boolean left) {
    int res = 0;
    while (root != null) {
      res++;
      root = left ? root.left : root.right;
    }
    return res;
  }
}

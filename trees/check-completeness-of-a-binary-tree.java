/*
Check Completeness of a Binary Tree

Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely 
filled, and all nodes in the last level are as far left as possible. It can have
between 1 and 2h nodes inclusive at the last level h.

Example 1:
Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} 
and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:
Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.
 
Note:
The tree will have between 1 and 100 nodes.

https://leetcode.com/problems/check-completeness-of-a-binary-tree/
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
  // Based on https://leetcode.com/problems/check-completeness-of-a-binary-tree/discuss/205682/JavaC%2B%2BPython-BFS-Level-Order-Traversal
  public boolean isCompleteTree(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (queue.peek() != null) {
      TreeNode cur = queue.remove();
      queue.add(cur.left);
      queue.add(cur.right);
    }
    while (!queue.isEmpty() && queue.peek() == null) {
      queue.remove();
    }
    return (queue.isEmpty());
  }
  
  // Using dfs
//   Integer depth;
//   boolean lastLevelFull;
//   public boolean isCompleteTree(TreeNode root) {
//     return dfs(root, 0);
//   }
  
//   boolean dfs(TreeNode root, int d) {
//     if (root == null) {
//       if (depth == null) {
//         depth = d;
//       } else if ( d + 1 == depth) {
//         lastLevelFull = true;
//       }
//       return d == (lastLevelFull ? depth - 1 : depth);
//     }
//     if (!dfs(root.left, d + 1)) {
//       return false;
//     }
//     return dfs(root.right, d + 1);
//   }
}

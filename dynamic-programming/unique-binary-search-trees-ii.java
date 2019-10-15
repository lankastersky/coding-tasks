/*
Unique Binary Search Trees II

Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

https://leetcode.com/problems/unique-binary-search-trees-ii/
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
  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return new ArrayList<>();
    }
    return gen(1, n);
  }
  
  List<TreeNode> gen(int m, int n) {
    List<TreeNode> res = new ArrayList<>();
    if (m > n) {
      res.add(null);
      return res;
    }
    for (int i = m; i <= n; i++) {
      List<TreeNode> lefts = gen(m, i - 1);
      List<TreeNode> rights = gen(i + 1, n);
      for (TreeNode left : lefts) {
        for (TreeNode right : rights) {
          TreeNode root = new TreeNode(i);
          root.left = left;
          root.right = right;
          res.add(root);
        }
      }
    }
    return res;
  }
}

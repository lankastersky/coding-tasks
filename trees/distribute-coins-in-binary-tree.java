/*
Distribute Coins in Binary Tree

Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another. 
(The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.

Input :      3
           /   \
          0     0 
Output : 2
Explanation: From the root of the tree, we move one 
coin to its left child, and one coin to
its right child.

Input :      0
           /   \
          3     0  
Output :3
Explanation : From the left child of the root, we move 
two coins to the root [taking two moves]. Then, we move 
one coin from the root of the tree to the right child.

Note:

1<= N <= 100
0 <= node.val <= N

https://leetcode.com/problems/distribute-coins-in-binary-tree/
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
  int res = 0;
  public int distributeCoins(TreeNode root) {
    rec(root);
    return res;
  }

  int rec(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int l = rec(root.left);
    int r = rec(root.right);
    res += Math.abs(l) + Math.abs(r);
    return root.val + l + r - 1;
  }
}

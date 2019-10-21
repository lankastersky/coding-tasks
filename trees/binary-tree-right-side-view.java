/*
Binary Tree Right Side View

Given a binary tree, imagine yourself standing on the right side of it, return the values
of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
  
https://leetcode.com/problems/binary-tree-right-side-view/
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
  
  public List<Integer> rightSideView(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<>();
    dfs(root, res, 0);
    return res;
  }
  
  void dfs(TreeNode root, ArrayList<Integer> res, int depth) {
    if (root == null) {
      return;
    }    
    if (depth == res.size()) {
      res.add(root.val);
    }
    dfs(root.right, res, depth + 1);
    dfs(root.left, res, depth + 1);
  }
  
  // O(n^2) space
  // public List<Integer> rightSideView(TreeNode root) {
  //   ArrayList<Integer> res = new ArrayList<>();
  //   if (root == null) {
  //     return res;
  //   }
  //   ArrayList<ArrayList<Integer>> layers = new ArrayList<>();
  //   Queue<TreeNode> queue = new LinkedList<>();
  //   queue.add(root);
  //   while (!queue.isEmpty()) {
  //     ArrayList<Integer> layer = new ArrayList<>();
  //     layers.add(layer);
  //     int n = queue.size();
  //     for (int i = 0; i < n; i++) {
  //       TreeNode node = queue.remove();
  //       layer.add(node.val);
  //       if (node.left != null) {
  //         queue.add(node.left);
  //       }
  //       if (node.right != null) {
  //         queue.add(node.right);
  //       }
  //     }
  //   }
  //   for (ArrayList<Integer> layer : layers) {
  //     res.add(layer.get(layer.size() - 1));
  //   }
  //   return res;
  // }
}

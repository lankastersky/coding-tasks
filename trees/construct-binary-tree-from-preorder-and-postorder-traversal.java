/*
Construct Binary Tree from Preorder and Postorder Traversal

Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 
Note:

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.

https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
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
  
  // Based on https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/discuss/161268/C%2B%2BJavaPython-One-Pass-Real-O(N)
  // int preInd = 0;
  // int postInd = 0;
  // public TreeNode constructFromPrePost(int[] pre, int[] post) {
  //   TreeNode node = new TreeNode(pre[preInd++]);
  //   if (node.val != post[postInd]) {
  //     node.left = constructFromPrePost(pre, post); // fill out left subtree until node.val == post[postInd]
  //   }
  //   if (node.val != post[postInd]) {
  //     node.right = constructFromPrePost(pre, post);
  //   }
  //   postInd++;
  //   return node;
  // }
  
  // Divide and conquer.
  Map<Integer, Integer> postToInd = new HashMap<>();
  public TreeNode constructFromPrePost(int[] pre, int[] post) {
    int n = pre.length;
    for (int i = 0; i < n; i++) {
      postToInd.put(post[i], i);
    }
    return dfs(pre, post, 0, n - 1, 0, n - 1); 
  }
  
  TreeNode dfs(int[] pre, int[] post, int preLeft, int preRight, int postLeft, int postRight) {
    if (preLeft > preRight || postLeft > postRight) {
      return null;
    }
    TreeNode root = new TreeNode(pre[preLeft]);
    if (preLeft == preRight) {
      return root;
    }
    int ind = postToInd.get(pre[preLeft + 1]); // find the index of left child in post
    int delta = ind - postLeft; // values in (postLeft, ind) are in left subtree. The rest goes to right
    root.left = dfs(pre, post, preLeft + 1, preLeft + 1 + delta, postLeft, postLeft + delta);
    root.right = dfs(pre, post, preLeft + 1 + delta + 1, preRight, postLeft + delta + 1, postRight);
    return root;
  }
}

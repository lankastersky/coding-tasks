/*
Delete Nodes And Return Forest

Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

Example 1:

Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
 
Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.

https://leetcode.com/problems/delete-nodes-and-return-forest/
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
  public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
    ArrayList<TreeNode> roots = new ArrayList<>();
    if (root == null || to_delete.length == 0) {
      return roots;
    }
    Set<Integer> del = new HashSet<>();
    for (int val : to_delete) {
      del.add(val);
    }
    dfs(root, del, roots);
    if (!del.contains(root.val)) {
      roots.add(root);
    }
    return roots;
  }
  
  TreeNode dfs(TreeNode root, Set<Integer> del, ArrayList<TreeNode> roots) {
    if (root == null) {
      return null;
    }
    root.left = dfs(root.left, del, roots);
    root.right = dfs(root.right, del, roots);
    if (del.contains(root.val)) {
      if (root.left != null) {
        roots.add(root.left);
      }
      if (root.right != null) {
        roots.add(root.right);
      }
      return null;
    }
    return root;
  }

  // Too complicated
//   public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
//     ArrayList<TreeNode> roots = new ArrayList<>();
//     if (root == null || to_delete.length == 0) {
//       return roots;
//     }
//     dfs(root, to_delete, roots);
//     boolean remroot = false;
//     for (int val : to_delete) {
//       if (root.val == val) {
//         if (root.left != null) {
//           roots.add(root.left);
//         }
//         if (root.right != null) {
//           roots.add(root.right);
//         }
//         remroot = true;
//         break;
//       }
//     }
//     if (!remroot) {
//       roots.add(root);
//     }
//     return roots;
//   }
  
//   void dfs(TreeNode root, int[] del, ArrayList<TreeNode> roots) {
//     if (root == null) {
//       return;
//     }
//     dfs(root.left, del, roots);
//     dfs(root.right, del, roots);
//     for (int val : del) {
//       if (root.left != null && root.left.val == val) {
//         if (root.left.left != null) {
//           roots.add(root.left.left);
//         }
//         if (root.left.right != null) {
//           roots.add(root.left.right);
//         }
//         root.left = null;
//       }
//       if (root.right != null && root.right.val == val) {
//         if (root.right.left != null) {
//           roots.add(root.right.left);
//         }
//         if (root.right.right != null) {
//           roots.add(root.right.right);
//         }
//         root.right = null;
//       }
//     }
//   }
}

/*
Delete Nodes And Return Forest

Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

Example 1:
https://assets.leetcode.com/uploads/2019/07/01/screen-shot-2019-07-01-at-53836-pm.png

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
        if (root == null) {
            return new ArrayList<>();
        }
        List<TreeNode> res = new ArrayList<>();
        HashSet<Integer> del = new HashSet<>();
        for (int i : to_delete) {
            del.add(i);
        }
        if (!rec(root, del, res)) {
            res.add(root);
        }
        return res;
    }

    boolean rec(TreeNode root, HashSet<Integer> del, List<TreeNode> res) {
        if (root == null) {
            return false;
        }
        if (rec(root.left, del, res)) {
            root.left = null;
        }
        if (rec(root.right, del, res)) {
            root.right = null;
        }
        if (toDelete(root, del)) {
            if (root.left != null) {
                res.add(root.left);
            }
            if (root.right != null) {
                res.add(root.right);
            }
            return true;
        }
        return false;
    }
    
    boolean toDelete(TreeNode root, HashSet<Integer> del) {
        if (del.contains(root.val)) {
            return true;
        }
        return false;
    }
}

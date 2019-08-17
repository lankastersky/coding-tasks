/*
Smallest Subtree with all the Deepest Nodes

Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.

Example 1:

https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/01/sketch1.png

Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation:

We return the node with value 2, colored in yellow in the diagram.
The nodes colored in blue are the deepest nodes of the tree.
The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
Both the input and output have TreeNode type.

https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
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
 
// O(n) time, O(n^2) space. See O(n) space at 
// https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/solution/
class Solution {
    ArrayList<ArrayList<TreeNode>> lists = new ArrayList<>();
    int maxLevel = -1;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        traverse(root, 0, new ArrayList<>());
        return merge(lists);
    }
    
    TreeNode merge(ArrayList<ArrayList<TreeNode>> lists) {
        if (lists.size() == 1) {
            return lists.get(0).get(lists.get(0).size() - 1);
        }
        int i = 0;
        int n = lists.get(0).size();
        while (i < n) {
            TreeNode cur = lists.get(0).get(i);
            for (ArrayList<TreeNode> list : lists) {
                if (cur != list.get(i)) {
                    return list.get(i - 1);
                }
            }
            i++;
        }
        return null;
    }
    
    void traverse(TreeNode root, int level, ArrayList<TreeNode> path) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            path.add(root);
            if (level > maxLevel) {
                maxLevel = level;
                lists.clear();
                lists.add(new ArrayList<>(path));
            } else if (level == maxLevel) {
                lists.add(new ArrayList<>(path));
            }
            path.remove(path.size() - 1);
            return;
        }
        path.add(root);
        if (root.left != null) {
            traverse(root.left, level + 1, path);
        }
        if (root.right != null) {
            traverse(root.right, level + 1, path);
        }
        path.remove(path.size() - 1);
    }
}

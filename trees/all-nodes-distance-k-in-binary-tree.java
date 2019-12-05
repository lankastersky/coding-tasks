/*
All Nodes Distance K in Binary Tree

We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  
The answer can be returned in any order.

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
Output: [7,4,1]
Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.

Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
 
Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.

https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
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
  // Based on https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143798/1ms-beat-100-simple-Java-dfs-with(without)-hashmap-including-explanation
  // See another approach with graph: https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143752/JAVA-Graph-%2B-BFS
  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
    // node to depth
    Map<TreeNode, Integer> map = new HashMap<>();
    findNode(root, target, map);
    ArrayList<Integer> res = new ArrayList<>();
    dfs(root, target, K, map.get(root), map, res);
    return res;
  }
  
  int findNode(TreeNode root, TreeNode t, Map<TreeNode, Integer> map) {
    if (root == null) {
      return -1;
    }
    if (root == t) {
      map.put(t, 0);
      return 0;
    }
    int l = findNode(root.left, t, map);
    if (l >= 0) {
      map.put(root, l + 1);
      return l + 1;
    }
    int r = findNode(root.right, t, map);
    if (r >= 0) {
      map.put(root, r + 1);
      return r + 1;
    }
    return -1;
  }
  
  void dfs(TreeNode root, TreeNode t, int K, int length,
             Map<TreeNode, Integer> map, ArrayList<Integer> res) {
    
    if (root == null) {
      return;
    }
    if (map.containsKey(root)) {
      length = map.get(root);
    }
    if (length == K) {
      res.add(root.val);
    }
    dfs(root.left, t, K, length + 1, map, res);
    dfs(root.right, t, K, length + 1, map, res);
  }
}

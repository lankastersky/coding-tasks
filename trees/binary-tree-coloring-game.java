/*
Binary Tree Coloring Game

Two players play a turn based game on a binary tree.  We are given the root of this binary tree, 
and the number of nodes n in the tree.  n is odd, and each node has a distinct value from 1 to n.

Initially, the first player names a value x with 1 <= x <= n, and the second player names a value
y with 1 <= y <= n and y != x.  The first player colors the node with value x red, and the second 
player colors the node with value y blue.

Then, the players take turns starting with the first player.  In each turn, that player chooses a
node of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the 
chosen node (either the left child, right child, or parent of the chosen node.)

If (and only if) a player cannot choose such a node in this way, they must pass their turn.  If both
players pass their turn, the game ends, and the winner is the player that colored more nodes.

You are the second player.  If it is possible to choose such a y to ensure you win the game, return 
true.  If it is not possible, return false.

Example 1:
Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
Output: true
Explanation: The second player can choose the node with value 2.
 
Constraints:

root is the root of a binary tree with n nodes and distinct node values from 1 to n.
n is odd.
1 <= x <= n <= 100

https://leetcode.com/problems/binary-tree-coloring-game
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
  // Based on https://leetcode.com/problems/binary-tree-coloring-game/discuss/350570/JavaC%2B%2BPython-Simple-recursion-and-Follow-Up
  int left;
  int right;
  public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
    dfs(root, x);
    return Math.max(Math.max(left, right), n - left - right - 1) > n / 2;
  }
  
  int dfs(TreeNode root, int x) {
    if (root == null) {
      return 0;
    }
    int l = dfs(root.left, x);
    int r = dfs(root.right, x);
    if (root.val == x) {
      left = l;
      right = r;
    }
    return l + 1 + r;
  }
  
  // Too complicated
//   int up;
//   int left;
//   int right;
//   public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
//     dfs(root, x, true, false, false);
//     if ((up > left + right + 1)
//         || (left > up + right + 1)
//         || (right > up + left + 1)) {
//       return true;      
//     }
//     return false;
//   }
  
//   void dfs(TreeNode root, int x, boolean addup, boolean addleft, boolean addright) {
//     if (root == null) {
//       return;
//     }
//     if (root.val == x) {
//       dfs(root.left, x, false, true, false);
//       dfs(root.right, x, false, false, true);
//     } else {
//       dfs(root.left, x, addup, addleft, addright);
//       dfs(root.right, x, addup, addleft, addright);
//       if (addup) {
//         up++;
//       } else if (addleft) {
//         left++;
//       } else if (addright) {
//         right++;
//       }
//     }
//   }
}

/*
Binary Search Tree Iterator

Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Example:

BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
 
Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number
in the BST when next() is called.

https://leetcode.com/problems/binary-search-tree-iterator/
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
class BSTIterator {
  TreeNode root;
  TreeNode cur;
  Stack<TreeNode> stack;
  
  public BSTIterator(TreeNode root) {
    stack = new Stack<>();
    pushLefts(root);
  }
  
  public int next() {
    TreeNode cur = stack.pop();
    if (cur.right != null) {
      pushLefts(cur.right);
    }
    return cur.val;
  }
  
  public boolean hasNext() {
    return !stack.isEmpty();
  }
  
  void pushLefts(TreeNode root) {
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
  }
  
  // Too complicated
  // public BSTIterator(TreeNode root) {
  //   this.root = root;
  //   cur = root;
  //   stack = new Stack<>();
  //   while (cur != null && cur.left != null) {
  //     stack.push(cur);
  //     cur = cur.left;
  //   }
  // }

//   /** @return the next smallest number */
//   public int next() {
//     if (cur == null) {
//       return 0;
//     }
//     int res = cur.val;
//     if (cur.right != null) {
//       stack.push(cur);
//       cur = cur.right;
//       while (cur.left != null) {
//         stack.push(cur);
//         cur = cur.left;
//       }
//     } else {
//       if (stack.isEmpty()) {
//         cur = null;
//       } else {
//         TreeNode parent = stack.pop();
//         if (parent.right == cur) {
//           int curval = cur.val;
//           while (!stack.isEmpty() && parent.val < curval) {
//             cur = parent;
//             parent = stack.pop();
//           }
//           if (parent.val < cur.val) {
//             cur = null;          
//           } else {
//             cur = parent;
//           }
//         } else {
//           cur = parent;
//         }        
//       }
//     }
//     return res;
//   }

//   /** @return whether we have a next smallest number */
//   public boolean hasNext() {
//     return cur != null;
//   }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

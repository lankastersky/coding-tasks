/*
Convert Binary Search Tree to Sorted Doubly Linked List

Convert a BST to a sorted circular doubly-linked list in-place. Think of the left
and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:
 
https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png
 
We want to transform this BST into a circular doubly linked list. Each node in a doubly linked 
list has a predecessor and successor. For a circular doubly linked list, the predecessor of the
first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol means
the node it points to is the smallest element of the linked list.

https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png
 
Specifically, we want to do the transformation in place. After the transformation, the left pointer
of the tree node should point to its predecessor, and the right pointer should point to its successor.
We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship, while
the dashed line means the predecessor relationship.
https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
  Node prev = null;
  public Node treeToDoublyList(Node root) {
    if (root == null) {
      return null;
    }
    dfs(root);
    return circle(root);
  }
  
  void dfs(Node root) {
    if (root == null) {
      return;
    }
    dfs(root.left);
    if (prev != null) {
      prev.right = root;
    }
    root.left = prev;
    prev = root;
    dfs(root.right);
  }
  
  Node circle(Node root) {
    Node beg = root;
    while (beg.left != null) {
      beg = beg.left;
    }
    Node end = root;
    while (end.right != null) {
      end = end.right;
    }
    beg.left = end;
    end.right = beg;
    return beg;
  }
}

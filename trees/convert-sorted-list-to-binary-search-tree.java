/*
Convert Sorted List to Binary Search Tree

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two 
subtrees of every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
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
  ListNode head;
  public TreeNode sortedListToBST(ListNode head) {
    int s = size(head);
    this.head = head;
    return rec(0, s - 1);
  }
  
  TreeNode rec(int left, int right) {
    if (left > right) {
      return null;
    }
    int mid = (int) Math.ceil((double) (left + right) / 2);
    TreeNode leftChild = rec(left, mid - 1);
    TreeNode root = new TreeNode(head.val);
    root.left = leftChild;
    head = head.next;
    root.right = rec(mid + 1, right);
    return root;
  }
  
  int size(ListNode head) {
    int s = 0;
    while (head != null) {
      s++;
      head = head.next;
    }
    return s;
  }
}

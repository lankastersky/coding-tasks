/*
Flatten a Multilevel Doubly Linked List

You are given a doubly linked list which in addition to the next and previous pointers,
it could have a child pointer, which may or may not point to a separate doubly linked 
list. These child lists may have one or more children of their own, and so on, to produce
a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list.
You are given the head of the first level of the list.

Example:

Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL
 
Explanation for the above example:

Given the following multilevel doubly linked list:
 https://assets.leetcode.com/uploads/2018/10/12/multilevellinkedlist.png
 
We should return the following flattened doubly linked list:
https://assets.leetcode.com/uploads/2018/10/12/multilevellinkedlistflattened.png

https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
  Node tail;
  public Node flatten(Node head) {
    Node dummy = new Node();
    tail = dummy;
    dfs(head);
    if (dummy.next != null) {
      dummy.next.prev = null;      
    }
    return dummy.next;
  }
  
  void dfs(Node cur) {
    if (cur == null) {
      return;
    }
    tail.next = cur;
    cur.prev = tail;
    tail = cur;
    Node next = cur.next;
    dfs(cur.child);
    cur.child = null;
    dfs(next);
  }
}

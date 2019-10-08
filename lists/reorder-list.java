/*
Reorder List

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:
Given 1->2->3->4, reorder it to 1->4->2->3.

Example 2:
Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

https://leetcode.com/problems/reorder-list/
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  public void reorderList(ListNode head) {
    if (head == null) {
      return;
    }
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    ListNode temp = slow.next;
    slow.next = null;
    ListNode second = reverse(temp);
    merge(head, second);
  }
  
  ListNode reverse(ListNode head) {
    ListNode cur = head;
    ListNode prev = null;
    while (cur != null) {
      ListNode temp = cur.next;
      cur.next = prev;
      prev = cur;
      cur = temp;
    }
    return prev;
  }
  
  ListNode merge(ListNode first, ListNode second) {
    boolean f = true;
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    while (first != null && second != null) {
      if (f) {
        cur.next = first;
        first = first.next;
      } else {
        cur.next = second;
        second = second.next;
      }
      cur = cur.next;
      f = !f;
    }
    while (first != null) {
      cur.next = first;
      first = first.next;
      cur = cur.next;
    }
    while (second != null) {
      cur.next = second;
      second = second.next;
      cur = cur.next;
    }
    return dummy.next;    
  }
}

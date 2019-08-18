/*

Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() 
and Java's indexOf().

https://leetcode.com/problems/implement-strstr/
*/

class Solution {
  int BASE = 256;  
  public int strStr(String haystack, String needle) {
    int n = haystack.length();
    int m = needle.length();
    if (needle == null || needle.isEmpty()) {
      return 0;
    }
    if (n < m) {
      return -1;
    }
    int nhash = 0;
    int hhash = 0;
    for (int i = 0; i < m; i++) {
      nhash = nhash * BASE + needle.charAt(i);
      hhash = hhash * BASE + haystack.charAt(i);
    }
    int pow = (int) Math.pow(BASE, m - 1);
    for (int i = 0; i < n - m; i++) {
      if (nhash == hhash) {
        if (comp(haystack, needle, i)) {
          return i;
        }
      }
      hhash = (hhash % pow) * BASE + haystack.charAt(i + m);
    }

    if (nhash == hhash) {
      if (comp(haystack, needle, n - m)) {
        return n - m;
      }
    }
    return -1;
  }
  
  boolean comp(String h, String n, int start) {
    for (int i = 0; i < n.length(); i++) {
      if (h.charAt(i + start) != n.charAt(i)) {
        return false;
      }
    }
    return true;
  }
}

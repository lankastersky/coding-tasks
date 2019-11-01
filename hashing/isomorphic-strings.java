/*
Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving
the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:
Input: s = "egg", t = "add"
Output: true

Example 2:
Input: s = "foo", t = "bar"
Output: false

Example 3:
Input: s = "paper", t = "title"
Output: true

Note:
You may assume both s and t have the same length.

https://leetcode.com/problems/isomorphic-strings/
*/

class Solution {
  int MAX = 256;
  public boolean isIsomorphic(String s, String t) {
    int[] dicS = new int[MAX];
    int[] dicT = new int[MAX];
    int n = s.length();
    if (n != t.length()) {
      return false;
    }
    for (int i = 0; i < n; i++) {
      char a = s.charAt(i);
      char b = t.charAt(i);
      if (dicS[a] != dicT[b]) {
        return false;
      }
      dicS[a] = i + 1;
      dicT[b] = i + 1;
    }
    return true;
  }
}

/*
K-Similar Strings

Strings A and B are K-similar (for some non-negative integer K) if we can swap
the positions of two letters in A exactly K times so that the resulting string equals B.

Given two anagrams A and B, return the smallest K for which A and B are K-similar.

Example 1:
Input: A = "ab", B = "ba"
Output: 1

Example 2:
Input: A = "abc", B = "bca"
Output: 2

Example 3:
Input: A = "abac", B = "baca"
Output: 2

Example 4:
Input: A = "aabc", B = "abca"
Output: 2

Note:
1 <= A.length == B.length <= 20
A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}

https://leetcode.com/problems/k-similar-strings/
*/

class Solution {
  public int kSimilarity(String A, String B) {
    Map<String, Integer> memo = new HashMap<>();
    return rec(A, B, 0, memo);
  }
  
  int rec(String A, String B, int i, Map<String, Integer> memo) {
    int n = A.length();
    if (i == n) {
      return 0;
    }
    if (memo.containsKey(B)) {
      return memo.get(B);
    }
    char a = A.charAt(i);
    char b = B.charAt(i);
    if (a == b) {
      return rec(A, B, i + 1, memo);
    }
    int res = Integer.MAX_VALUE;
    for (int j = i + 1; j < n; j++) {
      b = B.charAt(j);
      if (a == b) {
        res = Math.min(res, rec(A, swap(B, i, j), i + 1, memo));
      }
    }
    if (res != Integer.MAX_VALUE) {
      res++;
    }
    memo.put(B, res);
    return res;
  }
  
  String swap(String s, int i, int j) {
    char[] a = s.toCharArray();
    char t = a[i];
    a[i] = a[j];
    a[j] = t;
    return new String(a);
  }
}

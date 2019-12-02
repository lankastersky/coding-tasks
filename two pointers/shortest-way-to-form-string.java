/*
Shortest Way to Form String

From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation 
equals target. If the task is impossible, return -1.

Example 1:

Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".

Example 2:
Input: source = "abc", target = "acdbc"
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" 
in target string.

Example 3:
Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".

Constraints:

Both the source and target strings consist of only lowercase English letters from "a"-"z".
The lengths of source and target string are between 1 and 1000.

https://leetcode.com/problems/shortest-way-to-form-string/
*/

import java.util.*;

class Solution {
  int res = Integer.MAX_VALUE;
  public int shortestWay(String source, String target) {
    Set<Character> set = new HashSet<>();
    for (char c : source.toCharArray()) {
      set.add(c);
    }
    for (char c : target.toCharArray()) {
      if (!set.contains(c)) {
        return -1;
      }
    }
    dfs(source, target, 0, 0, 1);
    return res;
  }
  
  void dfs(String source, String target, int sind, int tind, int num) {
    int sl = source.length();
    int tl = target.length();
    if (tind == tl) {
      res = Math.min(res, num);
      return;
    }
    if (sind == sl) {
      num++;
      sind = 0;
    }
    char sc = source.charAt(sind);
    char tc = target.charAt(tind);
    if (sc == tc) {
      dfs(source, target, sind + 1, tind + 1, num);
    } else {
      dfs(source, target, sind + 1, tind, num);
      //dfs(source, target, sind, tind + 1, num);
    }
  }
}

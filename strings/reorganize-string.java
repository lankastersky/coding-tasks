/*
Reorganize String

Given a string S, check if the letters can be rearranged so that two characters that are 
adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"

Example 2:
Input: S = "aaab"
Output: ""

Note:
S will consist of lowercase letters and have length in range [1, 500].

https://leetcode.com/problems/reorganize-string/
*/

  class Solution {
    int A = 26;
    public String reorganizeString(String S) {
      int n = S.length();
      if (n == 0) {
        return "";
      }
      int[] cnt = new int[A];
      for (char c : S.toCharArray()) {
        cnt[c - 'a']++;
      }
      int max = 0;
      for (int a: cnt) {
        max = Math.max(max, a);
      }
      if (max > (n + 1) / 2) {
        return "";
      }
      PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
        if (a[1] != b[1]) {
          return -Integer.compare(a[1], b[1]);
        }
        return Integer.compare(a[0], b[0]);
      });
      for (int i = 0; i < A; i++) {
        if (cnt[i] == 0) {
          continue;
        }
        queue.add(new int[] {i, cnt[i]});
      }
      StringBuilder sb = new StringBuilder();
      while (!queue.isEmpty()) {
        int[] a = queue.remove();
        sb.append((char) (a[0] + 'a'));
        a[1]--;
        if (!queue.isEmpty()) {
          int[] b = queue.remove();
          sb.append((char) (b[0] + 'a'));
          b[1]--;
          if (b[1] > 0) {
            queue.add(b);
          }
        }
        if (a[1] > 0) {
          queue.add(a);
        }
      }
      return sb.toString();
    }
  }

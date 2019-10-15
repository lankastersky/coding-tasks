/*
Longest Valid Parentheses

Given a string containing just the characters '(' and ')', find the length of
the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"

Example 2:
Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"

https://leetcode.com/problems/longest-valid-parentheses/
*/

class Solution {
  // Based on https://leetcode.com/problems/longest-valid-parentheses/discuss/14278/Two-Java-solutions-with-explanation.-Stack-and-DP.-Short-and-easy-to-understand.
  public int longestValidParentheses(String s) {
    int opens = 0;
    int n = s.length();
    int[] dp = new int[n];
    int res = 0;
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == '(') {
        opens++;
      } else {
        if (opens > 0) {
          dp[i] = dp[i - 1] + 2;
          dp[i] += (i - dp[i] >= 0) ? dp[i - dp[i]] : 0;
          res = Math.max(res, dp[i]);
          opens--;
        }
      }
    }
    return res;
  }
}

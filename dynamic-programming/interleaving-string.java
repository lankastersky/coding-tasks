/*
Interleaving String

Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true

Example 2:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false

https://leetcode.com/problems/interleaving-string/
*/

class Solution {
  public boolean isInterleave(String s1, String s2, String s3) {
    int m = s1.length();
    int n = s2.length();
    int p = s3.length();
    if (m + n != p) {
      return false;
    }
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    for (int i = 0; i < m; i++) {
      dp[i + 1][0] = (dp[i][0] && s1.charAt(i) == s3.charAt(i));
    }
    for (int j = 0; j < n; j++) {
      dp[0][j + 1] = (dp[0][j] && s2.charAt(j) == s3.charAt(j));
    }
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
          dp[i][j] = true;        
        }
        if (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
          dp[i][j] = true;        
        }
      }
    }
    return dp[m][n];
  }
}

/*
Maximal Square

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4

https://leetcode.com/problems/maximal-square/
*/

class Solution {
  public int maximalSquare(char[][] matrix) {
    int n = matrix.length;
    if (n == 0) {
      return 0;
    }
    int m = matrix[0].length;
    if (m == 0) {
      return 0;
    }
    int[][] dp = new int[n][m];
    for (int i = 0; i < n; i++) {
      dp[i][0] = matrix[i][0] - '0';
    }
    for (int j = 0; j < m; j++) {
      dp[0][j] = matrix[0][j] - '0';
    }
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if (matrix[i][j] == '0') {
          dp[i][j] = 0;
          continue;
        }
        dp[i][j] = Math.min(
          Math.min(dp[i - 1][j], dp[i][j - 1]),
          dp[i - 1][j - 1]
        ) + 1;
      }
    }
    int res = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        res = Math.max(res, dp[i][j]);
      }
    }
    return res * res;
  }
}

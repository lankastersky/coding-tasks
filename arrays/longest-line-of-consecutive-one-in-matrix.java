/*
Longest Line of Consecutive One in Matrix

Given a 01 matrix M, find the longest line of consecutive one in the matrix. 
The line could be horizontal, vertical, diagonal or anti-diagonal.

Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3

Hint: The number of elements in the given matrix will not exceed 10,000.

https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/
*/

class Solution {
  public int longestLine(int[][] M) {
    int m = M.length;
    if (m == 0) {
      return 0;
    }
    int n = M[0].length;
    int res = 0;
    
    // diagonals
    int[] diags = new int[m + n - 1];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int k = (i - j + m + n - 1) % (m + n - 1);
        diags[k] = M[i][j] == 1 ? diags[k] + 1 : 0;
        res = Math.max(res, diags[k]);
      }
    }
    
    // antidiagonals
    int[] adiags = new int[m + n - 1];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        adiags[i + j] = M[i][j] == 1 ? adiags[i + j] + 1 : 0;
        res = Math.max(res, adiags[i + j]);
      }
    }
    
    // horizontals
    for (int i = 0; i < m; i++) {
      int sum = 0;
      for (int j = 0; j < n; j++) {
        sum = M[i][j] == 1 ? sum + 1 : 0;
        res = Math.max(res, sum);
      }
    }

    // verticals
    for (int j = 0; j < n; j++) {
      int sum = 0;
      for (int i = 0; i < m; i++) {
        sum = M[i][j] == 1 ? sum + 1 : 0;
        res = Math.max(res, sum);
      }
    }
    return res;
  }
}

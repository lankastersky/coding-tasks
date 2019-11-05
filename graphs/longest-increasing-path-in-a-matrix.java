/*
Longest Increasing Path in a Matrix

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down.
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:
Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:
Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
*/

class Solution {
  int[][] DIR = new int[][] {
    {-1, 0}, {0, -1}, {1, 0}, {0, 1}
  };
  
  // Based on https://leetcode.com/problems/longest-increasing-path-in-a-matrix/discuss/78336/Graph-theory-Java-solution-O(v2)-no-DFS
  public int longestIncreasingPath(int[][] matrix) {
    int m = matrix.length;
    if (m == 0) {
      return 0;
    }
    int n = matrix[0].length;
    if (n == 0) {
      return 0;
    }
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (isPeak(matrix, i, j, visited)) {
          queue.add(new int[] {i, j});
        }
      }
    }
    int res = 0;
    // Each round remove peak vertices in topological order
    while (!queue.isEmpty()) {
      res++;
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] p = queue.remove();
        visited[p[0]][p[1]] = true;
        for (int[] d : DIR) {
          int r = p[0] + d[0];
          int c = p[1] + d[1];
          if (r < 0 || r >= m || c < 0 || c >= n) {
            continue;
          }
          if (visited[r][c]) {
            continue;
          }
          if (isPeak(matrix, r, c, visited)) {
            if (matrix[r][c] != matrix[p[0]][p[1]]) {
              queue.add(new int[] {r, c});
            }
          }
        }
      }
    }
    return res;
  }
  
  boolean isPeak(int[][] matrix, int i, int j, boolean[][] visited) {
    int m = matrix.length;
    int n = matrix[0].length;
    for (int[] d : DIR) {
      int r = i + d[0];
      int c = j + d[1];
      if (r < 0 || r >= m || c < 0 || c >= n) {
        continue;
      }
      if (visited[r][c]) {
        continue;
      }
      if (matrix[i][j] < matrix[r][c]) {
        return false;
      }
    }
    return true;
  }
  
  // O(n^2*m^2), dp-based
  // public int longestIncreasingPath(int[][] matrix) {
  //   int m = matrix.length;
  //   if (m == 0) {
  //     return 0;
  //   }
  //   int n = matrix[0].length;
  //   if (n == 0) {
  //     return 0;
  //   }
  //   int[][] dp = new int[m][n];
  //   int res = 1;
  //   for (int k = 0; k < n * m; k++) {
  //     boolean stop = true;
  //     for (int i = 0; i < m; i++) {
  //       for (int j = 0; j < n; j++) {
  //         if (k == 0) {
  //           dp[i][j] = 1;
  //           stop = false;
  //         } else {
  //           int path = 0;
  //           for (int d = 0; d < 4; d++) {
  //             int r = i + DIR[d][0];
  //             int c = j + DIR[d][1];
  //             if (r < 0 || r >= m || c < 0 || c >= n) {
  //               continue;
  //             }
  //             if (matrix[i][j] > matrix[r][c]) {
  //               path = Math.max(path, dp[r][c] + 1);
  //             }
  //           }
  //           if (dp[i][j] < path) {
  //             dp[i][j] = path;
  //             res = Math.max(res, path);
  //             stop = false;
  //           }
  //         }
  //       }
  //     }
  //     if (stop) {
  //       break;
  //     }
  //   }
  //   return res;
  // }
}

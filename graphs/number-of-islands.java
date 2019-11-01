/*
Number of Islands
Medium

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally
or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3

https://leetcode.com/problems/number-of-islands/
*/

class Solution {
  int[][] DIR = new int[][] {{-1,0}, {0,-1}, {1,0}, {0,1}};
  public int numIslands(char[][] grid) {
    int m = grid.length;
    if (m == 0) {
      return 0;
    }
    int n = grid[0].length;
    if (n == 0) {
      return 0;
    }
    int ans = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '0' || grid[i][j] == 'v') {
          continue;
        }
        ans++;
        dfs(grid, i, j);
      }
    }
    return ans;
  }
  void dfs(char[][] grid, int i, int j) {
    int m = grid.length;
    int n = grid[0].length;
    if (i < 0 || i >= m || j < 0 || j >= n) {
      return;
    }
    if (grid[i][j] == '0' || grid[i][j] == 'v') {
      return;
    }
    grid[i][j] = 'v';
    for (int[] dir : DIR) {
      dfs(grid, i + dir[0], j + dir[1]);
    }
  }
}

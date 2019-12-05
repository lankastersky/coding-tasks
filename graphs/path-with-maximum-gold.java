/*
Path with Maximum Gold

In a gold mine grid of size m * n, each cell in this mine has an integer representing
the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position you can walk one step to the left, right, up or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.
 
Example 1:
Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.

Example 2:
Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.

Constraints:

1 <= grid.length, grid[i].length <= 15
0 <= grid[i][j] <= 100
There are at most 25 cells containing gold.

https://leetcode.com/problems/path-with-maximum-gold/
*/

class Solution {
  int[][] DIR = new int[][] {    {0, -1},
      {-1, 0}, {1, 0},
      {0, 1}};
  public int getMaximumGold(int[][] grid) {
    int gold = 0;
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        gold = Math.max(gold, dfs(grid, new boolean[m][n], i, j));
      }
    }
    return gold;
  }

  int dfs(int[][] grid, boolean[][] visited, int a, int b) {
    if (visited[a][b]) {
      return 0;
    }
    if (grid[a][b] == 0) {
      return 0;
    }
    visited[a][b] = true;
    int m = grid.length;
    int n = grid[0].length;
    int gold = 0;
    for (int[] dir : DIR) {
      int i = a + dir[0];
      int j = b + dir[1];
      if (i < 0 || i >= m || j < 0 || j >= n) {
        continue;
      }
      int temp = dfs(grid, visited, i, j);
      gold = Math.max(gold, temp);
    }
    visited[a][b] = false;
    return gold + grid[a][b];
  }
}

/*
Most Stones Removed with Same Row or Column

On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5

Example 2:
Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3

Example 3:
Input: stones = [[0,0]]
Output: 0
 
Note:

1 <= stones.length <= 1000
0 <= stones[i][j] < 10000

https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
*/

class Solution {
  int moves = 0;
  // Based on counting edges in the grid graph.
  // Two nodes in the same row or column are connected.
  public int removeStones(int[][] stones) {
    int n = stones.length;
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (visited[i]) {
        continue;
      }
      visited[i] = true;
      dfs(stones, visited, i);
    }
    return moves;
  }
  
  void dfs(int[][] stones, boolean[] visited, int s) {
    int n = stones.length;
    for (int i = 0; i < n; i++) {
      if (visited[i]) {
        continue;
      }
      if (stones[s][0] == stones[i][0] || stones[s][1] == stones[i][1]) {
        visited[i] = true;
        moves++;
        dfs(stones, visited, i);  
      }
    }
  }
}

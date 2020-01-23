/*
Minimum Knight Moves

In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
then one square in an orthogonal direction.

Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]

Example 2:
Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 
Constraints:
|x| + |y| <= 300

https://leetcode.com/problems/minimum-knight-moves/
*/

class Solution {
  // DFS based on https://leetcode.com/problems/minimum-knight-moves/discuss/388995/DFS-with-Memorization-beat-82-and-100
  Can also be solved with BFS without getting TLE.
  int[][] dirs = new int[][] {
    {-1, -2},
    // {1, -2},
    {-2, -1},
    // {2, -1},
    // {-2, 1},
    // {2, 1},
    // {-1, 2},
    // {1, 2},
  };
  
  public int minKnightMoves(int x, int y) {
    Map<String, Integer> map = new HashMap<>();
		map.put("0,0", 0);
		map.put("1,0", 3);
		map.put("1,1", 2);
		map.put("2,0", 2);
    return dfs(x, y, map);
  }
  
  int dfs(int r, int c, Map<String, Integer> memo) {
    r = Math.abs(r);
    c = Math.abs(c);
    String key = r + ","+ c;
    if (memo.containsKey(key)) {
      return memo.get(key);
    }
    int min = SIZE;
    for (int[] dir : dirs) {
      int i = r + dir[0];
      int j = c + dir[1];
      min = Math.min(min, dfs(i, j, memo) + 1);
    }
    memo.put(key, min);
    return min;
  }
}

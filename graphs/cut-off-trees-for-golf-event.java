/*
Cut Off Trees for Golf Event

You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map,
in this map:

0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents
the tree's height.
 

You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with
lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees.
If you can't cut off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

Example 1:

Input: 
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
Output: 6
 
Example 2:

Input: 
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
Output: -1
 
Example 3:

Input: 
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
Output: 6
Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 

Hint: size of the given matrix will not exceed 50x50.

leetcode - amazon online assessment
https://leetcode.com/problems/cut-off-trees-for-golf-event/
*/

import java.util.*;

class Solution {

    class Coords {
      int r;
      int c;
      int h;
      Coords(int r, int c, int h) {
        this.r = r;
        this.c = c;
        this.h = h;
      }
    }

    int[][] dir = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int cutOffTree(List<List<Integer>> forest) {
      SortedMap<Integer, Coords> trees = new TreeMap<>();

      int n = forest.size(); // rows
      int m = forest.get(0).size(); // cols
      int treeCount = 0;
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
          int ground = forest.get(r).get(c);
          if (ground > 1) {
            //treeCount++;
            trees.put(ground, new Coords(r, c, ground));
          }
        }
      }

      Coords prev = new Coords(0, 0, 1);
      int res = 0;
      for (int h : trees.keySet()) {
        Coords cur = trees.get(h);
        int d = dist(prev, cur, forest);
        if (d == -1) {
          return -1;
        }
        res += d;
        prev = cur;
      }
      return res;
    }

    int dist(Coords src, Coords dst, List<List<Integer>> forest) {
      int n = forest.size(); // rows
      int m = forest.get(0).size(); // cols
      Queue<Coords> queue = new LinkedList<>();
      boolean[][] visited = new boolean[n][m];
      int[][] dist = new int[n][m];
      for (int[] row : dist) {
        Arrays.fill(row, Integer.MAX_VALUE);
      }

      dist[src.r][src.c] = 0;
      visited[src.r][src.c] = true;
      queue.add(src);

      while (!queue.isEmpty()) {
        Coords cur = queue.remove();
        if (cur.r == dst.r && cur.c == dst.c) {
          return dist[cur.r][cur.c];
        }
        for (int[] d : dir) {
          int r = cur.r + d[0];
          int c = cur.c + d[1];
          if (r < 0 || r >= n || c < 0 || c >= m) {
            continue;
          }
          if (visited[r][c]) {
            continue;
          }
          visited[r][c] = true;
          int ground = forest.get(r).get(c);
          if (ground == 0) {
            continue;
          }
          dist[r][c] = Math.min(dist[r][c], dist[cur.r][cur.c] + 1);
          queue.add(new Coords(r, c, ground));
        }
      }
      return -1;
    }
  }

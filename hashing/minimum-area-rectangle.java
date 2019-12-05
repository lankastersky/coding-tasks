/*
Minimum Area Rectangle

Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from 
these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.

Example 1:
Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4

Example 2:
Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2
 
Note:

1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.

https://leetcode.com/problems/minimum-area-rectangle/
*/

class Solution {
  public int minAreaRect(int[][] points) {
    int area = Integer.MAX_VALUE;
    Set<String> set = new HashSet<>();
    int n = points.length;
    for (int[] p : points) {
      set.add(key(p[0], p[1]));
    }
    for (int i = 0; i < n; i++) {
      int[] p1 = points[i];
      for (int j = i + 1; j < n; j++) {
        int[] p2 = points[j];
        if (p1[0] == p2[0] || p1[1] == p2[1]) {
          continue;
        }
        if (set.contains(key(p1[0], p2[1])) 
            && set.contains(key(p2[0], p1[1]))) {
          int t = (Math.abs(p2[0] - p1[0]) 
                   * Math.abs(p2[1] - p1[1]));
          area = Math.min(area, t);
        }
      }
    }
    return area == Integer.MAX_VALUE ? 0 : area;
  }
  
  String key(int x, int y) {
    return x + "_" + y;
  }
}

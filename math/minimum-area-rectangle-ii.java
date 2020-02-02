/*
Minimum Area Rectangle II

Given a set of points in the xy-plane, determine the minimum area of any rectangle formed from 
these points, with sides not necessarily parallel to the x and y axes.

If there isn't any rectangle, return 0.

Example 1:
Input: [[1,2],[2,1],[1,0],[0,1]]
Output: 2.00000
Explanation: The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1], with an area of 2.

Example 2:
Input: [[0,1],[2,1],[1,1],[1,0],[2,0]]
Output: 1.00000
Explanation: The minimum area rectangle occurs at [1,0],[1,1],[2,1],[2,0], with an area of 1.

Example 3:
Input: [[0,3],[1,2],[3,1],[1,3],[2,1]]
Output: 0
Explanation: There is no possible rectangle to form from these points.

Example 4:
Input: [[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]
Output: 2.00000
Explanation: The minimum area rectangle occurs at [2,1],[2,3],[3,3],[3,1], with an area of 2.
 
Note:
1 <= points.length <= 50
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
Answers within 10^-5 of the actual value will be accepted as correct.

https://leetcode.com/problems/minimum-area-rectangle-ii/
*/

class Solution {
  
  // O(n^3)
  // public double minAreaFreeRect(int[][] points) {
  //   Set<String> set = new HashSet<>();
  //   for (int[] p : points) {
  //     set.add(key(p));
  //   }
  //   int n = points.length;
  //   double res = 0;
  //   for (int i = 0; i < n; i++) {
  //     int[] p1 = points[i];
  //     for (int j = 0; j < n; j++) {
  //       if (i == j) {
  //         continue;
  //       }
  //       int[] p2 = points[j];
  //       for (int k = 0; k < n; k++) {
  //         if (k == i || k == j) {
  //           continue;
  //         }
  //         int[] p3 = points[k];
  //         // if diags cross in the middle
  //         int x4 = p2[0] + p3[0] - p1[0];
  //         int y4 = p2[1] + p3[1] - p1[1];
  //         int[] p4 = new int[] {x4, y4};
  //         if (!set.contains(key(p4))) {
  //           continue;
  //         }
  //         // if orthogonal
  //         if ((p3[0] - p1[0]) * (p2[0] - p1[0]) 
  //             + (p3[1] - p1[1]) * (p2[1] - p1[1]) == 0) {
  //           double s = Math.sqrt(dist(p1, p2)) * Math.sqrt(dist(p1, p3));
  //           if (res == 0 || res > s) {
  //             res = s;
  //           }
  //         }
  //       }
  //     }
  //   }
  //   return res;
  // }
  
  // Based on https://leetcode.com/problems/minimum-area-rectangle-ii/discuss/208361/JAVA-O(n2)-using-Map
  public double minAreaFreeRect(int[][] points) {
    Map<String, List<int[]>> map = new HashMap<>();
    int n = points.length;
    for (int i = 0; i < n; i++) {
      int[] p1 = points[i];
      for (int j = i + 1; j < n; j++) {
        int[] p2 = points[j];
        int diagLength = dist(p1, p2);
        double diagCenterX = (double) (p1[0] + p2[0]) / 2;
        double diagCenterY = (double) (p1[1] + p2[1]) / 2;
        String key = diagLength + "_" + diagCenterX + "_" + diagCenterY;
        List<int[]> list = map.getOrDefault(key, new ArrayList<>());
        list.add(new int[] {i, j});
        map.put(key, list);
      }
    }
    double res = 0;
    for (String key : map.keySet()) {
      List<int[]> ps = map.get(key);
      for (int i = 0; i < ps.size(); i++) {
        int[] p1 = points[ps.get(i)[0]];
        int[] p3 = points[ps.get(i)[1]];
        for (int j = i + 1; j < ps.size(); j++) {
          int[] p2 = points[ps.get(j)[0]];
          int[] p4 = points[ps.get(j)[1]];
          double a = Math.sqrt(dist(p1, p2));
          double b = Math.sqrt(dist(p1, p4));
          double s = a * b;
          if (res == 0 || res > s) {
            res = s;
          }
        }
      }
    }
    return res;
  }
  
  String key(int[] p) {
    return p[0] + "_" + p[1];
  }
  
  int dist(int[] p1, int[] p2) {
    return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
  }
}

/*
Line formula:
x - x1   y - y1
------ = ------
x2 - x1  y2 - y1

y = kx + b

y - y1 = (x - x1) * (y2 - y1) / (x2 - x1)
y = (x - x1) * (y2 - y1) / (x2 - x1) + y1
k = (y2 - y1) / (x2 - x1)
y = (x - x1)k + y1
y = x k + y1 - x1 k
b = y1 - x1 * k
if (x2 == x1) k = inf

orthogonal vectors:
|a||b| cos = a * b
cos = 1 => a * b = 0
x1 y1 + x2 y2 = 0;

for rectangle:
Diags are crossed in the same point
(x1 + x3) / 2 = (x2 + x4) / 2
*/

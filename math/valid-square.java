/*
Valid Square
Medium

Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True
 

Note:

All the input integers are in the range [-10000, 10000].
A valid square has four equal sides with positive length and four equal angles (90-degree angles).
Input points have no order.

https://leetcode.com/problems/valid-square/
*/

class Solution {
  public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    ArrayList<int[]> points = new ArrayList<>();
    points.add(p1);
    points.add(p2);
    points.add(p3);
    points.add(p4);
    Collections.sort(points, (a1, a2) -> {
      if (a1[0] != a2[0]) {
        return a1[0] - a2[0];
      }
      return a1[1] - a2[1];
    });
    p1 = points.get(0);
    p2 = points.get(1);
    p3 = points.get(2);
    p4 = points.get(3);

    // Too complicated
    // // check if diagonals are crossed in the same point
    // if (p1[0] + p4[0] != p2[0] + p3[0]) {
    //   return false;
    // }
    // if (p1[1] + p4[1] != p2[1] + p3[1]) {
    //   return false;
    // }
    // // check if diagonals are of the same length
    // if (dist(p1, p4) != dist(p2, p3)) {
    //   return false;
    // }
    // // check is some points are the same
    // if (dist(p1, p4) == 0) {
    //   return false;
    // }
    // // dot product of orthogonal vectors (diagonals) is 0
    // if ((p1[0] - p4[0]) * (p2[0] - p3[0]) + (p1[1] - p4[1]) * (p2[1] - p3[1]) != 0) {
    //   return false;
    // }
    
    int d = dist(p1, p2);
    if (d == 0) {
      return false;
    }
    if ((d != dist(p2, p4)) || (d != dist(p1, p3)) || (d != dist(p3, p4))) {
      return false;
    }
    if (dist(p1, p4) != dist(p2, p3)) {
      return false;
    }
    return true;
  }
  
  int dist(int[] p1, int[] p2) {
    return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
  }
}

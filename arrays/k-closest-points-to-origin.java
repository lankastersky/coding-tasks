/*
K Closest Points to Origin

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)

https://leetcode.com/problems/k-closest-points-to-origin/
leetcode - amazon online assessment
*/

class Solution {
    class Point {
        int x;
        int y;
        int d;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
            d = x*x + y*y;
        }
    }
    public int[][] kClosest(int[][] points, int K) {
        if (K >= points.length) {
            return points;
        }
        PriorityQueue<Point> minheap = 
            new PriorityQueue<>(
            points.length, (Point p1, Point p2) -> { 
                return Integer.compare(p1.d, p2.d);
            });
        for (int[] p : points) {
            minheap.add(new Point(p[0], p[1]));            
        }
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            // if (minheap.isEmpty()) {
            //     break;
            // }
            Point p = minheap.poll();
            res[i][0] = p.x;
            res[i][1] = p.y;
        }
        return res;
    }
}

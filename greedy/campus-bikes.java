/*
Campus Bikes

On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. 
Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes and workers, 
we choose the (worker, bike) pair with the shortest Manhattan distance between each other,
and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the 
same shortest Manhattan distance, we choose the pair with the smallest worker index; 
if there are multiple ways to do that, we choose the pair with the smallest bike index). 
We repeat this process until there are no available workers.

The Manhattan distance between two points p1 and p2 is 
Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that 
the i-th worker is assigned to.

Example 1:
Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: [1,0]
Explanation: 
Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. 
So the output is [1, 0].

Example 2:
Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: [0,2,1]
Explanation: 
Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, 
thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
 
Note:

0 <= workers[i][j], bikes[i][j] < 1000
All worker and bike locations are distinct.
1 <= workers.length <= bikes.length <= 1000

https://leetcode.com/problems/campus-bikes/
*/

class Solution {
  class Assign {
    int[] w;
    int[] b;
    int wind;
    int bind;
    Assign(int[] w, int[] b, int wind, int bind) {
      this.w = w;
      this.b = b;
      this.wind = wind;
      this.bind = bind;
    }
  }
  public int[] assignBikes(int[][] workers, int[][] bikes) {
    int wl = workers.length;
    int bl = bikes.length;
    int[] res = new int[wl];
    boolean[] wtaken = new boolean[wl];
    boolean[] btaken = new boolean[bl];
    PriorityQueue<Assign> minheap = new PriorityQueue<>(wl * bl, (a1, a2) -> {
      int d1 = dist(a1.w, a1.b);
      int d2 = dist(a2.w, a2.b);
      if (d1 != d2) {
        return Integer.compare(d1, d2);
      }
      if (a1.wind != a2.wind) {
        return Integer.compare(a1.wind, a2.wind);
      }
      //if (a1.bind != a2.bind) {
        return Integer.compare(a1.bind, a2.bind);
      //}
    });
    for (int i = 0; i < wl; i++) {
      int[] w = workers[i];
      for (int j = 0; j < bl; j++) {
        int[] b = bikes[j];
        minheap.add(new Assign(w, b, i, j));
      }
    }
    while (!minheap.isEmpty()) {
      Assign a = minheap.remove();
      if (wtaken[a.wind] || btaken[a.bind]) {
        continue;
      }
      res[a.wind] = a.bind;
      wtaken[a.wind] = true;
      btaken[a.bind] = true;
    }
    return res;
  }
    
  int dist(int[] w, int[] b) {
    int res = 0;
    res += Math.abs(w[0] - b[0]);
    res += Math.abs(w[1] - b[1]);
    return res;
  }
}

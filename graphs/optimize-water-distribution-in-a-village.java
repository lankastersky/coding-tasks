/*
Optimize Water Distribution in a Village

There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another
well to it. The costs to lay pipes between houses are given by the array pipes, where each
pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a pipe.
Connections are bidirectional.

Find the minimum total cost to supply water to all houses.

Example 1:

https://assets.leetcode.com/uploads/2019/05/22/1359_ex1.png

Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
Explanation: 
The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with
cost 2 so the total cost is 3.

https://leetcode.com/problems/optimize-water-distribution-in-a-village/
*/

class Solution {
  // Based on https://leetcode.com/problems/optimize-water-distribution-in-a-village/discuss/365853/C%2B%2BPythonJava-Hidden-Well-in-House-0
  // Idea: we have a hidden house 0. A well i is just a pipe to this house with cost wells[i];
  // No solve minimum spanning tree problem with Kruskal's algorithm using disjoint-sets.
  public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    UnionFind uf = new UnionFind(n + 1); // including a hidden house
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((left, right) -> {
      return Integer.compare(left[2], right[2]);
    });
    for (int[] pipe : pipes) {
      minHeap.add(pipe);
    }
    for (int i = 0; i < n; i++) {
      minHeap.add(new int[] {0, i + 1, wells[i]});
    }
    int res = 0;
    while (!minHeap.isEmpty()) {
      int[] pipe = minHeap.remove();
      int x = pipe[0];
      int y = pipe[1];
      int px = uf.getParent(x);
      int py = uf.getParent(y);
      if (px == py) {
        continue;
      }
      uf.union(px, py);
      res += pipe[2];
    }
    return res;
  }
  
  class UnionFind {
    Integer[] parent;
    
    UnionFind(int n) {
      parent = new Integer[n];
    }
    
    int getParent(int x) {
      while (parent[x] != null) {
        x = parent[x];
      }
      return x;
    }
    
    void union(int x, int y) {
      int px = getParent(x);
      int py = getParent(y);
      if (px != py) {
        parent[px] = py;
      }
    }
  }
}

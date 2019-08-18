/*
Cheapest Flights Within K Stops

There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is
to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:

https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png

The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:

https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png

The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.

Note:
The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.

https://leetcode.com/problems/cheapest-flights-within-k-stops/
*/

// Based on https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/128776/5-ms-AC-Java-Solution-based-on-Dijkstra's-Algorithm
// O(|E| + |V|log|V|)
class Solution {
  
  class Edge {
    int u;
    int v;
    int w;
    Edge(int u, int v, int w) {
      this.u = u;
      this.v = v;
      this.w = w;
    }
  }
  
  class Node {
    int ind;
    int dist;
    int hops;
    Node(int i, int d, int h) {
      ind = i;
      dist = d;
      hops = h;
    }
  }
  
  class Graph {
    Map<Integer, ArrayList<Edge>> adj;
    int size;
    Graph(int size) {
      this.size = size;
      adj = new HashMap<>(); 
      for (int i = 0; i < size; i++) {
        adj.put(i, new ArrayList<>());
      }
    }
  }
  
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    Graph g = build(n, flights);
    return getDist(g, src, dst, K);
  }
  
  Graph build(int n, int[][] flights) {
    Graph g = new Graph(n);
    for (int[] e : flights) {
      g.adj.get(e[0]).add(new Edge(e[0], e[1], e[2]));
    }
    return g;
  }
  
  int getDist(Graph g, int src, int dst, int K) {
    PriorityQueue<Node> heap = new PriorityQueue<>(
     g.size, (n1, n2) -> Integer.compare(n1.dist, n2.dist));
    
    long[] dist = new long[g.size];
    int[] hops = new int[g.size];
    
    Arrays.fill(dist, Integer.MAX_VALUE);
    Arrays.fill(hops, Integer.MAX_VALUE);
    
    dist[src] = 0;
    hops[src] = 0;
    
    heap.add(new Node(src, 0, 0));
    
    while (!heap.isEmpty()) {
      Node node = heap.poll();
      if (node.ind == dst) {
        return node.dist;
      }
      if (node.hops == K + 1) {
        continue;
      }
      for (Edge e : g.adj.get(node.ind)) {
        long d = node.dist + e.w;
        int h = node.hops + 1;
        if (d < dist[e.v]) {
          dist[e.v] = d;
          heap.add(new Node(e.v, (int) d, h));
        } else if (h < hops[e.v]) {
          hops[e.v] = h;
          heap.add(new Node(e.v, (int) d, h));
        }
      }
    }
    if (dist[dst] != Integer.MAX_VALUE) {
      return (int) dist[dst];
    }
    return -1;
  }
}

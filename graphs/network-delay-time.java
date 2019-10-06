/*
Network Delay Time

There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), 
where u is the source node, v is the target node, and w is the time it takes
for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes 
to receive the signal? If it is impossible, return -1.

Example 1:

Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2

Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.

https://leetcode.com/problems/network-delay-time/
*/

class Solution {
  
  // Based on Bellman-Ford
  public int networkDelayTime(int[][] times, int N, int K) {
    long[] dist = new long[N];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[K - 1] = 0;
    for (int k = 0; k < N; k++) {
      for (int[] edge : times) {
        int i = edge[0] - 1;
        int j = edge[1] - 1;
        int w = edge[2];
        if (dist[j] > dist[i] + w) {
          dist[j] = dist[i] + w;
        }
      }
    }
    int res = 0;
    for (int i = 0; i < N; i++) {
      res = Math.max(res, (int) dist[i]);
    }
    return res == Integer.MAX_VALUE ? -1 : res;
  }
}

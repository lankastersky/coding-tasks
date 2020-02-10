/*
Minimum Cost to Hire K Workers

There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers,
we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other 
workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.

Example 1:
Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.

Example 2:
Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 
 
Note:
1 <= K <= N <= 10000, where N = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
Answers within 10^-5 of the correct answer will be considered correct.

https://leetcode.com/problems/minimum-cost-to-hire-k-workers/
*/

class Solution {
  // Based on https://leetcode.com/articles/minimum-cost-to-hire-k-workers/ (case 2)
  public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
    int n = quality.length;
    int[][] workers = new int[n][2];
    for (int i = 0; i < n; i++) {
      workers[i] = new int[] {quality[i], wage[i]};
    }
    // Sort workers starting with cheapest
    Arrays.sort(workers, (w1, w2) -> {
      return Double.compare((double) w1[1] / w1[0], (double) w2[1] / w2[0]);
    });
    double res = Double.MAX_VALUE;
    // Stores qualities sorted from biggest
    PriorityQueue<Integer> heap = new PriorityQueue();
    int qualities = 0; // stores qualities of the current group
    // All next workers are more expensive and minimum wage expectation will be satisfied
    for (int i = 0; i < n; i++) {
      int[] w = workers[i];
      double factor = (double) w[1] / w[0];
      qualities += w[0];
      heap.add(-w[0]); // negation gives the order from biggest to smallest
      if (heap.size() > K) {
        qualities += heap.remove();
      } 
      if (heap.size() == K) {
        // All workers in the  group are paid in the ratio of the current worker
        res = Math.min(res, qualities * factor);
      }
    }
    return res;    
  }
  
  // Based on https://leetcode.com/articles/minimum-cost-to-hire-k-workers/ (case 1)
  // Gives TLE
//     public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
//         int N = quality.length;
//         double ans = 1e5;

//         for (int captain = 0; captain < N; ++captain) {
//             // Must pay at least wage[captain] / quality[captain] per qual
//             double factor = (double) wage[captain] / quality[captain];
//             double prices[] = new double[N];
//             int t = 0;
//             for (int worker = 0; worker < N; ++worker) {
//                 double price = factor * quality[worker];
//                 if (price < wage[worker]) {
//                   continue;
//                 }
//                 prices[t++] = price;
//             }
//             if (t < K) {
//               continue;
//             }
//             Arrays.sort(prices, 0, t);
//             double cand = 0;
//             for (int i = 0; i < K; ++i)
//                 cand += prices[i];
//             ans = Math.min(ans, cand);
//         }
//         return ans;
//     }
}

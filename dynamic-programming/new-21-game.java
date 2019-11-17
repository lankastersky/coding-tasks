/*
New 21 Game

Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K points.  
During each draw, she gains an integer number of points randomly from the range 
[1, W], where W is an integer.  Each draw is independent and the outcomes have
equal probabilities.

Alice stops drawing numbers when she gets K or more points. 
What is the probability that she has N or less points?

Example 1:

Input: N = 10, K = 1, W = 10
Output: 1.00000
Explanation:  Alice gets a single card, then stops.

Example 2:
Input: N = 6, K = 1, W = 10
Output: 0.60000
Explanation:  Alice gets a single card, then stops.
In 6 out of W = 10 possibilities, she is at or below N = 6 points.

Example 3:
Input: N = 21, K = 17, W = 10
Output: 0.73278

Note:
0 <= K <= N <= 10000
1 <= W <= 10000
Answers will be accepted as correct if they are within 10^-5 of the correct answer.
The judging time limit has been reduced for this question.

https://leetcode.com/problems/new-21-game/
*/

class Solution {
  // Based on https://leetcode.com/problems/new-21-game/discuss/132334/One-Pass-DP-O(N)
  public double new21Game(int N, int K, int W) {
    /*
      The probability to get point K is
      p(K) = p(K-1) / W + p(K-2) / W + p(K-3) / W + ... p(K-W) / W
      let wsum = p(K-1) + p(K-2) + ... + p(K-W)
      p(K) = wsum / W
      dp is storing p(i) for i in [0 ... N]
      - We need to maintain the window by adding the new p(i), 
        and getting rid of the old p(i-w)
      - check i >= W because we would not have negative points before drawing a card
      - check i < K because we cannot continue the game after reaching K
      */
    if (K == 0) {
      return 1.0;
    }
    // all possibilities of positions after alice stops playing are from [K, K+W-1]
    if (N >= K + W) {
      return 1.0;
    }
    double[] dp = new double[N + 1];
    dp[0] = 1;
    double wsum = 1.0;
    double res = 0;
    for (int i = 1; i <= N; i++) {
      dp[i] = wsum / W;
      if (i >= W) {
        wsum -= dp[i - W];
      }
      if (i < K) {
        wsum += dp[i];
      } else {
        res += dp[i];
      }
    }
    return res;
  }
}

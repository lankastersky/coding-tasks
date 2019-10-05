/*
Last Stone Weight II

We have a collection of rocks, each rock has a positive integer weight.

Each turn, we choose any two rocks and smash them together.  Suppose 
the stones have weights x and y with x <= y.  The result of this smash is:

If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of weight
y has new weight y-x.
At the end, there is at most 1 stone left.  Return the smallest possible weight
of this stone (the weight is 0 if there are no stones left.)

Example 1:

Input: [2,7,4,1,8,1]
Output: 1

Explanation: 
We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 
Note:

1 <= stones.length <= 30
1 <= stones[i] <= 100

https://leetcode.com/problems/last-stone-weight-ii/
*/

class Solution {
  /*
    a + b = sum
    a - b = ans
    2a = sum + ans
    2b = sum - ans
    ans = 2a - sum = sum - 2b
  */
  // Based on https://leetcode.com/problems/last-stone-weight-ii/discuss/295167/Java-beat-100-with-nice-explanation
  public int lastStoneWeightII(int[] stones) {
    int n = stones.length;
    if (n == 0) {
      return 0;
    }
    int sum = 0;
    for (int el : stones) {
      sum += el;
    }
    // true if some subset from 1st to i'th has a sum equal to sum j
    boolean[][] dp = new boolean[n + 1][sum / 2 + 1];
    for (int i = 0; i < n; i++) {
      dp[i][0] = true;
    }
    int res = 0;
    for (int i = 1; i <= n; i++) {
      int el = stones[i - 1];
      for (int j = 0; j <= sum / 2; j++) {
        dp[i][j] = dp[i - 1][j];
        if (j - el >= 0) {
          dp[i][j] |= dp[i - 1][j - el];
        }
        if (dp[i][j]) {
          res = Math.max(res, j);
        }
      }
    }
    return sum - 2 * res;
  }
  
  // Working solution
//   public int lastStoneWeightII(int[] stones) {
//     Map<String, Integer> memo = new HashMap<>();
//     return rec(stones, 0, 0, 0, memo);
//   }
  
//   int rec(int[] stones, int ind, int a, int b, Map<String, Integer> memo) {
//     if (ind == stones.length) {
//       return Math.abs(a - b);
//     }
//     String key = String.format("%d_%d_%d", ind, a, b);
//     if (memo.containsKey(key)) {
//       return memo.get(key);
//     }
//     int el = stones[ind];
//     int res = Math.min(
//       rec(stones, ind + 1, a + el, b, memo),
//       rec(stones, ind + 1, a, b + el, memo)
//     );
//     memo.put(key, res);
//     return res;
//   }
}

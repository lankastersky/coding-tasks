/*
Coin Change

You are given coins of different denominations and a total amount of money amount. Write a function to compute
the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by 
any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.

https://leetcode.com/problems/coin-change/
*/

import java.util.*;

class Solution {
 // int res = Integer.MAX_VALUE;
  
  // Bottom-up; accepted. O(Sn)
  // public int coinChange(int[] coins, int amount) {
  //   long[] memo = new long[amount + 1];
  //   Arrays.fill(memo, Integer.MAX_VALUE);
  //   memo[0] = 0;
  //   for (int j = 1; j <= amount; j++) {
  //     for (int i = 0; i < coins.length; i++) {
  //       int a = coins[i];
  //       if (j - a >= 0) {
  //         memo[j] = Math.min(memo[j], memo[j - a] + 1);
  //       }
  //     }
  //   }
  //   int res = (int) memo[amount];
  //   if (res == Integer.MAX_VALUE) {
  //     return -1;
  //   }
  //   return res;
  // }

  public int coinChange(int[] coins, int amount) {
    int[] memo = new int[amount + 1];
    Arrays.fill(memo, -1);
    int res = (int) rec(coins, amount, memo);
    if (res == Integer.MAX_VALUE) {
      return -1;
    }
    return res;
  }
  
  long rec(int[] coins, int amount, int[] memo) {
    if (amount == 0) {
      return 0;
    }
    if (memo[amount] != -1) {
      return memo[amount];
    }
    long res = Integer.MAX_VALUE;
    for (int i = 0; i < coins.length; i++) {
      if (amount - coins[i] >= 0) {
        res = Math.min(res, rec(coins, amount - coins[i], memo) + 1);
      } 
    }
    memo[amount] = (int) res;
    return res;
  }
}

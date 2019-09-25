/*
Guess Number Higher or Lower II

We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win 
the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

https://leetcode.com/problems/guess-number-higher-or-lower-ii/
*/

class Solution {
  public int getMoneyAmount(int n) {
    int[][] dp = new int[n + 1][n + 1];
    return rec(1, n, dp);
  }
  
  int rec(int low, int high, int[][] dp) {
    if (low >= high) {
      return 0;
    }
    if (dp[low][high] != 0) {
      return dp[low][high];
    }
    int res = Integer.MAX_VALUE;
    for (int i = low; i < high; i++) {
      res = Math.min(res, i + 
        Math.max(rec(low, i - 1, dp), rec(i + 1, high, dp)));
    }
    dp[low][high] = res;
    return res;
  }
}

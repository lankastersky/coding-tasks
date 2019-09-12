/*
Knight Dialer

A chess knight can move as indicated in the chess diagram below:

1 2 3
4 5 6
7 8 9
  0
  
This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.
Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing 
N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.

https://leetcode.com/problems/knight-dialer/
*/

class Solution {
  static int POS = 10;
  static int[][] jumps = new int[][] {
    {4, 6}, //0
    {6, 8}, //1
    {7, 9}, //2
    {4, 8}, //3
    {0, 3, 9}, //4
    {},     //5
    {0, 1, 7}, //6
    {2, 6}, //7
    {1, 3}, //8
    {2, 4}  //9
  };
  int MOD = (int) (1e9 + 7);
  
  public int knightDialer(int N) {
    int res = 0;
    int[][] memo = new int[N + 1][POS];
    for (int[] row : memo) {
      Arrays.fill(row, -1);
    }
    for (int number = 0; number < POS; number++) {
      res = (res + jump(N - 1, number, memo)) % MOD;            
    }
    return res;
  }
  
  int jump(int N, int pos, int[][] memo) {
    if (N == 0) {
      return 1;
    }
    if (memo[N][pos] != -1) {
      return memo[N][pos];
    }
    int res = 0;
    for (int number : jumps[pos]) {
      res = (res + jump(N - 1, number, memo)) % MOD;
    }
    memo[N][pos] = res;
    return res;
  }
}

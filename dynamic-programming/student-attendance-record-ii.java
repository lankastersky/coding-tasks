/*
Student Attendance Record II

Given a positive integer n, return the number of all possible attendance records with length n, 
which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than
two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8 
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times. 

Note: The value of n won't exceed 100,000.

https://leetcode.com/problems/student-attendance-record-ii/
*/

class Solution {
  int M = (int) (1e9 + 7);  
/*
Based on https://leetcode.com/problems/student-attendance-record-ii/discuss/101633/Improving-the-runtime-from-O(n)-to-O(log-n)
dp[i][j][k] - in sequence of length i
There can be at most j A's in the entire sequence.
There can be at most k trailing L's.
*/
  public int checkRecord(int n) {
    int[][][] dp = new int[n + 1][2][3];
    dp[0] = new int[][] {{1,1,1},{1,1,1}};
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 3; k++) {
          int val = dp[i - 1][j][2]; // adding P
          if (j > 0) val = (val + dp[i - 1][j - 1][2]) % M; // adding A
          if (k > 0) val = (val + dp[i - 1][j][k - 1]) % M; // adding L
          dp[i][j][k] = val;          
        }
      }
    }
    return dp[n][1][2];
  }
  
/*
Based on https://leetcode.com/problems/student-attendance-record-ii/discuss/101643/Share-my-O(n)-C%2B%2B-DP-solution-with-thinking-process-and-explanation
Ln ended with L
An ended with A
Pn ended with P
LnoA ended with L no A
PnoA ended with P no A
res = ln + an + pn

adding P:
Pn+1 = Pn + Ln + An

adding L:
Ln+1 = Pn + An + Pn-1+ An-1

adding A:
An+1 = PnoAn + LnoAn
PnoAn+1 = PnoAn + LnoAn = An
LnoAn+1 = PnoAn + LnoAPn-1
or 
An+1 = An + An-1 + An-2 + An -3
*/  
  public int checkRecord2(int n) {
    if (n == 1) {
      return 3;
    }
    if (n == 2) {
      return 8;
    }
    int[] L = new int[n];
    int[] A = new int[n];
    int[] P = new int[n];
    L[0] = 1;
    A[0] = 1;
    P[0] = 1;
    
    L[1] = 3;
    A[1] = 2;
    P[1] = 1;
    
    A[2] = 4;
    
    for (int i = 1; i < n; i++) {
      P[i] = ((P[i - 1] + L[i - 1]) % M + A[i - 1]) % M;
      if (i >= 2) {
        L[i] = (((P[i - 1] + A[i - 1]) % M + P[i - 2]) % M + A[i - 2]) % M;
      }
      if (i >= 3) {
        A[i] = ((A[i - 1] + A[i - 2]) % M + A[i - 3]) % M;
      }
    }
    return ((P[n - 1] + A[n - 1]) % M + L[n - 1]) % M;
  }
}


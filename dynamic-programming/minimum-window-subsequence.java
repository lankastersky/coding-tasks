/*
Minimum Window Subsequence

Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". 
If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:

Input: 
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation: 
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.
 

Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].

https://leetcode.com/problems/minimum-window-subsequence/
*/

class Solution {
  public String minWindow(String S, String T) {
    int n = S.length();
    int m = T.length();
    // dp[i][j] stores starting index of subseq for 
    // S[:i] and T[:j]
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
      dp[i][0] = i + 1;
    }
    
    for (int j = 1; j <= m; j++) {
      char t = T.charAt(j - 1);
      for (int i = 1; i <= n; i++) {
        char s = S.charAt(i - 1);
        if (s == t) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    int start = -1;
    int end = n;
    for (int i = 1; i <= n; i++) {
      if (dp[i][m] != 0) {
        if ((start == -1) || (i - dp[i][m] + 1 < (end - start))) {
          start = dp[i][m] - 1;
          end = i;
        }
      }
    }
    if (start == -1) {
      return "";
    }
    return S.substring(start, end);
  }
}

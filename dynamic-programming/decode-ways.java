/*
Decode Ways

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26

Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:
Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:
Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

https://leetcode.com/problems/decode-ways/
*/

class Solution {
  public int numDecodings(String s) {
    int n = s.length();
    if (n == 0) {
      return 0;
    }
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = s.charAt(0) == '0' ? 0 : 1;
    for (int i = 2; i <= n; i++) {
      int first = s.charAt(i - 2) - '0';
      int second = s.charAt(i - 1) - '0';
      if (0 < second) {
        dp[i] += dp[i - 1];
      }
      int num = first * 10 + second;
      if (10 <= num && num <= 26) {
        dp[i] += dp[i - 2];
      }
    }
    return dp[n];
  }
  
  // Based on recursion
//   public int numDecodings(String s) {
//     if (s.length() == 0) {
//       return 0;
//     }
//     if (!checkZeros(s)) {
//       return 0;
//     }
//     return decode(s, 0);
//   }
  
//   String trim(String s) {
//     int i = 0;
//     while (i < s.length() && s.charAt(i) == '0') {
//       i++;
//     }
//     return s.substring(i, s.length());
//   }
  
//   boolean checkZeros(String s) {
//     String s1 = trim(s);
//     if (s1.length() != s.length()) {
//       return false;
//     }
//     int n = s.length();
//     for (int i = 0; i + 1 < n; i++) {
//       int c = s.charAt(i) - '0';
//       int c2 = s.charAt(i + 1) - '0';
//       if (c == 0 && c2 == 0) {
//         return false;
//       }
//       if (c2 == 0 && c * 10 + c2 > 26) {
//         return false;
//       }
//     }
//     return true;
//   }
  
//   int decode(String s, int i) {
//     int n = s.length();
//     if (i >= n) {
//       return 1;
//     }
//     int c = s.charAt(i) - '0';
//     int res = 0;
//     if (c == 0) {
//       return 0;
//     }
//     res += decode(s, i + 1);
//     if (i + 1 < n) {
//       int c2 = s.charAt(i + 1) - '0';
//       int num = c * 10 + c2;
//       if (num <= 26) {
//         res += decode(s, i + 2);
//       }
//     }
//     return res;
//   }
}

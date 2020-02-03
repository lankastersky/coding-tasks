/*
Swap Adjacent in LR String

In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of 
either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with 
"XR". Given the starting string start and the ending string end, return True if and only if 
there exists a sequence of moves to transform one string to the other.

Example:
Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: True
Explanation:
We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX

Note:
1 <= len(start) = len(end) <= 10000.
Both start and end will only consist of characters in {'L', 'R', 'X'}.

https://leetcode.com/problems/swap-adjacent-in-lr-string/
*/

class Solution {
  public boolean canTransform(String start, String end) {
    int A = 26;
    int[] cntS = new int[A];
    int[] cntE = new int[A];
    int n = start.length();
    if (n != end.length()) {
      return false;
    }
    for (int i = 0; i < n; i++) {
      char s = start.charAt(i);
      cntS[s - 'A']++;
      char e = end.charAt(i);
      cntE[e - 'A']++;
    }
    for (int i = 0; i < A; i++) {
      if (cntS[i] != cntE[i]) {
        return false;
      }
    }
    int i = 0;
    int j = 0;
    while (i < n && j < n) {
      while (i < n && start.charAt(i) == 'X') {
        i++;
      }
      while (j < n && end.charAt(j) == 'X') {
        j++;
      }
      if (i == n && j == n) {
        return true;
      }
      if (i == n || j == n) {
        return false;
      }
      if (start.charAt(i) != end.charAt(j)) {
        return false;
      }
      // L can move to the left only, so its position in start >= than in end.
      if (start.charAt(i) == 'L' && i < j) {
        return false;
      }
      // R can move to the right only, so its position in start <= than in end.
      if (start.charAt(i) == 'R' && i > j) {
        return false;
      }
      i++;
      j++;
    }
    return true;
  }
}

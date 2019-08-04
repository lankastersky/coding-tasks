/*
Divide two strings to form palindrome

Given two strings, A and B, of equal length, find whether it is possible to split both strings at
the same index such that merging the first part of A and the second part of B forms a palindrome.
Return the location of the split. Palindrome is a word that reads the same backward as forward.
If the solution can not be found, return -1.
Example:
"abcdefgh" and "dasedcba" return 4, since we can split the strings like "abcd" and "dcba"

https://cs.stackexchange.com/questions/109662/divide-two-strings-to-form-palindrome
*/

  class Solution {

    int solve(String A, String B) {
      int n = A.length();
      int i = 0;
      for (; i < n / 2; i++) {
        int j = n - i - 1;
        if (A.charAt(i) != B.charAt(j)) {
          if (i == 0) {
            return -1;
          }
          String pal1 = A.substring(i, j + 1);
          String pal2 = B.substring(i, j + 1);

          if (isPal(pal1) || isPal(pal2)) {
            return i;
          } else {
            return i;
          }
        }
      }
      if (i == n / 2) {
        return n / 2;
      }
      return -1;
    }

    boolean isPal(String s) {
      int n = s.length();
      for (int i = 0; i < n / 2; i++) {
        if (s.charAt(i) != s.charAt(n - i - 1)) {
          return false;
        }
      }
      return true;
    }
  }

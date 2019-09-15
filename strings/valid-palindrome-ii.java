/*
Valid Palindrome II

Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True

Example 2:
Input: "abca"
Output: True

Explanation: You could delete the character 'c'.

Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

https://leetcode.com/problems/valid-palindrome-ii/
*/

class Solution {
  public boolean validPalindrome(String s) {
    if (isPal(s)) {
      return true;
    }
    int n = s.length();
    for (int i = 0; i < n / 2; i++) {
      if (s.charAt(i) != s.charAt(n - i - 1)) {
        String subL = s.substring(0, i) + s.substring(i + 1);
        String subR = s.substring(0, n - i - 1) + s.substring(n - i);
        return (isPal(subL) || isPal(subR));
      }
    }
    return false;
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

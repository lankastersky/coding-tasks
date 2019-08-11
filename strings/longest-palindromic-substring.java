/*
Longest Palindromic Substring

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"

leetcode - amazon online assessment
https://leetcode.com/problems/longest-palindromic-substring/
*/

// O(nlogn). See O(n) https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int res = 1;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int l;
            int r;
            l = i - 1;
            r = i + 1;
            while (l >= 0 && r < s.length()) {
                if (s.charAt(l) == s.charAt(r)) {
                    if (res < r - l + 1) {
                        res = r - l + 1;
                        start = l;
                    }
                    l--;
                    r++;
                } else {
                    break;
                }
            }
            l = i;
            r = i + 1;
            while (l >= 0 && r < s.length()) {
                if (s.charAt(l) == s.charAt(r)) {
                    if (res < r - l + 1) {
                        res = r - l + 1;
                        start = l;
                    }
                    l--;
                    r++;
                } else {
                    break;
                }
            }
        }
        return s.substring(start, start + res);
    }
}

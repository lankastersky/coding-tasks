/*
Remove K Digits

Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is 
the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

https://leetcode.com/problems/remove-k-digits/
*/

class Solution {
    public String removeKdigits(String num, int k) {
      int n = num.length();
      if (k >= n) {
        return "0";
      }
      StringBuilder sb = new StringBuilder();      
      for (int i = 0; i < k; i++) {
        int j = 0;
        boolean uneq = false;
        while (j < num.length() - 1) {
          if (num.charAt(j) > num.charAt(j + 1)) {
            j++;
            uneq = true;
            break;
          }
          sb.append(num.charAt(j));
          j++;
        }
        if (uneq) {
          sb.append(num.substring(j, num.length()));          
        } else {
          sb.append(num.substring(j, num.length() - 1));          
        }
        num = sb.toString();
        sb.setLength(0);
      }
      int i = 0;
      for (; i < num.length(); i++) {
        if (num.charAt(i) != '0') {
          break;
        }
      }
      if (i < num.length()) {
        num = num.substring(i, num.length());
      } else {
        // all chars are 0
        num = "0";
      }
      if (num.isEmpty()) {
        num = "0";
      }
      
      return num;
    }
}

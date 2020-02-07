/*
Next Closest Time

Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. 
There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34",
"12:9" are all invalid.

Example 1:

Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later. 
It is not 19:33, because this occurs 23 hours and 59 minutes later.
Example 2:

Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the 
returned time is next day's time since it is smaller than the input time numerically.

https://leetcode.com/problems/next-closest-time/
*/

class Solution {
  int[] VAL_NUMS = new int[] {2,9,5,9};
  int VAL_HOURS = 24;
  //int VAL_MINS = 59;
  public String nextClosestTime(String time) {
    int n = 4;
    int[] digits = new int[] { 
      time.charAt(0) - '0',
      time.charAt(1) - '0',
      time.charAt(3) - '0',
      time.charAt(4) - '0',
    };
    int gmin = 0;
    for (int i = 1; i < n; i++) {
      if (digits[gmin] > digits[i]) {
        gmin = i;
      }
    }
    StringBuilder sb = new StringBuilder();
    // Go from the end and try to find a bigger valid digit than current
    for (int i = n - 1; i > 0; i--) {
      int d = digits[i];
      int min = -1;
      // find min digit which bigger than d
      for (int j = 0; j < n; j++) {
        if (digits[j] <= d) {
          continue;
        }
        if (digits[j] > VAL_NUMS[i]) {
          continue;
        }
        if (i == 1) {
          int hours = digits[0] * 10 + digits[j];
          if (hours > VAL_HOURS) {
            continue;
          }
        }
        if (min == -1 || digits[min] > digits[j]) {
          min = j;
        }
      }      
      if (min == -1) {
        continue;
      }
      // Replace the i-th digit with the bigger one and return
      for (int j = 0; j < i; j++) {
        sb.append(digits[j]);
      }
      sb.append(digits[min]);
      for (int j = i + 1; j < n; j++) {
        sb.append(digits[gmin]);
      }
      sb.insert(2, ':');
      return sb.toString();
    }
    // Build time using smallest number
    for (int i = 0; i < n; i++) {
      sb.append(digits[gmin]);
    }
    sb.insert(2, ':');
    return sb.toString();
  }
}

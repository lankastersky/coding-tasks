/*
Divide Two Integers

Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: 
[−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division 
result overflows.

https://leetcode.com/problems/divide-two-integers/
*/

class Solution {
  
  public int divide(int dividend, int divisor) {
    int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
    int power = 32;
    long x = Math.abs((long) dividend);
    long y = Math.abs((long) divisor);
    long yPower = y << power;
    if (yPower < 0) {
      yPower = Long.MAX_VALUE;
    }
    long res = 0;
    while (x >= y) {
      while (x < yPower) {
        yPower = yPower >> 1;
        --power;
      }
      res += 1L << power;
      x -= yPower;
    }
    if (sign > 0) {
      if (res > Integer.MAX_VALUE) {
        return Integer.MAX_VALUE;
      }
      return (int) res;
    }
    return (int) -res;
  }
  
  // TLE
  // public int divide(int dividend, int divisor) {
  //   int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
  //   long a = Math.abs((long) dividend);
  //   long b = Math.abs((long) divisor);
  //   long q = 0;
  //   while (a >= b) {
  //     a -= b;
  //     q++;
  //   }
  //   if (sign > 0) {
  //     return (int) q;
  //   }
  //   return (int) -q;
  // }
}

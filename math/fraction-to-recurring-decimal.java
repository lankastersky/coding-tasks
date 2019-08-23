/*
Fraction to Recurring Decimal

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"

https://leetcode.com/problems/fraction-to-recurring-decimal/
*/

  class Solution {
    public String fractionToDecimal(int n, int d) {
//      if (!isRepeating(n, d)) {
      long x = n;
      long y = d;
        if (x % y == 0) {
          return String.valueOf(x / y);
        }
//        double res = (double) n / d;
//        return String.valueOf(res);
//      } else {

        return repeatingDec(x, y);
//      }
    }

//    boolean isRepeating(int n, int d) {
//      // n/d will only have a finite decimal representation if all the prime
//      // factors of d that aren't 2 or 5 are also present in n
//
//      int p = 2;
//      while ((d % p) == 0) {
//        d /= p;
//      }
//      p = 5;
//      while ((d % p) == 0) {
//        d /= p;
//      }
//      return ((n % d) != 0);
//    }

    // Based on https://www.geeksforgeeks.org/find-recurring-sequence-fraction/
    String repeatingDec(long n, long d) {
      boolean sign = false;
      if ((n < 0 && d > 0) || (n > 0 && d < 0)) {
        sign = true;
      }
      if (sign) {
        n = Math.abs(n);
        d = Math.abs(d);
      }

      long dec = n / d;
      n = n % d;
      StringBuilder sb = new StringBuilder();
      if (sign) {
        sb.append("-");
      }
      sb.append(dec);
      sb.append(".");
      int shift = sb.length();
      Map<Long, Integer> rems = new HashMap<>();
      long rem = n % d;
      String res = "";
      while (rem != 0 && !rems.containsKey(rem)) {
        rems.put(rem, res.length());
        rem = rem * 10;
        res += String.valueOf(rem / d);
        sb.append(rem / d);
        rem = rem % d;
      }
      if (rem != 0) {
        sb.insert(rems.get(rem) + shift, "(");
        sb.append(')');
      }
      return sb.toString();
    }

    // See idea at https://softwareengineering.stackexchange.com/questions/192070/what-is-a-efficient-way-to-find-repeating-decimal
    // Fails on 1/6
//    String repeatingDec(int n, int d) {
//      int dec = n / d;
//      n = n - dec;
//      long x = n * 9;
//      long r = x;
//      int p = 1; // length of repeated part
//      // n / d = r / (10^p - 1)
//      // r = n * (10^p - 1) / d
//      while (r % d != 0) {
//        r = r * 10 + x;
//        p++;
//      }
//      r = r / d;
//      int rlength = (int) (Math.log10(r) + 1);
//      StringBuilder sb = new StringBuilder();
//      for (int i = 0; i < p - rlength; i++) {
//        sb.append('0');
//      }
//      sb.append(r);
//      String res = String.format("%d.(%s)", dec, sb);
//      return res;
//    }
  }

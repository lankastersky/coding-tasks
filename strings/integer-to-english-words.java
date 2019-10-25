/*
Integer to English Words

Convert a non-negative integer to its english words representation. 
Given input is guaranteed to be less than 231 - 1.

Example 1:
Input: 123
Output: "One Hundred Twenty Three"

Example 2:
Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"

Example 3:
Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Example 4:
Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred 
Sixty Seven Thousand Eight Hundred Ninety One"

https://leetcode.com/problems/integer-to-english-words/
*/

class Solution {
  
  String[] nums = new String[] {
    "Zero",
    "One",
    "Two",
    "Three",
    "Four",
    "Five",
    "Six",
    "Seven",
    "Eight",
    "Nine",
    "Ten",
    "Eleven",
    "Twelve",
    "Thirteen",
    "Fourteen",
    "Fifteen",
    "Sixteen",
    "Seventeen",
    "Eighteen",
    "Nineteen",
  };
  
  String[] tens = new String[] {
    "Zeroes",
    "Tens",
    "Twenty",
    "Thirty",
    "Forty",
    "Fifty",
    "Sixty",
    "Seventy",
    "Eighty",
    "Ninety"
  };
  
  String[] orders = new String[] {
    "",
    "Thousand",
    "Million",
    "Billion"
  };
  
  public String numberToWords(int num) {
    if (num == 0) {
      return nums[0];
    }
    StringBuilder sb = new StringBuilder();
    int ord = 0;
    Stack<String> stack = new Stack<>();
    while (num > 0) {
      int rem = num % 1000;
      String remStr = parse1000(rem);
      String order = orders[ord++];
      if (!remStr.isEmpty()) {
        stack.add(order);
        stack.add(" ");
        stack.add(remStr);
        stack.add(" ");        
      }
      num /= 1000;
    }
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    return sb.toString().trim();
  }
  
  String parse1000(int n) {
    StringBuilder sb = new StringBuilder();
    if (n / 100 > 0) {
      int hundred = n / 100;
      String hundredStr = nums[hundred];
      sb.append(hundredStr);
      sb.append(" Hundred ");
      n %= 100;
    }
    if (n >= 20) {
      int ten = n / 10;
      String tenStr = tens[ten];
      sb.append(tenStr);
      sb.append(' ');
      n %= 10;
    }
    if (n > 0) {
      String nStr = nums[n];
      sb.append(nStr);
    }
    return sb.toString().trim();
  }
}

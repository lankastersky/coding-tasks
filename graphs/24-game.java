/*
24 Game

You have 4 cards each containing a number from 1 to 9. You need to judge whether they could 
operated through *, /, +, -, (, ) to get the value of 24.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24

Example 2:
Input: [1, 2, 1, 2]
Output: False

Note:
The division operator / represents real division, not integer division. For example,
4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary
operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is
not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we 
cannot write this as 12 + 12.

https://leetcode.com/problems/24-game/
*/

class Solution {
  double eps = 0.01;
  public boolean judgePoint24(int[] nums) {
    ArrayList<Double> a = new ArrayList<>();
    for (int i : nums) {
      a.add((double) i);
    }
    return dfs(a);
  }
  
  boolean dfs(ArrayList<Double> nums) {
    if (nums.size() == 1) {
      return Math.abs(nums.get(0) - 24.) < eps;
    }
    int n = nums.size();
    for (int i = 0; i < n; i++) {
      double op1 = nums.get(i);
      for (int j = i + 1; j < n; j++) {
        double op2 = nums.get(j);
        ArrayList<Double> ops = genOps(op1, op2);
        for (double op : ops) {
          ArrayList<Double> next = new ArrayList<>();
          next.add(op);
          for (int k = 0; k < n; k++) {
            if (k == i || k == j) {
              continue;
            }
            next.add(nums.get(k));
          }
          if (dfs(next)) {
            return true;
          }
          
        }
      }
    }
    return false;
  }
  
  ArrayList<Double> genOps(double op1, double op2) {
    ArrayList<Double> next = new ArrayList<>();
    next.add(op1 + op2);
    next.add(op1 * op2);
    next.add(op1 - op2);
    next.add(op2 - op1);
    next.add(op1 / op2);
    next.add(op2 / op1);
    return next;    
  }
}

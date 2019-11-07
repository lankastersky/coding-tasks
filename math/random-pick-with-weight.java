/*
Random Pick with Weight

Given an array w of positive integers, where w[i] describes the weight of index i, write
a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.

Example 1:
Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]

Example 2:
Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]

Explanation of Input Syntax:
The input is two lists: the subroutines called and their arguments. Solution's constructor 
has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped
with a list, even if there aren't any.

https://leetcode.com/problems/random-pick-with-weight/
*/

class Solution {
  int[] acc;
  int sum;
  Random random;
  public Solution(int[] w) {
    int n = w.length;
    acc = new int[n];
    acc[0] = w[0];
    for (int i = 1; i < n; i++) {
      acc[i] = acc[i - 1] + w[i];
    }
    random = new Random();
  }

  public int pickIndex() {
    int n = acc.length;
    int r = random.nextInt(acc[n - 1]) + 1;
    int res = Arrays.binarySearch(acc, r);
    if (res < 0) {
      res = -res - 1;
    }
    return res;
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

/*
Maximum Sum Circular Subarray

Given a circular array C of integers represented by A, find the maximum possible 
sum of a non-empty subarray of C.

Here, a circular array means the end of the array connects to the beginning of the array.
(Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)

Also, a subarray may only include each element of the fixed buffer A at most once.  
(Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist 
i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)

Example 1:

Input: [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3
Example 2:

Input: [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
Example 3:

Input: [3,-1,2,-1]
Output: 4
Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
Example 4:

Input: [3,-2,2,-3]
Output: 3
Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
Example 5:

Input: [-2,-3,-1]
Output: -1
Explanation: Subarray [-1] has maximum sum -1
 
Note:

-30000 <= A[i] <= 30000
1 <= A.length <= 30000

https://leetcode.com/problems/maximum-sum-circular-subarray/
*/

class Solution {
  public int maxSubarraySumCircular(int[] A) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    int sum = 0;
    int curMin = 0;
    int curMax = 0;
    for (int a : A) {
      curMin = Math.min(a, a + curMin);
      min = Math.min(min, curMin);
      curMax = Math.max(a, a + curMax);
      max = Math.max(max, curMax);
      sum += a;
    }
    if (sum == min) { // all values are negative
      return max;
    }
    return Math.max(max, sum - min);
  }
  
  // Another working solution
  // public int maxSubarraySumCircular(int[] A) {
  //   return Math.max(maxSub(A), maxSubCirc(A));
  // }
  
//   int maxSub(int[] A) {
//     int res = Integer.MIN_VALUE;
//     int sum = 0;
//     for (int a : A) {
//       sum = Math.max(a, a + sum);
//       res = Math.max(res, sum);
//     }
//     return res;
//   }
  
//   int maxSubCirc(int[] A) {
//     int n = A.length;
//     // Maximum subarray sum starts at index ® and ends at or before index i.
//     int[] prefs = new int[n];
// .   // Maximum subarray sum starts at index i + 1 and ends at the last element.
//     int[] sufs = new int[n];
//     int sum = A[0];
//     prefs[0] = sum;
//     for (int i = 1; i < n; i++) {
//       sum += A[i];
//       prefs[i] = Math.max(prefs[i - 1], sum);
//     }
//     sum = 0;
//     for (int i = n - 2; i >= 0; i--) {
//       sum += A[i + 1];
//       sufs[i] = Math.max(sufs[i + 1], sum);
//     }
//     int res = Integer.MIN_VALUE;
//     for (int i = 0; i < n; i++) {
//       res = Math.max(res, prefs[i] + sufs[i]);
//     }
//     return res;
//   }
}

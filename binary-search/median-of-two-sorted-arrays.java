/*
Median of Two Sorted Arrays

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:
nums1 = [1, 3]
nums2 = [2]
The median is 2.0

Example 2:
nums1 = [1, 2]
nums2 = [3, 4]
The median is (2 + 3)/2 = 2.5

https://leetcode.com/problems/median-of-two-sorted-arrays/
*/

class Solution {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    /*
    i = 0..m-1
    j = (m + n - 1) / 2 - i
    m < n to make j > 0
    nums1[i - 1] <= nums2[j]
    nums2[j - 1] <= nums1[i]
    */
    if (nums1.length > nums2.length) {
      int[] t = nums1;
      nums1 = nums2;
      nums2 = t;
    }
    int m = nums1.length;
    int n = nums2.length;
    int low = 0;
    int high = m; // why not m - 1?
    while (low <= high) {
      int i = (low + high) / 2;
      int j = (m + n + 1) / 2 - i;
      if (i > 0 && j < n && nums1[i - 1] > nums2[j]) {
        high = i - 1;
      } else if (j > 0 && i < m && nums2[j - 1] > nums1[i]) {
        low = i + 1;
      } else {
        double left = 0;
        double right = 0;
        if (i > 0 && j > 0) {
          left = Math.max(nums1[i - 1], nums2[j - 1]);            
        } else if (i > 0) {
          left = nums1[i - 1];
        } else {
          left = nums2[j - 1];
        }
        if ((m + n) % 2 == 1) {
          return left;
        }
        
        if (i < m && j < n) {
          right = Math.min(nums1[i], nums2[j]);          
        } else if (i < m) {
          right = nums1[i];
        } else {
          right = nums2[j];
        }
        return (left + right) / 2;        
      }
    }
    return 0;
  }
}

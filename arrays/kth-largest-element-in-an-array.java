/*
Kth Largest Element in an Array

Find the kth largest element in an unsorted array. Note that it is the kth largest
element in the sorted order, not the kth distinct element.

Example 1:
Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.

https://leetcode.com/problems/kth-largest-element-in-an-array/
*/

  class Solution {
    // Based on https://en.wikipedia.org/wiki/Quicksort#Lomuto_partition_scheme
    public int findKthLargest(int[] nums, int k) {
      int n = nums.length;
      return quickSelect(nums, 0, n - 1, n - k);
    }

    int quickSelect(int[] nums, int left, int right, int target) {
      if (left == right) {
        return nums[left];
      }
      int pivot = partition(nums, left, right);
      if (target == pivot) {
        return nums[pivot];
      }
      if (target < pivot) {
        return quickSelect(nums, left, pivot - 1, target);
      } else {
        return quickSelect(nums, pivot + 1, right, target);
      }
    }

    int partition(int[] nums, int lo, int hi) {
      int pivot = nums[hi];
      int i = lo;
      for (int j = lo; j < hi; j++) {
        if (nums[j] <= pivot) {
          swap(nums, i, j);
          i++;
        }
      }
      swap(nums, i, hi);
      return i;
    }
//    int partition(int[] nums, int left, int right) {
//      int i = left;
//      int j = right;
//      int pivotInd = (i + j) / 2;
//      int pivot = nums[pivotInd];
//      while (i <= j) {
//        while (nums[i] < pivot) {
//          i++;
//        }
//        while (pivot < nums[j]) {
//          j--;
//        }
//        if (i < j) {
//          swap(nums, i, j);
//          i++;
//          j--;
//        }
//      }
//      return i;
//    }

    void swap(int[] nums, int i, int j) {
      int t = nums[i];
      nums[i] = nums[j];
      nums[j] = t;
    }
  }

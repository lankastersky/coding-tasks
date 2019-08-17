/*
Intersection of Two Arrays

Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Note:

Each element in the result must be unique.
The result can be in any order.

https://leetcode.com/problems/intersection-of-two-arrays/
*/

import java.util.*;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> first =
            Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> second =
            Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        first.retainAll(second);
        return first.stream().mapToInt(Integer::intValue).toArray();
    }
}

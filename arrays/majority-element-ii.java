/*
Majority Element II

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]

https://leetcode.com/problems/majority-element-ii/
https://www.interviewbit.com/problems/n3-repeat-number/
*/

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
            if (map.size() == 3) {
                Iterator<Map.Entry<Integer, Integer>> iterator =
                    map.entrySet().iterator(); 
                while (iterator.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iterator.next();
                    int val = entry.getValue();
                    val--;
                    if (val == 0) {
                        iterator.remove();
                        length--;
                    } else {
                        entry.setValue(val);
                    }
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int key : map.keySet()) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (key == nums[i]) {
                    count++;
                }
            }
            if (count > nums.length / 3) {
                res.add(key);
            }
        }
        return res;
    }
}

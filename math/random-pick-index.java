/*
Random Pick Index

Given an array of integers with possible duplicates, randomly output the index of a given target number.
You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);

https://leetcode.com/problems/random-pick-index/
*/

class Solution {
  int[] nums;
  Random random;

  public Solution(int[] nums) {
    this.nums = nums;
    random = new Random();
  }

  // Using Reservoir Sampling
  public int pick(int target) {
    int res = 0;
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      if (target == nums[i]) {
        if (random.nextInt(++count) == 0) {
          res = i;
        }
      }
    }
    return res;
  }
  
  // Also a working solution
  // public int pick(int target) {
  //   int count = 0;
  //   for (int i = 0; i < nums.length; i++) {
  //     if (nums[i] == target) {
  //       count++;
  //     }
  //   }
  //   int rand = random.nextInt(count);
  //   count = 0;
  //   for (int i = 0; i < nums.length; i++) {
  //     if (nums[i] == target) {
  //       if (count == rand) {
  //         return i;
  //       }
  //       count++;
  //     }
  //   }
  //   return 0;
  // }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */

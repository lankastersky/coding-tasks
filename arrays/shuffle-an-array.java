/*
Shuffle an Array
Medium

Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

https://leetcode.com/problems/shuffle-an-array/
*/

class Solution {
  int[] nums;
  int[] orig;
  Random rand = new Random();
  public Solution(int[] nums) {
    this.nums = nums;
    this.orig = nums.clone();
  }

  /** Resets the array to its original configuration and return it. */
  public int[] reset() {
    nums = orig.clone();
    return nums;
  }

  /** Returns a random shuffling of the array. */
  public int[] shuffle() {
    for (int i = 0; i < nums.length; i++) {
      //int r = (int) (Math.random() * (i + 1));
      int r = rand.nextInt(i + 1);
      swap(nums, i, r);
    }
    return nums;
  }
  
  static void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

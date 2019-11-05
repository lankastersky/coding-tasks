/*
Count of Smaller Numbers After Self

You are given an integer array nums and you have to return a new counts array. 
The counts array has the property where counts[i] is the number of smaller elements 
to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0]

Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

https://leetcode.com/problems/count-of-smaller-numbers-after-self/
*/

class Solution {
  /*
  Based on merge sort.
  See https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76583/11ms-JAVA-solution-using-merge-sort-with-explanation
  Idea: Sort and then see how each number moved, for example 5
  moved two spots to the right so there were two smaller numbers
  on its right.
  */
  class Pair {
    int val;
    int ind;
    Pair(int v, int i) {
      val = v;
      ind = i;
    }
  }

  public List<Integer> countSmaller(int[] nums) {
    int n = nums.length;
    int[] counts = new int[n];
    Pair[] pairs = new Pair[n];
    Pair[] helper = new Pair[n];
    for (int i = 0; i < n; i++) {
      pairs[i] = new Pair(nums[i], i);
    }

    mergeSort(pairs, 0, n - 1, counts, helper);
    ArrayList<Integer> res = new ArrayList<>();
    for (int i : counts) {
      res.add(i);
    }
    return res;
  }

  void mergeSort(Pair[] nums, int l, int r, int[] counts, Pair[] helper) {
    if (l >= r) {
      return;
    }
    int m = (l + r) / 2;
    mergeSort(nums, l, m , counts, helper);
    mergeSort(nums, m + 1, r, counts, helper);
    merge(nums, l, m, r, counts, helper);
  }

  void merge(Pair[] nums, int l, int m, int r, int[] counts, Pair[] helper) {
    for (int i = l; i <= r; i++) {
      helper[i] = nums[i];
    }
    int leftind = l;
    int rightind = m + 1;
    int ind = l;

    int jumped = 0;

    while (leftind <= m && rightind <= r) {
      if (helper[leftind].val <= helper[rightind].val) {
        nums[ind] = helper[leftind];
        leftind++;

        counts[nums[ind].ind] += jumped;
      } else {
        nums[ind] = helper[rightind];
        rightind++;

        jumped++;
      }
      ind++;
    }
    int rem = m - leftind;
    for (int i = 0; i <= rem; i++) {
      nums[ind + i] = helper[leftind + i];
      counts[nums[ind + i].ind] += jumped;
    }
    // The right half doesn't need to be copied because it's already there
  }

  // Gives TLE
//   public List<Integer> countSmaller(int[] nums) {
//     TreeMap<Integer, Integer> cnt = new TreeMap<>();
//     for (int i : nums) {
//       cnt.put(i, cnt.getOrDefault(i, 0) + 1);
//     }
//     ArrayList<Integer> counts = new ArrayList<>();
//     for (int i : nums) {
//       Map<Integer, Integer> head = cnt.headMap(i);
//       int c = 0;
//       for (int j : head.keySet()) {
//         c += head.get(j);
//       }
//       counts.add(c);

//       int rem = cnt.get(i);
//       rem--;
//       if (rem == 0) {
//         cnt.remove(i);
//       } else {
//         cnt.put(i, rem);
//       }
//     }
//     return counts;
//   }
}

/*
Fruit Into Baskets

In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: you must perform
step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket
to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?

Example 1:
Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].

Example 2:
Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].

Example 3:
Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].

Example 4:
Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 
Note:

1 <= tree.length <= 40000
0 <= tree[i] < tree.length

https://leetcode.com/problems/fruit-into-baskets/
*/

class Solution {
  // Too complicated
//   public int totalFruit(int[] tree) {
//     int n = tree.length;
//     int p1 = 0;
//     int p2 = 0;
//     int res = 0;
//     int b1 = -1;
//     int b2 = -1;
//     int cnt1 = 0;
//     int cnt2 = 0;
//     while (p1 < n) {
//       boolean full = false;
//       int f = tree[p1];
//       if (b1 == -1 || b1 == f) {
//         b1 = f;
//         cnt1++;
//       } else if (b2 == -1 || b2 == f) {
//         b2 = f;
//         cnt2++;
//       } else {
//         full = true;
//       }
//       res = Math.max(res, (p1 - p2));
//       if (full) {
//         while (p2 < p1) {
//           if (b1 == tree[p2]) {
//             cnt1--;
//             if (cnt1 == 0) {
//               b1 = f;
//               cnt1 = 1;
//               p2++;
//               break;
//             }
//           }
//           if (b2 == tree[p2]) {
//             cnt2--;
//             if (cnt2 == 0) {
//               b2 = f;
//               cnt2 = 1;
//               p2++;
//               break;
//             }
//           }
//           p2++;
//         }
//       }
//       p1++;
//     }
//     res = Math.max(res, (p1 - p2));
//     return res;
//   }
  
  public int totalFruit(int[] tree) {
    int n = tree.length;
    Map<Integer, Integer> map = new HashMap<>();
    int res = 0;
    int i = 0;
    for (int j = 0; j < n; j++) {
      int f = tree[j];
      map.put(f, map.getOrDefault(f, 0) + 1);
      while (map.size() > 2) {
        map.put(tree[i], map.get(tree[i]) - 1);
        if (map.get(tree[i]) == 0) {
          map.remove(tree[i]);
        }
        i++;
      }
      res = Math.max(res, j - i + 1);
    }
    return res;
  }
}

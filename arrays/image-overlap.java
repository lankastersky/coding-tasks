/*
Image Overlap

Two images A and B are given, represented as binary, square matrices of the same size.
(A binary matrix has only 0s and 1s as values.)

We translate one image however we choose (sliding it left, right, up, or down any number
of units), and place it on top of the other image.  After, the overlap of this translation
is the number of positions that have a 1 in both images.

(Note also that a translation does not include any kind of rotation.)

What is the largest possible overlap?

Example 1:
Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.

Notes: 
1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1

https://leetcode.com/problems/image-overlap/
*/

class Solution {
  // O(n^4)
//   public int largestOverlap(int[][] A, int[][] B) {
//     int res = 0;
//     res = Math.max(res, calc(A, B));
//     res = Math.max(res, calc(B, A));
//     return res;
//   }
  
//   int calc(int[][] A, int[][] B) {
//     int n = A.length;
//     int res = 0;
//     for (int i = 0; i < n; i++) {
//       for (int j = 0; j < n; j++) {
//         int cur = 0;
//         for (int r = 0; r + i < n; r++) {
//           for (int c = 0; c + j < n; c++) {
//             if (A[r][c] == 1 && A[r][c] == B[i + r][j + c]) {
//               cur++;
//             }
//           }
//         }
//         res = Math.max(res, cur);
//       }
//     }
//     return res;
//   }

  // Good for sparse matrices
  public int largestOverlap(int[][] A, int[][] B) {
    int res = 0;
    int n = A.length;
    List<int[]> al = new ArrayList<>();
    List<int[]> bl = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (A[i][j] == 1) {
          al.add(new int[] {i, j});
        }
        if (B[i][j] == 1) {
          bl.add(new int[] {i, j});
        }
      }
    }
    // Maps vectors to the numbers of overlapped 1's.
    Map<String, Integer> map = new HashMap<>();
    for (int[] a : al) {
      for (int[] b : bl) {
        // The vector determins the overlap.
        // We calculate how many points are overlapped for the given vector.
        String vector = (a[0] - b[0]) + " " + (a[1] - b[1]);
        map.put(vector, map.getOrDefault(vector, 0) + 1);
      }
    }
    for (int v : map.values()) {
      res = Math.max(res, v);
    }
    return res;
  }
}

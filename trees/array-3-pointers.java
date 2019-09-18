/*
Array 3 Pointers
(Find the closest entries in sorted arrays)

You are given 3 arrays A, B and C. All 3 of the arrays are sorted.

Find i, j, k such that :
max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is minimized.
Return the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i]))

Example :

Input : 
        A : [1, 4, 10]
        B : [2, 15, 20]
        C : [10, 12]

Output : 5 
         With 10 from A, 15 from B and 10 from C. 
         
https://www.interviewbit.com/problems/array-3-pointers/
*/

public class Solution {
    class Pair {
        int idx;
        int val;
        Pair(int i, int v) {
            idx = i;
            val = v;
        }
    }
    public int minimize(
        final List<Integer> A, final List<Integer> B, final List<Integer> C) {
        int m = A.size();
        int n = B.size();
        int p = C.size();
        if (m * n * p == 0) {
            return 0;
        }

        int k = 3;
        ArrayList<List<Integer>> arrays = new ArrayList<>();
        arrays.add(A);
        arrays.add(B);
        arrays.add(C);
        int[] idxs = new int[k];
        
        NavigableSet<Pair> set = new TreeSet<>((p1, p2) -> {
            if (p1.val == p2.val) {
              return Integer.compare(p1.idx, p2.idx);
            }
            return Integer.compare(p1.val, p2.val);
        });

        for (int i = 0; i < k; i++) {
            set.add(new Pair(i, arrays.get(i).get(0)));
        }
        int res = Integer.MAX_VALUE;
        while (true) {
            Pair min = set.first();
            Pair max = set.last();
            res = Math.min(res, max.val - min.val);
            int idx = min.idx;
            idxs[idx]++;
            if (idxs[idx] >= arrays.get(idx).size()) {
                return res;
            }
            set.pollFirst();
            set.add(new Pair(idx, arrays.get(idx).get(idxs[idx])));
        }
    }
}

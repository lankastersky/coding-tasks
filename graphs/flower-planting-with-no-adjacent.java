/*
Flower Planting With No Adjacent

You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.

paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.

Also, there is no garden that has more than 3 paths coming into or leaving it.

Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have 
different types of flowers.

Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden. 
The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.

Example 1:

Input: N = 3, paths = [[1,2],[2,3],[3,1]]
Output: [1,2,3]
Example 2:

Input: N = 4, paths = [[1,2],[3,4]]
Output: [1,2,1,2]
Example 3:

Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
Output: [1,2,3,4]
 
Note:

1 <= N <= 10000
0 <= paths.size <= 20000
No garden has 4 or more paths coming into or leaving it.
It is guaranteed an answer exists.

leetcode - Google online assessment
*/

class Solution {
    
    static final int COL = 4;
    Map<Integer, ArrayList<Integer>> adj;
    int[] flowers;
    
    public int[] gardenNoAdj(int N, int[][] paths) {
        adj = new HashMap<>();
        for (int i = 0; i < N; i++) {
            adj.put(i, new ArrayList<>());
        }
        
        fillAdj(N, paths);
        
        flowers = new int[N];
        for (int i = 0; i < N; i++) {
            rec(i);
        }
        return flowers;
    }
    
    boolean rec(int cur) {
        if (flowers[cur] != 0) {
            // visited
            return true;
        }
        boolean[] fl = new boolean[COL + 1];
        ArrayList<Integer> neib = adj.get(cur);
        if (neib == null) {
            return false;
        }
        if (neib.isEmpty()) {
            flowers[cur] = 1;
            return true;
        }
        
        // find flowers of neighbours
        for (int n : neib) {
            int c = flowers[n];
            if (c != 0) {
                fl[c] = true;
            }
        }
        for (int c = 1; c <= COL; c++) {
            if (!fl[c]) {
                flowers[cur] = c;
                boolean res = true;
                for (int n : neib) {
                    if (!rec(n)) {
                        res = false;
                        break;
                    }
                }
                if (res) {
                    return true;
                }
                flowers[cur] = 0;
            }
        }
        return false;
    }
    
    void fillAdj(int N, int[][] paths) {
        for (int[] p : paths) {
            adj.get(p[0] - 1).add(p[1] - 1);
            adj.get(p[1] - 1).add(p[0] - 1);
        }
    }
}

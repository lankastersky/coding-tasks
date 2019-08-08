/*
Number of Equivalent Domino Pairs

Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a==c and b==d),
or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.

Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].


Example 1:

Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
Output: 1
 

Constraints:

1 <= dominoes.length <= 40000
1 <= dominoes[i][j] <= 9

https://leetcode.com/problems/number-of-equivalent-domino-pairs/
*/

// O(nlogn) complexity. Could make O(n) using hashmap.
class Solution {
    class Pair {
        int l;
        int r;
        Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
    public int numEquivDominoPairs(int[][] dominoes) {
        ArrayList<Pair> dom = new ArrayList<>();
        for (int[] d : dominoes) {
            if (d[0] > d[1]) {
                int t = d[0];
                d[0] = d[1];
                d[1] = t;
            }
            dom.add(new Pair(d[0], d[1]));
        }
        
        Collections.sort(dom, (Pair d1, Pair d2) -> {
            if (d1.l == d2.l) {
                return Integer.compare(d1.r, d2.r);
            }
            return Integer.compare(d1.l, d2.l);
        });
        
        int res = 0;
        int n = dominoes.length;
        for (int i = 1; i < n; i++) {
            Pair d = dom.get(i);
            Pair p = dom.get(i - 1);
            int pairs = 0;
            while (d.l == p.l && d.r == p.r) {
                pairs++;
                i++;
                if (i == n) {
                    break;
                }
                d = dom.get(i);
            }
            res += (pairs * (pairs + 1)) / 2;
        }
        return res;
    }
}

/*
Letter Tile Possibilities

You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible non-empty
sequences of letters you can make.

Example 1:

Input: "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
Example 2:

Input: "AAABBC"
Output: 188
 
Note:

1 <= tiles.length <= 7
tiles consists of uppercase English letters.

https://leetcode.com/problems/letter-tile-possibilities/
leetcode - Google Online Assessment
*/

  class Solution {
    public int numTilePossibilities(String tiles) {
      if (tiles == null || tiles.isEmpty()) {
        return 0;
      }
      int[] memo = new int[tiles.length()];
      Arrays.fill(memo, -1);
      char[] ch = tiles.toCharArray();
      Arrays.sort(ch);
      boolean[] used = new boolean[tiles.length()];
      return rec(ch, -1, used, memo);
    }

    int rec(char[] tiles, int pos, boolean[] used, int[] memo) {
      int res = 0;
      int n = tiles.length;
      int last = -1;
      for (int i = 0; i < n; i++) {
        if (used[i]) {
          continue;
        }

        if (last != -1 && tiles[i] == last) {
          continue;
        }
        used[i] = true;
        last = tiles[i];
        res++;
        //print(tiles, used);
        res += rec(tiles, i, used, memo);
        used[i] = false;
      }

      // memo[pos] = res;
      return res;
    }
  

  void print(char[] tiles, boolean[] used) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < used.length; i++) {
      if (used[i]) {
        sb.append(tiles[i]);
      }
    }
    System.out.println(sb);
  }
}

/*
Hand of Straights

Alice has a hand of cards, given as an array of integers.

Now she wants to rearrange the cards into groups so that each group is size W, 
and consists of W consecutive cards.

Return true if and only if she can.

Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].

Example 2:
Input: hand = [1,2,3,4,5], W = 4
Output: false
Explanation: Alice's hand can't be rearranged into groups of 4.

Note:
1 <= hand.length <= 10000
0 <= hand[i] <= 10^9
1 <= W <= hand.length

https://leetcode.com/problems/hand-of-straights
*/

import java.util.*;

class Solution {
  public boolean isNStraightHand(int[] hand, int W) {
    if (hand.length % W != 0) {
      return false;
    }
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int i : hand) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }
    int w = 0;
    int cur = 0;
    for (int i = 0; i < hand.length; i++) {
      if (w == 0) {
        cur = map.firstKey();  
      } else {
        if (!map.containsKey(cur + 1)) {
          return false;
        }
        cur++;
      }
      map.put(cur, map.get(cur) - 1);
      if (map.get(cur) == 0) {
        map.remove(cur);
      }        
      w++;
      if (w == W) {
        w = 0;
      }
    }
    return true;
  }
}

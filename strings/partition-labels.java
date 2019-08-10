/*
Partition Labels

A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that
each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.

leetcode - amazon online assessment
https://leetcode.com/problems/partition-labels/
*/

// O(nlogn). Could be solved in O(n), see https://leetcode.com/articles/partition-labels/
class Solution {
    class Range {
        int b;
        int e;
        char c;
        Range(int b, int e, char c) {
            this.b = b;
            this.e = e;
            this.c = c;
        }
    }
    public List<Integer> partitionLabels(String S) {
        HashMap<Character, Range> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (map.containsKey(c)) {
                Range r = map.get(c);
                r.e = i;
            } else {
                Range r = new Range(i, i, c);
                map.put(c, r);
            }
        }
        
        ArrayList<Range> list = new ArrayList<>(map.values());
        Collections.sort(list, (l, r) -> {
            if (l.b == r.b) {
                return Integer.compare(l.e, r.e);
            }
            return Integer.compare(l.b, r.b);
        });
        
        List<Integer> res = new ArrayList<>();
        int length = 0;
        for (int i = 1; i < list.size(); i++) {
            Range prev = list.get(i - 1);
            Range cur = list.get(i);
            int end = prev.e;
            while (end > cur.b) {
                i++;
                end = Math.max(end, cur.e);
                if (i == list.size()) {
                    break;
                }
                cur = list.get(i);
            }
            res.add(end + 1 - length);                
            length += res.get(res.size() - 1);
        }
        if (length < S.length()) {
            res.add(S.length() - length);
        }
        return res;
    }
}

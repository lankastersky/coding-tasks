/*
Delete Operation for Two Strings

Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step
you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.

https://leetcode.com/problems/delete-operation-for-two-strings/
*/

class Solution {
    public int minDistance(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return minDistance(word1, word2, 0, 0, memo);
    }

    int minDistance(String word1, String word2, int i, int j, int[][] memo) {
        if (i == word1.length() && j == word2.length()) {
            return 0;
        }
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (word1.charAt(i) == word2.charAt(j)) {
            return minDistance(word1, word2, i + 1, j + 1, memo);
        }
        int res = Math.min(
            minDistance(word1, word2, i, j + 1, memo),
            minDistance(word1, word2, i + 1, j, memo)
        ) + 1;
        memo[i][j] = res;
        return res;
    }
};

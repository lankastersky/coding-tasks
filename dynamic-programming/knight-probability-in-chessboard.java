/*
Knight Probability in Chessboard

On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. 
The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, 
then one square in an orthogonal direction.

https://assets.leetcode.com/uploads/2018/10/12/knight.png

Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off
the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that 
the knight remains on the board after it has stopped moving.

Example:

Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
 
Note:

N will be between 1 and 25.
K will be between 0 and 100.
The knight always initially starts on the board.

https://leetcode.com/problems/knight-probability-in-chessboard/
*/

class Solution {
    
    int[] dirX = {-2, -2, -1, 1, 2, 2, -1, 1};
    int[] dirY = {-1, 1, -2, -2, -1, 1, 2, 2};
    int M = 8; // moves
    double[][][] memo;
    public double knightProbability(int N, int K, int r, int c) {
        memo = new double[K + 1][N + 1][N + 1];
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                for (int k = 0; k <= N; k++) {
                    memo[i][j][k] = -1;
                }
            }
        }
        return prob(N, K, r, c, memo);
        
    }
    
    double prob(int N, int K, int r, int c, double[][][] memo) {
        if (K == 0) {
            if (!onBoard(N, r, c)) {
                return 0;
            }
            return 1;
        }
        if (memo[K][r][c] != -1) {
            return memo[K][r][c];
        }
        double res = 0;
        for (int i = 0; i < M; i++) {
            int x = r + dirX[i];
            int y = c + dirY[i];
            if (!onBoard(N, x, y)) {
                continue;
            }
            res += prob(N, K - 1, x, y, memo) / 8;
        }
        memo[K][r][c] = res;
        return res;
    }
    
    boolean onBoard(int N, int r, int c) {
        if (r >= 0 && r < N && c >= 0 && c < N) {
            return true;
        }
        return false;
    }
}

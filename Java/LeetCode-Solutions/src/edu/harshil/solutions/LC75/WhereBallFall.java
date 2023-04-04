package edu.harshil.solutions.LC75;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * https://leetcode.com/problems/where-will-the-ball-fall/description/
 * You have a 2-D grid of size m x n representing a box, and you have n balls. The box is open on the top and bottom sides.
 *
 * Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball to the right or to the left.
 *
 * A board that redirects the ball to the right spans the top-left corner to the bottom-right corner and is represented in the grid as 1.
 * A board that redirects the ball to the left spans the top-right corner to the bottom-left corner and is represented in the grid as -1.
 * We drop one ball at the top of each column of the box. Each ball can get stuck in the box or fall out of the bottom. A ball gets stuck if it hits a "V" shaped pattern between two boards or if a board redirects the ball into either wall of the box.
 *
 * Return an array answer of size n where answer[i] is the column that the ball falls out of at the bottom after dropping the ball from the ith column at the top, or -1 if the ball gets stuck in the box.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
 * Output: [1,-1,-1,-1,-1]
 * Explanation: This example is shown in the photo.
 * Ball b0 is dropped at column 0 and falls out of the box at column 1.
 * Ball b1 is dropped at column 1 and will get stuck in the box between column 2 and 3 and row 1.
 * Ball b2 is dropped at column 2 and will get stuck on the box between column 2 and 3 and row 0.
 * Ball b3 is dropped at column 3 and will get stuck on the box between column 2 and 3 and row 0.
 * Ball b4 is dropped at column 4 and will get stuck on the box between column 2 and 3 and row 1.
 * Example 2:
 *
 * Input: grid = [[-1]]
 * Output: [-1]
 * Explanation: The ball gets stuck against the left wall.
 * Example 3:
 *
 * Input: grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
 * Output: [0,1,2,3,4,-1]
 */
public class WhereBallFall {

    /**
     * Solution Method 1: Using DP
     * Runtime 4 ms Beats 16.19% O(MN)
     * Memory 43.7 MB Beats 60.75% O(MN)
     */
    public int[] findBallDP(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[] out = new int[n];
        int[][] dp = new int[m + 1][n];
        for (int row = m; row >= 0; row--) {
            for (int col = 0; col < n; col++) {
                //values for the last row
                if (row == m) {
                    dp[row][col] = col;
                    continue;
                }
                int nextCol = col + grid[row][col];
                //Blockage condition
                if (nextCol == n || nextCol < 0 || grid[row][col] != grid[row][nextCol])
                    dp[row][col] = -1;
                //Get the column value
                else
                    dp[row][col] = dp[row + 1][nextCol];
                //When we reach first row
                if (row == 0)
                    out[col] = dp[row][col];
            }
        }
        return out;

    }

    /**
     * Solution Method 2: Using DFS
     * Runtime 1 ms Beats 86.40% O(MN)
     * Memory 44.1 MB Beats 31.9% O(M)
     */
    public int[] findBallDFS(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[] out = new int[n];
        for (int startCol = 0; startCol < n; startCol++) {

            out[startCol] = dfs(grid, 0, startCol);
        }
        return out;

    }

    public int dfs(int[][] grid, int row, int col) {

        if (row == grid.length)
            return col;
        int nextCol = grid[row][col] + col;

        //Blockage condition
        if (nextCol == grid[0].length || nextCol < 0 || grid[row][col] != grid[row][nextCol])
            return -1;

        return dfs(grid, row + 1, nextCol);
    }

    /**
     * Solution Method 3: Iterative
     * Runtime 2 ms Beats 57.66% O(MN)
     * Memory 43.5 MB Beats 87.14% O(1)
     */
    public int[] findBall(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[] out = new int[n];
        for (int startCol = 0; startCol < n; startCol++) {
            int col = startCol;
            for (int row = 0; row < m; row++) {
                int nextCol = col + grid[row][col];
                // Check for -1 cases, next column out of bounds or forming a V
                if (nextCol < 0 || nextCol >= n || grid[row][col] != grid[row][nextCol]) {
                    out[startCol] = -1;
                    break;
                }
                // Traverse till ball exists
                out[startCol] = nextCol;
                col = nextCol;
            }
        }
        return out;

    }
}

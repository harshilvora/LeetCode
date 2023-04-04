package edu.harshil.solutions.LC75;

import java.util.*;

/**
 * https://leetcode.com/problems/spiral-matrix
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * Example 1:
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 *
 *
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {

    /**
     * Solution1: Using direction array to move in the spiral
     * Runtime 0 ms Beats 100% O(MN)
     * Memory 40.8 MB Beats 21.26% O(N)
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        // Right, Down, Left, Up
        int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};
        ArrayList<Integer> out = new ArrayList<>();

        int m = matrix.length;

        int n = matrix[0].length;

        int totcnt=0;
        int dir =0;
        int i =0, j=0;
        int cnt = 0;
        while(totcnt<m*n){
            int val = matrix[i][j];

            out.add(val);

            int tempi = i, tempj = j;
            tempi += direction[dir][0];
            tempj += direction[dir][1];
            // If going up or down just check the row constraint
            // If going left or right just check the column constraint
            if((dir%2!=0 && (tempi>=m-cnt || tempi<cnt)) || (dir%2==0 && (tempj>=n-cnt || tempj<cnt))){
                dir += 1;
                dir = dir%4;
                // loop is done
                if(dir==3)
                    cnt++;
                i += direction[dir][0];
                j += direction[dir][1];
            }else{
                i = tempi;
                j = tempj;
            }
            totcnt++;
        }
        return out;
    }

    /**
     * Solution2: Looping through one direction one at a time
     * Runtime 0 ms Beats 100% O(MN)
     * Memory 40.2 MB Beats 90.33% O(1)
     *
     */
    public List<Integer> spiralOrder(int[][] matrix) {

        ArrayList<Integer> out = new ArrayList<>();
        int rowEnd = matrix.length-1;
        int m = matrix.length;
        int colEnd = matrix[0].length-1;
        int n = matrix[0].length;
        int rowStart=0, colStart=0;
        int totcnt=0;

        while(totcnt<m*n){
            // Go Right
            for(int j = colStart; j<=colEnd; j++){
                out.add(matrix[rowStart][j]);
                totcnt++;
            }
            rowStart++;
            // Go down
            for(int i= rowStart; i<=rowEnd; i++){
                out.add(matrix[i][colEnd]);
                totcnt++;
            }
            colEnd--;
            //Check for end condition
            if(totcnt==m*n)
                break;
            // Go left
            for(int j = colEnd; j>=colStart; j--){
                out.add(matrix[rowEnd][j]);
                totcnt++;
            }
            rowEnd--;
            //Check for end condition
            if(totcnt==m*n)
                break;
            // Go up
            for(int i= rowEnd; i>=rowStart; i--){
                out.add(matrix[i][colStart]);
                totcnt++;
            }
            colStart++;

        }
        return out;
    }
}

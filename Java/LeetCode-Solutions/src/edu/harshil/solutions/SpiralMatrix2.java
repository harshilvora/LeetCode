package edu.harshil.solutions;


/**
 * https://leetcode.com/problems/spiral-matrix-ii/description/
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: [[1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 20
 */
public class SpiralMatrix2 {

    /**
     * Runtime 0 ms Beats 100%
     * Memory 40.8 MB Beats 43.43%
     */
    public int[][] generateMatrix(int n) {

        int i=1;
        int rowEnd=n-1, colEnd=n-1, rowStart=0, colStart=0;
        int[][] ans= new int[n][n];
        while(i<=n*n){

            //Right
            for(int j=colStart; j<=colEnd; j++){
                ans[rowStart][j] = i++;
            }
            rowStart++;

            //Down
            for(int j=rowStart; j<=rowEnd; j++){
                ans[j][colEnd] = i++;
            }
            colEnd--;

            if(i>n*n)
                break;

            //Left
            for(int j=colEnd; j>=colStart; j--){
                ans[rowEnd][j] = i++;
            }
            rowEnd--;
            if(i>n*n)
                break;
            //Up
            for(int j=rowEnd; j>=rowStart; j--){
                ans[j][colStart] = i++;
            }
            colStart++;
        }

        return ans;

    }
}

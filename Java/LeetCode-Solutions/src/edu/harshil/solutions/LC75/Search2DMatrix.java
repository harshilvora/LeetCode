package edu.harshil.solutions.LC75;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/description/
 * You are given an m x n integer matrix with the following two properties:
 * 
 * Each row is sorted in non-decreasing order.
 * The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 * 
 * You must write a solution in O(log(m * n)) time complexity.
 *
 * Example 1:
 * 
 * 
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * Example 2:
 * 
 * 
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 * 
 * 
 * Constraints:
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 */
public class Search2DMatrix {

    /**
     * Runtime 0 ms Beats 100% O(log(m*n))
     * Memory 41.2 MB Beats 99.44% O(log(m*n))
     *
     * Binary Search with recursion
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length;
        int c = matrix[0].length;

        return binarySearch(matrix, target, 0, (r * c) - 1, r, c);

    }

    public boolean binarySearch(int[][] matrix, int target, int low, int high, int r, int c) {

        if (high < low)
            return false;

        int mid = (low + high) >>> 1;
        /*
        In a 2D matrix, each element can be uniquely identified by its row index and column index.
        Let's say we have a matrix of size m x n (m rows and n columns) and we want to find the row and column index of an element at index i in the matrix.
        To find the row index, we can divide the index i by the number of columns n. The quotient of this division gives us the row index.
        This is because every n elements in the matrix belong to the same row. For example, in a matrix of size 3 x 4, the first 4 elements belong to the first row, the next 4 elements belong to the second row, and so on.
        To find the column index, we can take the remainder of the same division (i divided by n). This is because the remainder gives us the position of the element within the row.
        For example, if i is 5 and n is 4, then the row index is i/n = 5/4 = 1 (integer division gives us 1) and the column index is i mod n = 5 mod 4 = 1.
         */
        int midval = matrix[mid / c][mid % c];
        if (midval == target)
            return true;

        else if (midval > target)
            return binarySearch(matrix, target, low, mid - 1, r, c);

        else
            return binarySearch(matrix, target, mid + 1, high, r, c);
    }
}

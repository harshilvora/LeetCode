package edu.harshil.solutions;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 * Given an integer array nums, find a
 * subarray
 *  that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a
 * 32-bit integer.
 *
 * Runtime 2 ms Beats 70.64% O(N)
 * Memory 42.1 MB Beats 96.56% O(N)
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int[] maxDP = new int[nums.length];
        int[] minDP = new int[nums.length];
        maxDP[0] = nums[0];
        minDP[0] = nums[0];
        int max = maxDP[0];
        int min = maxDP[0];

        for(int i = 1; i<nums.length; i++){
            maxDP[i] = Math.max(maxDP[i-1]* nums[i], nums[i]);
            maxDP[i] = Math.max(minDP[i-1]* nums[i], maxDP[i]);
            //To take care of negative test case
            minDP[i] = Math.min(minDP[i-1] * nums[i], nums[i]);
            minDP[i] = Math.min(maxDP[i-1] * nums[i], minDP[i]);
            max = Math.max(maxDP[i], max);

        }
        return max;
    }
}

package edu.harshil.solutions;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 * Given an integer array nums, return an array answer such that answer[i] is
 * equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a
 * 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the
 * division operation.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a
 * 32-bit integer.
 *
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The
 * output array does not count as extra space for space complexity analysis.)
 */
public class ProductofArrayExceptSelf {

    /**
     * Runtime 2 ms Beats 67.14% O(N)
     * Memory 50.8 MB Beats 61.91% O(N)
     */
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        Arrays.fill(output, 1);
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        Arrays.fill(left, 1);
        Arrays.fill(right, 1);

        for(int i=1;i<nums.length; i++){
            left[i] = left[i-1] * nums[i-1];
        }
        for(int i=nums.length -2; i>=0; i--){
            right[i] = right[i+1]*nums[i+1];
        }
        for(int i=0;i<nums.length; i++){
            output[i] = left[i] * right[i];
        }
        return output;
    }

    /**
     * Runtime 1 ms Beats 100% O(N)
     * Memory 50 MB Beats 99.49% O(1)
     */
    public int[] productExceptSelfSpaceOptimized(int[] nums) {
        int[] output = new int[nums.length];
        output[0] = 1;
        int right = 1;
        for(int i=1;i<nums.length; i++){
            output[i] = output[i-1] * nums[i-1];
        }
        for(int i=nums.length-2;i>=0; i--){
            right *= nums[i+1];
            output[i] = output[i] * right;
        }
        return output;
    }


}


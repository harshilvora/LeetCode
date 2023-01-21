package edu.harshil.solutions;

/**
 * https://leetcode.com/problems/maximum-subarray/description/
 * Given an integer array nums, find the
 * subarray
 *  with the largest sum, and return its sum.
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 *
 * Follow up: If you have figured out the O(n) solution, try coding another
 * solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {

    /**
     * Runtime 1 ms Beats 100% O(N)
     * Memory 51.6 MB Beats 82.75% O(N)
     */
    public int maxSubArrayDP(int[] nums) {

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i =1; i<nums.length; i++){
            dp[i] = Math.max(dp[i-1] + nums[i],nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * Runtime 20 ms Beats 5.71% O(NlogN)
     * Memory 51.7 MB Beats 76.97% O(logN)
     *
     */
    public int maxSubArrayDivideConquer(int[] nums) {
        return findMax(nums, 0, nums.length);
    }

    public int findMax(int[] nums, int i, int j){
        int sum = 0,leftMaxSum = Integer.MIN_VALUE,
                rightMaxSum = Integer.MIN_VALUE;
        int mid = (i+j)/2;
        for(int left = mid; left>=i; i--){
            sum += nums[i];
            if(sum > leftMaxSum){
                leftMaxSum = sum;
            }
        }
        sum = 0;
        for(int right = mid+1; right<j; j++){
            sum += nums[j];
            if(sum > rightMaxSum){
                rightMaxSum = sum;
            }
        }
        int max = Math.max(findMax(nums, i, mid), findMax(nums, mid+1, j));
        return Math.max(max, leftMaxSum+rightMaxSum);
    }
}

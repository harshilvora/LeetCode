package edu.harshil.solutions;


/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 * You are given an integer array prices where prices[i] is the price of a
 * given stock on the ith day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only
 * hold at most one share of the stock at any time. However, you can buy it
 * then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5),
 * profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3
 * = 3.
 * Total profit is 4 + 3 = 7.
 *
 * Constraints:
 *
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 */
public class BestTimetoBuyandSellStock2 {


    /**
     *
     * Runtime 1 ms Beats 90.1% O(N)
     * Memory 42.1 MB Beats 80.15% O(1)
     */
    public int maxProfit(int[] prices) {

        int maxProfit = 0;
        int stock = prices[0];
        for(int i=1;i <prices.length; i++){
            if(prices[i]>stock)
                maxProfit += prices[i] - stock;
            stock = prices[i];
        }

        return maxProfit;
    }
}

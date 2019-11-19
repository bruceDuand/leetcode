/*
 * @lc app=leetcode id=122 lang=java
 *
 * [122] Best Time to Buy and Sell Stock II
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1)
            return 0;

        int[] diff_prices = new int[prices.length - 1];
        for (int i = 1; i < prices.length; i++)
            diff_prices[i - 1] = prices[i] - prices[i - 1];

        int res = 0;
        for (int i = 0; i < diff_prices.length; i++) {
            if (diff_prices[i] > 0)
                res = res + diff_prices[i];
        }

        return res;

    }
}
// @lc code=end

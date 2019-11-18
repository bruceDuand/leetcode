/*
 * @lc app=leetcode id=121 lang=java
 *
 * [121] Best Time to Buy and Sell Stock
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1)
            return 0;

        int[] diff_prices = new int[prices.length - 1];
        for (int i = 1; i < prices.length; i++)
            diff_prices[i - 1] = prices[i] - prices[i - 1];

        int max_so_far = 0;
        int max_end_here = 0;

        int max_start_index = 0;
        int max_end_index = 0;
        for (int i = 0; i < diff_prices.length; i++) {
            max_end_here = max_end_here + diff_prices[i];
            if (max_end_here < 0) {
                max_end_here = 0;
                max_start_index = i + 1;
            } else if (max_end_here > max_so_far) {
                max_so_far = max_end_here;
                max_end_index = i;
            }
        }

        return max_so_far;

    }
}
// @lc code=end

// this method is O(n), but the ratio is still 82.61%, interesting
// the method is better than divide-and-conquer here,
// but it also proves that divide-and-conquer is very powerful in solving
// problems, excellent
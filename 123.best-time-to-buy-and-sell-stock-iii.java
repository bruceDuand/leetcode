import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode id=123 lang=java
 *
 * [123] Best Time to Buy and Sell Stock III
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;

        int n = prices.length;
        int[] maxprofit_left = new int[n];
        int maxprofit = 0;
        int minprice = prices[0];

        for (int i = 0; i < n; i++) {
            if (minprice > prices[i])
                minprice = prices[i];
            else
                maxprofit = Math.max(maxprofit, prices[i] - minprice);
            maxprofit_left[i] = maxprofit;
        }

        maxprofit = 0;
        int res = 0;
        int maxprice = prices[n - 1];

        for (int i = n - 1; i >= 0; i--) {
            if (maxprice < prices[i])
                maxprice = prices[i];
            else
                maxprofit = Math.max(maxprofit, maxprice - prices[i]);

            res = Math.max(res, maxprofit + maxprofit_left[i]);
        }

        return res;

    }
}
// @lc code=end

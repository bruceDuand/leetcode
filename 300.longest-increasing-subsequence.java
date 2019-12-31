import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/*
 * @lc app=leetcode id=300 lang=java
 *
 * [300] Longest Increasing Subsequence
 */

// @lc code=start
class Solution {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int curind = 0;
        for (int x : nums) {
            if (curind == 0 || x > dp[curind - 1])
                dp[curind++] = x;
            else {
                int i = 0, j = curind - 1;
                int ans = 0;
                while (i <= j) {
                    int mid = (i + j) / 2;
                    if (dp[mid] >= x) {
                        ans = mid;
                        j = mid - 1;
                    } else
                        i = mid + 1;
                }

                dp[ans] = x;
            }
        }
        return curind;
    }

    public int lengthOfLIS2(int[] nums) {
        int[][] memo = new int[nums.length + 1][nums.length];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        // initialize each position to be -1, in case prevind+1 == curind
        return findLIS(nums, -1, 0, memo);
    }

    // the reason why the first thought is not correct is that I simply considered
    // the subsequence that only starts with the first number
    // here I only considered the part that is in the if block, and ignored the part
    // after it, which skips the current value and considered the one without it
    private int findLIS(int[] nums, int prevind, int curind, int[][] memo) {
        if (curind == nums.length) {
            return 0;
        }

        if (memo[prevind + 1][curind] >= 0)
            return memo[prevind + 1][curind];

        int taken = 0;
        // in case the prevind less than 0, it means the first number, where the single
        // character has a length of 1
        if (prevind < 0 || nums[curind] > nums[prevind]) {
            // in this case the prevind is updated with curind, since in recursion method,
            // the next step should be updated with the current step
            taken = 1 + findLIS(nums, curind, curind + 1, memo);
        }
        int nottaken = findLIS(nums, prevind, curind + 1, memo);

        int max = Math.max(taken, nottaken);
        // update the memo with the maximum value, since after recursion, the returned
        // value must the maximum one
        memo[prevind + 1][curind] = max;

        return memo[prevind + 1][curind];
    }

    public int lengthOfLIS3(int[] nums) {
        return findLIS(nums, Integer.MIN_VALUE, 0);
    }

    private int findLIS(int[] nums, int prev, int curind) {
        if (curind == nums.length) {
            return 0;
        }

        int taken = 0;
        if (nums[curind] > prev) {
            taken = 1 + findLIS(nums, nums[curind], curind + 1);
        }
        int nottaken = findLIS(nums, prev, curind + 1);

        return Math.max(taken, nottaken);
    }

    public int lengthOfLIS4(int[] nums) {
        int res = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);

            }
            // The maximum length may not be the subsequence ending with the last number
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // failed
    public int lengthOfLIS5(int[] nums) {
        Stack<Integer> stk = new Stack<>();
        int res = 0;
        for (int x : nums) {
            if (stk.isEmpty() || x > stk.peek()) {
                stk.push(x);
                res = Math.max(res, stk.size());
                System.out.println(res);
            } else {
                while (!stk.isEmpty() && stk.peek() >= x) {
                    stk.pop();
                }
                stk.push(x);
            }
        }
        return res;
    }

}
// @lc code=end

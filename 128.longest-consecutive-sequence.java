import java.util.HashSet;

/*
 * @lc app=leetcode id=128 lang=java
 *
 * [128] Longest Consecutive Sequence
 */

// @lc code=start
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null)
            return 0;

        HashSet<Integer> numset = new HashSet<Integer>();
        int N = nums.length;

        for (int i = 0; i < N; i++) {
            numset.add(nums[i]);
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            if (!numset.contains(nums[i] - 1)) {
                int temp = nums[i];
                while (numset.contains(temp))
                    temp++;

                if (temp - nums[i] > res)
                    res = temp - nums[i];
            }
        }

        return res;
    }
}
// @lc code=end

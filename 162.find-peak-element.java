/*
 * @lc app=leetcode id=162 lang=java
 *
 * [162] Find Peak Element
 */

// @lc code=start
class Solution {
    public int findPeakElement(int[] nums) {
        return findPeak(nums, 0, nums.length - 1);
    }

    private int findPeak(int[] nums, int left, int right) {
        int mid = (left + right) / 2;
        if ((mid == 0 || nums[mid - 1] < nums[mid]) && (mid == nums.length - 1 || nums[mid + 1] < nums[mid]))
            return mid;

        if (mid > 0 && nums[mid - 1] > nums[mid])
            return findPeak(nums, left, mid - 1);
        else
            return findPeak(nums, mid + 1, right);

    }
}
// @lc code=end

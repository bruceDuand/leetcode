#
# @lc app=leetcode id=80 lang=python3
#
# [80] Remove Duplicates from Sorted Array II
#

# @lc code=start
class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        if len(nums) < 3:
            return len(nums)

        end = 1
        for i in range(2, len(nums)):
            if nums[i] != nums[end-1]:
                end += 1
                nums[end] = nums[i]

        return end+1

# @lc code=end

# note
# consider version 1, use a new variable "end" denote the end of 
# the new array with none duplicates, and update the nums[end] value
# based on if value nums[i] equals to nums[end]
# in version 2, just consider the value nums[end-1]
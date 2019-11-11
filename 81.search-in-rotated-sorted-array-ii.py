#
# @lc app=leetcode id=81 lang=python3
#
# [81] Search in Rotated Sorted Array II
#

# @lc code=start
class Solution:
    def search(self, nums: List[int], target: int) -> bool:
        if not nums:
            return False

        left, right = 0, len(nums) - 1
        if target == nums[0]:
            return True

        while left <= right:
            mid = int((left + right) / 2)
            if nums[mid] == target:
                return True
            if nums[mid] < target:
                if target > nums[0]:
                    if nums[mid] >= nums[0]:
                        left = mid + 1
                    else:
                        right = mid - 1
                else:
                    left = mid + 1
            elif nums[mid] > target:
                if target < nums[0]:
                    if nums[mid] >= nums[0]:
                        left = mid + 1
                    else:
                        right = mid - 1
                else:
                    right = mid - 1
        
        if nums[mid] == target:
            return True

# @lc code=end

      
#     6
#   5
# 2          2
#           1
#       0 0


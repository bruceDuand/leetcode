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
            if nums[mid] < nums[right]:
                if target > nums[mid] and target <= nums[right]:
                    left = mid + 1
                else:
                    right = mid - 1
            elif nums[mid] > nums[right]:
                if target < nums[mid] and target >= nums[left]:
                    right = mid - 1
                else:
                    left = mid + 1
            else:
                right -= 1

        
        return False

# @lc code=end

      
#     6
#   5
# 2          2
#           1
#       0 0

# note 
# the idea gradually gets rid of half of the array 
# but when duplicate numbers exist, in some cases the algorithm cannot delete
# half of the array due to the same of mid,left,right value, in these cases,
# the only thing can do is end = end - 1 


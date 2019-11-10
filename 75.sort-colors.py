#
# @lc app=leetcode id=75 lang=python3
#
# [75] Sort Colors
#

# @lc code=start
class Solution:
    def sortColors(self, nums: List[int]) -> None:
        red, blue = 0, len(nums)-1
        cur = 0
        while cur <= blue:
            if nums[cur] == 0:
                nums[cur], nums[red] = nums[red], nums[cur]
                red += 1
                cur = red
            elif nums[cur] == 2:
                nums[cur], nums[blue] = nums[blue], nums[cur]
                blue -= 1
            else:
                cur += 1
            
        
# @lc code=end


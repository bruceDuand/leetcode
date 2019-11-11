#
# @lc app=leetcode id=84 lang=python3
#
# [84] Largest Rectangle in Histogram
#

# @lc code=start
class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        hbar = []
        
        heights.append(0)

        res = 0
        i   = 0
        while i < len(heights):
            if len(hbar) == 0 or heights[i] >= heights[hbar[-1]]:
                hbar.append(i)
                i += 1
            else:
                curb = hbar[-1]
                hbar.pop()
                width = i if len(hbar)==0 else i-hbar[-1]-1
                res = max(res, heights[curb]*width)
                
        return res
# @lc code=end

# note
# the use of stack is very excellent, and this is why we need stack!
# pay attention to this problem, and has a second look of how stack
# is implemented here.
# make some notes later! 
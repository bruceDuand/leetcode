#
# @lc app=leetcode id=66 lang=python3
#
# [66] Plus One
#

# @lc code=start
class Solution:
    def plusOne(self, digits: List[int]) -> List[int]:
        digits[-1] = digits[-1] + 1
        i = len(digits) - 1
        while(digits[i] == 10 and i > 0):
            digits[i] = 0
            digits[i-1] = digits[i-1] + 1
            i -= 1
        

        if digits[0] == 10:
            digits.insert(0, 1)
            digits[1] = 0
        
        return digits
        
# @lc code=end


#
# @lc app=leetcode id=78 lang=python3
#
# [78] Subsets
#

# @lc code=start
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:

        res = []
        def dfs(nums, temp, i, res):
            res.append(temp[:])
            for i in range(i, len(nums)):
                temp.append(nums[i])
                dfs(nums, temp, i+1, res)
                temp.pop()

        dfs(nums, [], 0, res)
        return res
                
        
# @lc code=end

# note: (important)
# assignments in python does not copy object, "=" sign builds a 
# connection between an object and a target, which means here the
# res.append(temp) let all the objects in res have a connection to
# the same temp, so they always have the same value.
# with res.append(temp[:]), the statement makes a copy of temp, and assigns
# it to be an object in res, which is more reasonable 

#
# @lc app=leetcode id=85 lang=python3
#
# [85] Maximal Rectangle
#

# @lc code=start
class Solution:
    def maximalRectangle(self, matrix: List[List[str]]) -> int:
        if len(matrix) == 0 or len(matrix[0]) == 0:
            return 0
        m, n = len(matrix), len(matrix[0])
        olength = [[0 for _ in range(n)] for _ in range(m)]
        res = 0
        for i in range(m):
            for j in range(n):
                if matrix[i][j] == "0":
                    continue
                if j == 0:
                    olength[i][j] = 1
                else:
                    olength[i][j] = olength[i][j-1] + 1
        
        for i in range(m):
            for j in range(n):
                if olength[i][j] == 0:
                    continue
                ab = olength[i][j]
                res = max(res, ab)
                for k in range(i-1, -1, -1):
                    if olength[k][j] == 0:
                        break
                    ab = min(ab, olength[k][j])
                    res = max(res, ab * (i - k + 1))
        return res
# @lc code=end

# The code below implemented the largestRectangularInHist function
# solved in the last problem, the idea is excellent
# def maximalRectangle(self, matrix: List[List[str]]) -> int:
#     if not matrix:
#         return 0
    
#     res = 0
#     m, n = len(matrix), len(matrix[0])
#     heights = [0 for _ in range(n)]

#     for i in range(m):
#         for j in range(n):
#             if matrix[i][j] == "0":
#                 heights[j] = 0
#             else:
#                 heights[j] += 1 
#         curmax = self.largestRectangularInHist(heights.copy())
#         res = max(res, curmax)
#     return res

# def largestRectangularInHist(self, heights: List[int]) -> int:
#     res = 0
#     heights.append(0)
#     hlist = []

#     i = 0
#     while i < len(heights):
#         if len(hlist) == 0 or heights[i] > heights[hlist[-1]]:
#             hlist.append(i)
#             i += 1
#         else:
#             ind = hlist[-1]
#             hlist.pop()
#             res = max(res, heights[ind]*(i if len(hlist)==0 else i-hlist[-1]-1))

#     return res
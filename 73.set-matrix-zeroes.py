#
# @lc app=leetcode id=73 lang=python3
#
# [73] Set Matrix Zeroes
#

# @lc code=start
class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        m, n = len(matrix), len(matrix[0])
        rowzero = False
        colzero = False

        for i in range(m):
            if matrix[i][0] == 0:
                colzero = True
        
        for j in range(n):
            if matrix[0][j] == 0:
                rowzero = True
        
        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][j] == 0:
                    matrix[i][0], matrix[0][j] = 0, 0
        
        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][0] == 0 or matrix[0][j] == 0:
                    matrix[i][j] = 0
        
        if rowzero:
            for j in range(n):
                matrix[0][j] = 0

        if colzero:
            for i in range(m):
                matrix[i][0] = 0

        
# @lc code=end


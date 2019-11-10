#
# @lc app=leetcode id=74 lang=python3
#
# [74] Search a 2D Matrix
#

# @lc code=start
class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        if not matrix:
            return False

        m, n = len(matrix), len(matrix[0])
        if m == 0 or n == 0:
            return False

        rowl, rowr = 0, m-1
        while(rowl+1 < rowr):
            rowmid = int((rowl + rowr) / 2)
            if matrix[rowmid][0] == target or matrix[rowmid+1][0] == target:
                return True
            elif matrix[rowmid][0] > target:
                rowr = rowmid
            else:
                rowl = rowmid
        
        coll, colr = 0, n-1
        while(coll+1 < colr):
            colmid = int((colr + coll) / 2)
            if matrix[rowl][colmid] == target or matrix[rowl][colmid+1] == target:
                return True
            elif matrix[rowl][colmid] > target:
                colr = colmid
            else:
                coll = colmid

        print(rowl, rowr, coll, colr)

        if matrix[rowl][coll] == target or matrix[rowl][colr] == target:
            return True
        else:
            return False
# @lc code=end
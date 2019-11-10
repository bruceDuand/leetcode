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
            if matrix[rowmid][0] >= target:
                rowr = rowmid
            else:
                rowl = rowmid

        if matrix[rowr][0] == target or matrix[rowl] == target:
            return True
        elif matrix[rowr][0] > target:
            rowt = rowl
        else:
            rowt = rowr
        
        coll, colr = 0, n-1
        while(coll+1 < colr):
            colmid = int((colr + coll) / 2)
            if matrix[rowt][colmid] >= target:
                colr = colmid
            else:
                coll = colmid

        if matrix[rowt][coll] == target or matrix[rowt][colr] == target:
            return True
        else:
            return False
# @lc code=end


# note
# when use binary search, let condition be while(left<=right)
# and the update left and right value be right = mid-1 and
# left = mid+1 is very helpful, and then check if A[left] equals
# the target value
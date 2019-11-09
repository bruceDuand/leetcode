#
# @lc app=leetcode id=59 lang=python3
#
# [59] Spiral Matrix II
#

# @lc code=start
class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        if n == 0:
            return []
        res = [[0 for _ in range(n)] for _ in range(n)]
        dr = [0, 1, 0, -1]
        dc = [1, 0, -1, 0]

        cr, cc, di = 0, 0, 0
        for num in range(1, n*n+1):
            res[cr][cc] = num
            cr, cc = cr + dr[di], cc + dc[di]
            if cr == n or cc == n or res[cr][cc] != 0:
                cr, cc = cr - dr[di], cc - dc[di]
                di = (di + 1) % 4
                cr, cc = cr + dr[di], cc + dc[di]

        return res
# @lc code=end


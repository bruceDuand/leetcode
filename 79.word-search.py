#
# @lc app=leetcode id=79 lang=python3
#
# [79] Word Search
#

# @lc code=start
class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        if not board:
            return False

        m, n = len(board), len(board[0])
        self.used = [[False for _ in range(n)] for _ in range(m)]
    
        self.res = False
        def dfs(board, cr, cc, wordi):
            if wordi == len(word): return True
            if cr == -1 or cc == -1 or cr == m or cc == n or self.used[cr][cc] or board[cr][cc] != word[wordi]:
                return False

            self.used[cr][cc] = True
            if(dfs(board, cr-1, cc, wordi+1)): return True
            if(dfs(board, cr+1, cc, wordi+1)): return True
            if(dfs(board, cr, cc-1, wordi+1)): return True
            if(dfs(board, cr, cc+1, wordi+1)): return True
            self.used[cr][cc] = False
        
        for i in range(m):
            for j in range(n):
                if dfs(board, i, j, 0): return True
            
        return False

# @lc code=end


# the answer is almost the same as mine, but is more cleaner
# no wonder that is quicker, I should pay attention to code logic
# in the future, there are many conditions that could be simplified

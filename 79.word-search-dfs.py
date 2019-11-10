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
        self.dr = [0, 1, 0, -1]
        self.dc = [1, 0, -1, 0]  
    
        self.res = False
        def dfs(board, temp, cr, cc, wordi):
            if self.res == True:
                return
            if ''.join(temp) == word:
                print(''.join(temp))
                self.res = True
                return 
            for i in range(4):
                cr = cr + self.dr[i]
                cc = cc + self.dc[i]
                # print("cr=", str(cr), " cc=", str(cc), " wordi=", str(wordi)," temp", str(temp))
                if cr == -1 or cc == -1 or cr == m or cc == n:
                    cr = cr - self.dr[i]
                    cc = cc - self.dc[i]
                elif self.used[cr][cc] == True:
                    # print("used")
                    cr = cr - self.dr[i]
                    cc = cc - self.dc[i]
                else:               
                    if board[cr][cc] == word[wordi]:
                        self.used[cr][cc] = True
                        temp.append(word[wordi])
                        dfs(board, temp, cr, cc, wordi+1)
                        self.used[cr][cc] = False
                        temp.pop()
                        cr = cr - self.dr[i]
                        cc = cc - self.dc[i]
                    else:
                        cr = cr - self.dr[i]
                        cc = cc - self.dc[i]
        
        for i in range(m):
            for j in range(n):
                if board[i][j] == word[0]:
                    self.used = [[False for _ in range(n)] for _ in range(m)]
                    self.used[i][j] = True
                    dfs(board, [word[0]], i, j, 1)
                    if self.res == True:
                        return True
        
        return False

# @lc code=end


# A B C E
# S F E S
# A D E E

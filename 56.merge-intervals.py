#
# @lc app=leetcode id=56 lang=python3
#
# [56] Merge Intervals
#

# @lc code=start
class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        if len(intervals) == 0:
            return []
        intervals.sort(key=lambda x: x[0])
        # intervals = self.merge_sort(intervals, 0, len(intervals)-1)

        res = []
        for i in range(0,len(intervals)):
            if not res or intervals[i][0] > res[-1][1]:
                res.append(intervals[i])
            elif intervals[i][0] <= res[-1][1] and intervals[i][1] > res[-1][1]:
                res[-1][1] = intervals[i][1]
        return res
    
    def merge_sort(self, intervals: List[List[int]], p, r) -> List[List[int]]:
        if p < r:
            q = int((p+r)/2)
            intervals = self.merge_sort(intervals, p, q)
            intervals = self.merge_sort(intervals, q+1, r)
            intervals = self.merge_interval(intervals, p, q, r)
        
        return intervals


    def merge_interval(self, intervals: List[List[int]], p, q, r) -> List[List[int]]:
        Llist = intervals[p:q+1]
        Rlist = intervals[q+1:r+1]
        Llist.append([999,999])
        Rlist.append([999,999])

        i = 0
        j = 0
        for k in range(p, r+1):
            if Llist[i][0] <= Rlist[j][0]: 
                intervals[k] = Llist[i]
                i += 1
            else:
                intervals[k] = Rlist[j]
                j += 1
        return intervals
# @lc code=end

# note
# intervals.sort(key=lambda x: x[0]) is much quicker than
# merge sort, I do not know why
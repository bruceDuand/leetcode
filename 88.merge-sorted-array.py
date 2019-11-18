#
# @lc app=leetcode id=88 lang=python3
#
# [88] Merge Sorted Array
#

# @lc code=start
class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        end = m + n - 1
        m = m - 1
        n = n - 1
        while n != -1:
            if m == -1:
                nums1[0:end+1] = nums2[0:n+1]
                n = -1
            else:
                if nums1[m] >= nums2[n]:
                    nums1[end] = nums1[m]
                    m   -= 1            
                else:
                    nums1[end] = nums2[n]
                    n   -= 1
                end -= 1
            

        
# @lc code=end


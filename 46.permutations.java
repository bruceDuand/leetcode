import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * @lc app=leetcode id=46 lang=java
 *
 * [46] Permutations
 */

// @lc code=start
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(nums, 0, res);
        return res;
    }

    private void dfs(int[] nums, int start, List<List<Integer>> res) {
        if (start == nums.length - 1) {
            List<Integer> tmp = new ArrayList<>();
            for (int num : nums) {
                tmp.add(num);
            }
            res.add(tmp);

            return;
        }

        for (int i = start; i < nums.length; i++) {
            swap(nums, i, start);
            dfs(nums, start + 1, res);
            swap(nums, i, start);
        }

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public List<List<Integer>> permute2(int[] nums) {
        Queue<List<Integer>> queue = new LinkedList<List<Integer>>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length; i++) {
            int quene_length = queue.size();
            for (int j = 0; j < quene_length; j++) {
                List<Integer> list = queue.poll();
                for (int k = 0; k <= list.size(); k++) {
                    list.add(k, nums[i]);
                    queue.add(new ArrayList<Integer>(list));
                    list.remove(k);
                }
            }

            if (quene_length == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                queue.add(list);
            }
        }

        int length = queue.size();
        for (int i = 0; i < length; i++) {
            res.add(new ArrayList(queue.poll()));

        }

        return res;
    }
}
// @lc code=end

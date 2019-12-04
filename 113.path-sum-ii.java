import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=113 lang=java
 *
 * [113] Path Sum II
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        if (root == null)
            return res;

        temp.add(root.val);
        checkPath(root, sum, 0, res, temp);
        return res;

    }

    private void checkPath(TreeNode node, int target, int sum, List<List<Integer>> res, List<Integer> temp) {
        sum = sum + node.val;
        if (sum == target && node.left == null && node.right == null) {
            res.add(new ArrayList(temp));
            return;
        }
        if (node.left != null) {
            temp.add(node.left.val);
            checkPath(node.left, target, sum, res, temp);
            temp.remove(temp.size() - 1);
        }
        if (node.right != null) {
            temp.add(node.right.val);
            checkPath(node.right, target, sum, res, temp);
            temp.remove(temp.size() - 1);
        }

        return;

    }
}
// @lc code=end

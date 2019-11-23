import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.tree.TreeNode;
/*
 * @lc app=leetcode id=107 lang=java
 *
 * [107] Binary Tree Level Order Traversal II
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);

        while (!q.isEmpty()) {
            int length = q.size();
            List<Integer> temp = new ArrayList<Integer>();
            for (int i = 0; i < length; i++) {
                TreeNode cnode = q.remove();
                temp.add(cnode.val);
                if (cnode.left != null)
                    q.add(cnode.left);
                if (cnode.right != null)
                    q.add(cnode.right);
            }
            res.add(0, temp);
        }
        return res;

    }
}
// @lc code=end

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=111 lang=java
 *
 * [111] Minimum Depth of Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        int height = 1;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            int length = q.size();

            for (int i = 0; i < length; i++) {
                TreeNode curNode = q.remove();
                if (curNode.left == null && curNode.right == null)
                    return height;
                if (curNode.left != null)
                    q.add(curNode.left);
                if (curNode.right != null)
                    q.add(curNode.right);
            }
            height += 1;
        }
        return 0;
    }
}
// @lc code=end

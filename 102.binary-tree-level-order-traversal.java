import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.tree.TreeNode;
/*
 * @lc app=leetcode id=102 lang=java
 *
 * [102] Binary Tree Level Order Traversal
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int level_size = 0;
        
        if(root == null) return res;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        level_size = 1;

        while(!q.isEmpty()) {
            List<Integer> temp = new ArrayList<Integer>();
            int temp_count = 0;
            
            for(int i=0; i<level_size; i++) {
                TreeNode cnode = q.remove();
                temp.add(cnode.val);
                if(cnode.left != null) {
                    temp_count += 1;
                    q.add(cnode.left);
                }

                if(cnode.right != null) {
                    temp_count += 1;
                    q.add(cnode.right);
                }
            }

            res.add(temp);
            level_size = temp_count;
        }

        return res;
    }
}
// @lc code=end


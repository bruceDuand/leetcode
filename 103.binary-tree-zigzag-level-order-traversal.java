import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.tree.TreeNode;
/*
 * @lc app=leetcode id=103 lang=java
 *
 * [103] Binary Tree Zigzag Level Order Traversal
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        boolean reflag = false;

        while(!q.isEmpty()) {
            int length = q.size();
            List<Integer> temp = new ArrayList<Integer>();
            
            for(int i=0; i<length; i++) {
                TreeNode cnode = q.remove();
                temp.add(cnode.val);
                if(cnode.left != null)
                    q.add(cnode.left);
                if(cnode.right != null)
                    q.add(cnode.right);
            }

            if(reflag)
                for(int i=0; i<temp.size(); i++)
                    temp.add(i, temp.remove(temp.size()-1));
            reflag = !reflag;

            res.add(temp);
        }

        return res;
    }
}
// @lc code=end


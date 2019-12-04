# Depth First Search

The DFS algorithm is a recursive algorithm, it involves exhaustive searches for all the nodes by going ahead, if possible, else by backtracking. If there are no more nodes along the current path, you move backwards on the same path, and find another path to traverse. 

The algorithm can be implemented using stacks, and this is an **iterative way** of implementation:

1. Pick a starting node and push all the adjacent nodes into a stack.
2. Pop a node from the stack as the current working node, and push all the adjacent nodes of the current one into the stack.
3. Repeat the process, while marking the visited nodes to avoid infinite loop.

Also, the algorithm can implemented by **recursion**:

1. Pick the starting node.
2. Visit the current working node.
3. For all the adjacent nodes of the current working one, repeat step 2.

## 99. Recover Binary Search Tree

```
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.
```

There are many methods of solving the problem. 

### 1. Reucrsion i

The first is using recursion and the inorder traversal of the tree. **The inorder traversal is an extremely useful method of solving BST problem, since the inorder traversal is well ordered.** In this problem, only two elements are swapped, so inorder traversal can help to find the two elements.

```java
    public void recoverTree(TreeNode root) {
        if(root==null)
            return;

        List<TreeNode> inorder = new ArrayList<TreeNode>();
        inorderTraversal(root, inorder);
        TreeNode first = null;
        TreeNode second = null;
        for(int i=1; i<inorder.size(); i++){
            if(inorder.get(i).val > inorder.get(i-1).val)
                continue;
            if (first == null) first = inorder.get(i-1);
            second = inorder.get(i);
        }

        if(first == null) return;
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
        
    }

    public void inorderTraversal(TreeNode node, List<TreeNode> inorder){
        if(node==null)
            return;
        inorderTraversal(node.left, inorder);
        inorder.add(node);
        inorderTraversal(node.right, inorder);
    }
```


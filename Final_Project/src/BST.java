public class BST {

    BSTNode delete(BSTNode root, BSTNode node) {
        if (root == null)
            return root;

        if (node.value < root.value)
            root.left = delete(root.left, node);
        else if (node.value >= root.value && !node.name.equals(root.name))
            root.right = delete(root.right, node);

        else if(node.name.equals(root.name)){
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            BSTNode temp = minValue(root.right);
            root.value = temp.value;
            root.name = temp.name;

            root.right = delete(root.right, node);
        }

        return root;
    }

    BSTNode minValue(BSTNode root) {
        BSTNode min = root;
        while (root.left != null) {
            min = root.left;
            root = root.left;
        }
        return min;
    }

    BSTNode insert(BSTNode root, BSTNode node) {
        if (root == null) {
            root = node;
            return root;
        }

        if (node.value < root.value)
            root.left = insert(root.left, node);
        else
            root.right = insert(root.right, node);

        return root;
    }

    boolean search(BSTNode root, BSTNode node) {
        if (root == null) {
            return false;
        }
        if (node.value == root.value && node.name.equals(root.name)) {
            return true;
        }
        if (node.value < root.value) {
            return search(root.left, node);
        } else {
            return search(root.right, node);
        }
    }

    void inOrder(BSTNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inOrder(root.left);
        }
        System.out.println(root.value);
        if (root.right != null) {
            inOrder(root.right);
        }
    }

    void mostBrs(BSTNode root) {
        if (root == null) {
            return;
        }
        while (root.right != null) {
            root = root.right;
        }
        System.out.println(root.name);
    }

}

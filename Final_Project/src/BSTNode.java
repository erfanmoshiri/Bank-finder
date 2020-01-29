public class BSTNode {
    BSTNode left;
    BSTNode right;
    int value;
    String name;

    public BSTNode(int value, String name) {
        this.value = value;
        this.name = name;
        left = right = null;
    }
}


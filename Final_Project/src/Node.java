public class Node {

    Node left;
    Node right;
    String name;
    int[] coo;
    boolean isB = false;
    String mainB;

    public Node(String name, int[] coo) {
        this.name = name;
        this.coo = coo;
        this.left = null;
        this.right = null;
    }


    public Node(int[] coo) {
        this.coo = coo;
    }

}

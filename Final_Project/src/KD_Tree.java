public class KD_Tree {

    private static final int K = 2;

    Node insert(Node root, Node node, int d) {

        if (root == null) {
            return node;
        }
        int l = d % K;
        if (node.coo[l] < root.coo[l]) {
            root.left = insert(root.left, node, d + 1);
        } else {
            root.right = insert(root.right, node, d + 1);
        }

        return root;
    }

    boolean search(Node root, int[] coo, int d) {

        if (root == null) {
            return false;
        }
        if (root.coo[0] == coo[0] && root.coo[1] == coo[1]) {
            return true;
        }
        int l = d % K;
        if (coo[l] < root.coo[l]) {
            return search(root.left, coo, d + 1);
        }
        return search(root.right, coo, d + 1);
    }

    Node findMin(Node root, int e, int d) {

        if (root == null) {
            return null;
        }
        int l = d % K;

        if (l == e) {
            if (root.left == null) {
                return root;
            }
            return findMin(root.left, e, d + 1);
        }
        return Min(root, findMin(root.left, e, d + 1), findMin(root.right, e, d + 1), d);
    }

    Node Min(Node a, Node b, Node c, int d) {

        Node temp = a;
        if (b != null && b.coo[d] < temp.coo[d]) {
            temp = b;
        }
        if (c != null && c.coo[d] < temp.coo[d]) {
            temp = c;
        }
        return temp;

    }

    Node deleteNode(Node root, int coo1[], int d) {

        if (root == null) {
            return null;
        }

        int l = d % K;

        if (root.coo[0] == coo1[0] && root.coo[1] == coo1[1]) {
            if (root.right != null) {
                Node min = findMin(root.right, l, 0);
                root.coo[0] = min.coo[0];
                root.coo[1] = min.coo[1];
                root.isB = min.isB;
                if (min.name != null)
                    root.name = min.name;
                if (min.mainB != null)
                    root.mainB = min.mainB;
                root.right = deleteNode(root.right, min.coo, d + 1);

            } else if (root.left != null) {
                Node min = findMin(root.left, l, 0);
                root.coo[0] = min.coo[0];
                root.coo[1] = min.coo[1];
                root.isB = min.isB;
                if (min.name != null)
                    root.name = min.name;
                if (min.mainB != null)
                    root.mainB = min.mainB;
                root.right = deleteNode(root.left, min.coo, d + 1);
            } else {
                root = null; //??
                return root;
            }
            return root;
        }

        if (coo1[l] < root.coo[l]) {
            root.left = deleteNode(root.left, coo1, d + 1);
        } else {
            root.right = deleteNode(root.right, coo1, d + 1);
        }
        return root;
    }

    boolean isB(Node root, int[] coo, int d) {

        if (root == null) {
            return false;
        }
        if (root.coo[0] == coo[0] && root.coo[1] == coo[1]) {
            return root.isB;
        }
        int l = d % K;
        if (coo[l] < root.coo[l]) {
            return isB(root.left, coo, d + 1);
        }
        return isB(root.right, coo, d + 1);
    }

    void findInRec(Node root, int[][] coo, int d) {

        if (root == null) {
            return;
        }
        int l = d % K;

        if (root.coo[0] >= coo[0][0] && root.coo[0] <= coo[0][1] && root.coo[1] >= coo[1][0] && root.coo[1] <= coo[1][1]) {
            System.out.println("(" + root.coo[0] + ", " + root.coo[1] + ") : " + root.name);
            findInRec(root.right, coo, d + 1);
            findInRec(root.left, coo, d + 1);
        } else {
            if (root.coo[l] >= coo[l][0] && root.coo[l] <= coo[l][1]) {
                findInRec(root.right, coo, d + 1);
                findInRec(root.left, coo, d + 1);
            } else if (root.coo[l] >= coo[l][0] && root.coo[l] >= coo[l][1]) {
                findInRec(root.left, coo, d + 1);
            } else if (root.coo[l] <= coo[l][0] && root.coo[l] <= coo[l][1]) {
                findInRec(root.right, coo, d + 1);
            }
        }
    }

    Object[] findNearest(Node root, int[] coo, Object[] dis, int d) {

        Object[] o = new Object[4];
        o[0] = dis[0];
        o[1] = dis[1];
        o[2] = dis[2];
        o[3] = dis[3];

        if (root == null) {
            return o;
        }
        double newDis = Math.sqrt(((root.coo[0] - coo[0]) * (root.coo[0] - coo[0])) + ((root.coo[1] - coo[1]) * (root.coo[1] - coo[1])));
        if (Double.compare(newDis, (double)dis[0]) == -1) {
            dis[0] = o[0] = newDis;
            dis[1] = o[1] = root.name;
            dis[2] = o[2] = root.coo[0];
            dis[3] = o[3] = root.coo[1];
        }
        int l = d % K;
        if (coo[l] < root.coo[l] && root.left != null) {
            o = findNearest(root.left, coo, dis, d + 1);
            if (Double.compare((double)o[0], (double)dis[0]) == 0 && root.right != null) {
                o = findNearest(root.right, coo, dis, d + 1);
            }
        } else if (root.right != null) {
            o = findNearest(root.right, coo, dis, d + 1);
            if (Double.compare((double)o[0], (double)dis[0]) == 0 && root.left != null) {
                o = findNearest(root.left, coo, dis, d + 1);
            }
        }
        return o;
    }

    void print(Node root) {
        if (root == null) {
            return;
        }
        if(root.left != null) {
            print(root.left);
        }
        System.out.print("(" + root.coo[0] + ", " + root.coo[1] + ") ");
        if(root.right != null)
            print(root.right);
    }

    String[] searchName(Node root, int[] coo, int d) {

        if (root == null) {
            return null;
        }
        if (root.coo[0] == coo[0] && root.coo[1] == coo[1]) {
            String[] s = new String[2];
            s[0] = root.name;
            s[1] = root.mainB;
            return s;
        }
        int l = d % K;
        if (coo[l] < root.coo[l]) {
            return searchName(root.left, coo, d + 1);
        }
        return searchName(root.right, coo, d + 1);
    }

    void findInCircle(Node root, int[] coo, double r, int d) {

        if (root == null) {
            return;
        }
        double dis = Math.sqrt( (root.coo[0] - coo[0])*(root.coo[0] - coo[0]) + (root.coo[1] - coo[1])*(root.coo[1] - coo[1]));
        if (Double.compare(dis, r) == -1) {
            System.out.println(root.name + " : (" + root.coo[0] + ", " + root.coo[1] + ")");
            findInCircle(root.left, coo, r, d + 1);
            findInCircle(root.right, coo, r, d + 1);
        } else {
            int l = d % K;
            if (coo[l] < root.coo[l]) {
                findInCircle(root.left, coo, r, d + 1);
            } else {
                findInCircle(root.right, coo, r, d + 1);
            }
        }
    }

    Object[] findNearestBranch(Node root, int[] coo, Object[] dis, String Bname, int d) {

        Object[] o = new Object[4];
        o[0] = dis[0];
        o[1] = dis[1];
        o[2] = dis[2];
        o[3] = dis[3];

        if (root == null) {
            return o;
        }
        if(root.mainB.equals(Bname)) {
            double newDis = Math.sqrt(((root.coo[0] - coo[0]) * (root.coo[0] - coo[0])) + ((root.coo[1] - coo[1]) * (root.coo[1] - coo[1])));
            if (Double.compare(newDis, (double) dis[0]) == -1) {
                dis[0] = o[0] = newDis;
                dis[1] = o[1] = root.name;
                dis[2] = o[2] = root.coo[0];
                dis[3] = o[3] = root.coo[1];
            }
        }
        int l = d % K;
        if (coo[l] < root.coo[l] && root.left != null) {
            o = findNearestBranch(root.left, coo, dis, Bname, d + 1);
            if (Double.compare((double)o[0], (double)dis[0]) == 0 && root.right != null) {
                o = findNearestBranch(root.right, coo, dis, Bname, d + 1);
            }
        } else if (root.right != null) {
            o = findNearestBranch(root.right, coo, dis, Bname, d + 1);
            if (Double.compare((double)o[0], (double)dis[0]) == 0 && root.left != null) {
                o = findNearestBranch(root.left, coo, dis, Bname, d + 1);
            }
        }
        return o;
    }

//    Object[] sample(double a, String s) {
//
//    }

}

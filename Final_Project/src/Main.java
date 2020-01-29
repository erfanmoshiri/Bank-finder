import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TrieTree tt = new TrieTree();
        NeighbourhoodTrieTree nt = new NeighbourhoodTrieTree();

        Trie R = tt.initRoot();
        NTrie NR = nt.initRoot();

        KD_Tree kdTree = new KD_Tree();
        BST bst = new BST();
        MyStack<String> lastActions = new MyStack<>();

        int[] b = new int[2];
        b[0] = 6;
        b[1] = 7;
        Node root = null;
        BSTNode bstNode = null;


        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input;
            input = scanner.next();

            if (input.equals("addN")) {
                System.out.print("enter neighbourhood's name : ");
                String name = scanner.next();
                if (!legalName(name)) {
                    System.out.println("illegal input!");
                    continue;
                }
                if (nt.search(name)) {
                    System.out.println("name already taken!");
                } else {
                    System.out.println("enter 4 coordinates : ");
                    int[][] coo1 = new int[4][2];
                    String[] s0 = new String[2];
                    for (int i = 0; i < 4; i++) {
                        s0[0] = scanner.next();
                        s0[1] = scanner.next();
                        if (!legalCoo(s0)) {
                            System.out.println("illegal input!");
                            continue;
                        }
                        coo1[i][0] = Integer.parseInt(s0[0]);
                        coo1[i][1] = Integer.parseInt(s0[1]);
                    }
                    nt.add(name, coo1);
                    System.out.println("neighbourhood added.");
                }
            }
            //////////////////////////////
            else if (input.equals("findN")) {
                System.out.print("enter neighbourhood's name : ");
                String s = scanner.next();
                if (!legalName(s)) {
                    System.out.println("illegal input!");
                    continue;
                }
                if (nt.search(s)) {
                    System.out.println(nt.findCoo(s)[0][0] + ", " + nt.findCoo(s)[0][1]);
                }
            }
            //////////////////////////////
            else if (input.equals("addB")) {
                System.out.print("enter the name: ");
                String name = scanner.next();
                if (!legalName(name)) {
                    System.out.println("illegal input!");
                    continue;
                }
                if (tt.search(name)) {
                    System.out.println("name already taken!");
                    continue;
                } else {
                    System.out.print("enter the coordinate: ");
                    int[] coo = new int[2];
                    scanner.nextLine();
                    String[] s0 = scanner.nextLine().split(" ");
                    if (!legalCoo(s0)) {
                        System.out.println("illegal input!");
                        continue;
                    }
                    coo[0] = Integer.parseInt(s0[0]);
                    coo[1] = Integer.parseInt(s0[1]);
                    if (!kdTree.search(root, coo, 0)) {
                        Node node = new Node(name, coo);
                        node.isB = true;
                        root = kdTree.insert(root, node, 0);
                        tt.add(name, true);
                        bstNode = bst.insert(bstNode, new BSTNode(0, name));                 /////////////////
                        System.out.println("bank added.");

                    } else {
                        System.out.println("the coordinate is already taken!");
                        continue;
                    }
                }
            } else if (input.equals("addBr")) {
                System.out.print("enter the name: ");
                String name = scanner.next();
                if (!legalName(name)) {
                    System.out.println("illegal input!");
                    continue;
                }
                if (tt.search(name)) {
                    System.out.println("name already taken!");
                    continue;
                } else {
                    System.out.print("enter the bank name: ");
                    String bname = scanner.next();
                    if (!legalName(bname)) {
                        System.out.println("illegal input!");
                        continue;
                    }
                    if (!tt.ismain(bname)) {
                        System.out.println("bank not found!");
                        continue;
                    } else {
                        int size = tt.listBranches(bname).size();                  ////////////////////////
                        bstNode = bst.delete(bstNode, new BSTNode(size, bname));
                        bstNode = bst.insert(bstNode, new BSTNode(++size, bname));
                        System.out.print("enter the coordinate: ");
                        int[] coo = new int[2];
                        scanner.nextLine();
                        String[] s0 = scanner.nextLine().split(" ");
                        if (!legalCoo(s0)) {
                            System.out.println("illegal input!");
                            continue;
                        }
                        coo[0] = Integer.parseInt(s0[0]);
                        coo[1] = Integer.parseInt(s0[1]);
                        if (!kdTree.search(root, coo, 0)) {
                            Node node = new Node(name, coo);
                            node.isB = false;
                            node.mainB = bname;
                            root = kdTree.insert(root, node, 0);
                            tt.add(name, false);
                            tt.addToBranches(bname, name);
                            System.out.println("branch added.");

                        } else {
                            System.out.println("the coordinate is already taken!");
                            continue;
                        }
                    }
                }
            } else if (input.equals("delBr")) {
                System.out.print("enter the coordinate: ");
                int[] coo = new int[2];
                scanner.nextLine();
                String[] s0 = scanner.nextLine().split(" ");
                if (!legalCoo(s0)) {
                    System.out.println("illegal input!");
                    continue;
                }
                coo[0] = Integer.parseInt(s0[0]);
                coo[1] = Integer.parseInt(s0[1]);
                if (kdTree.search(root, coo, 0)) {
                    if (!kdTree.isB(root, coo, 0)) {
                        String[] names = kdTree.searchName(root, coo, 0);
                        if (names != null) {
                            int size = tt.listBranches(names[1]).size();             ////////////////////////
                            bstNode = bst.delete(bstNode, new BSTNode(size, names[1]));
                            bstNode = bst.insert(bstNode, new BSTNode((++size), names[1]));
                            tt.delete(names[0]);
                            tt.removeFromBranches(names);
                        }
                        root = kdTree.deleteNode(root, coo, 0);
                    } else {
                        System.out.println("it's a main bank!");
                        continue;
                    }
                } else {
                    System.out.println("coordinate not found!");
                }
                System.out.println("branch deleted.");
            } else if (input.equals("listBrs")) {
                System.out.print("enter the bank name: ");
                String name = scanner.next();
                if (!legalName(name)) {
                    System.out.println("illegal input!");
                    continue;
                }
                if (tt.search(name)) {
                    MyStack<String> myStack = tt.listBranches(name);
                    MyStack<String> temp = new MyStack<>();
                    System.out.println("branches: ");
                    while (!myStack.isEmpty()) {
                        System.out.print(myStack.peek() + ", ");
                        temp.push(myStack.pop());
                    }
                    System.out.println("\n");
                    while (!temp.isEmpty()) {
                        myStack.push(temp.pop());
                    }
                } else {
                    System.out.println("bank not found!");
                }
            } else if (input.equals("listB")) {
                System.out.print("enter the neighbourhood's name: ");
                String s = scanner.next();
                if (!legalName(s)) {
                    System.out.println("illegal input!");
                    continue;
                }
                if (nt.search(s)) {
                    int[][] Ncoo;
                    int[][] compact = new int[2][2];
                    Ncoo = nt.findCoo(s);
                    compact[0][0] = Ncoo[0][0];
                    compact[0][1] = Ncoo[1][0];
                    compact[1][0] = Ncoo[2][1];
                    compact[1][1] = Ncoo[0][1];
                    kdTree.findInRec(root, compact, 0);
                } else {
                    System.out.println("neighbourhood not found!");

                }
            } else if (input.equals("nearB")) {
                System.out.println("enter a coordinate: ");
                int[] coo = new int[2];
                scanner.nextLine();
                String[] s0 = scanner.nextLine().split(" ");
                if (!legalCoo(s0)) {
                    System.out.println("illegal input!");
                    continue;
                }
                coo[0] = Integer.parseInt(s0[0]);
                coo[1] = Integer.parseInt(s0[1]);
                Object[] temp = new Object[4];
                temp[0] = Double.POSITIVE_INFINITY;
                temp[1] = "";
                Object[] o;
                o = kdTree.findNearest(root, coo, temp, 0);
                System.out.println("nearest bank: " + (String) o[1] + ": (" + (int) o[2] + ", " + (int) o[3] + ")");
                /////////////////////////////////////////////////
            } else if (input.equals("print")) {
                kdTree.print(root);
                /////////////////////////////////////////////////
            } else if (input.equals("availB" +
                    "")) {
                System.out.print("enter a coordinate: ");
                int[] coo = new int[2];
                scanner.nextLine();
                String[] s0 = scanner.nextLine().split(" ");
                if (!legalCoo(s0)) {
                    System.out.println("illegal input!");
                    continue;
                }
                coo[0] = Integer.parseInt(s0[0]);
                coo[1] = Integer.parseInt(s0[1]);
                System.out.print("enter a radius: ");
                double r = scanner.nextDouble();
                kdTree.findInCircle(root, coo, r, 0);
            } else if (input.equals("nearBr")) {
                System.out.print("enter a coordinate: ");
                int[] coo = new int[2];
                scanner.nextLine();
                String[] s0 = scanner.nextLine().split(" ");
                if (!legalCoo(s0)) {
                    System.out.println("illegal input!");
                    continue;
                }
                coo[0] = Integer.parseInt(s0[0]);
                coo[1] = Integer.parseInt(s0[1]);
                if (!kdTree.search(root, coo, 0)) {
                    System.out.println("coordinate not found!");
                } else {
                    System.out.print("enter the bank name: ");
                    String name = scanner.next();
                    if (!legalName(name)) {
                        System.out.println("illegal input!");
                        continue;
                    }
                    if (tt.ismain(name)) {
                        Object[] temp = new Object[4];
                        temp[0] = Double.POSITIVE_INFINITY;
                        temp[1] = "";
                        Object[] o;
                        o = kdTree.findNearestBranch(root, coo, temp, name, 0);
                        System.out.println("nearest branch: " + (String) o[1] + ": (" + (int) o[2] + ", " + (int) o[3] + ")");
                    } else {
                        System.out.println("it's not a bank!");
                    }
                }
                /////////////////////////////////////////////////
            } else if (input.equals("add")) {
                String s = scanner.next();
                if (!legalName(s)) {
                    System.out.println("illegal input!");
                    continue;
                }
                int i = scanner.nextInt();
                BSTNode bstNode1 = new BSTNode(i, s);
                bstNode = bst.insert(bstNode, bstNode1);
                System.out.println(bst.search(bstNode, bstNode1));

            } else if (input.equals("delete")) {
                String s = scanner.next();
                if (!legalName(s)) {
                    System.out.println("illegal input!");
                    continue;
                }
                int i = scanner.nextInt();
                BSTNode f = new BSTNode(i, s);
                bstNode = bst.delete(bstNode, f);
            } else if (input.equals("search")) {
                String s = scanner.next();
                if (!legalName(s)) {
                    System.out.println("illegal input!");
                    continue;
                }
                int i = scanner.nextInt();
                BSTNode f = new BSTNode(i, s);
                System.out.println(bst.search(bstNode, f));
                bst.inOrder(bstNode);
                //////////////////////////////////////////////////
            }else if (input.equals("mostBrs")) {
                if (bstNode == null) {
                    System.out.println("no bank found!");
                }else {
                    System.out.print("the bank with the most branches: ");
                    bst.mostBrs(bstNode);
                }
            }
        }
    }

    static boolean legalName(String s) {
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (!(c >= 'a' && c <= 'z')) {
                return false;
            }
        }
        return true;
    }
    static boolean legalCoo(String[] s) {
        if (!(s[0].charAt(0) >= '0' && s[0].charAt(0) <= '9')) {
            return false;
        }
        if (!(s[1].charAt(0) >= '0' && s[1].charAt(0) <= '9')) {
            return false;
        }
        return true;
    }
}

public class Trie {

    private static final int ALPHA = 26;
    boolean isEnd;
    boolean isB = false;
    MyStack<String> branches;
    Trie[] aTries = new Trie[ALPHA];


    public Trie() {
        isEnd = false;
        for (int i = 0; i < ALPHA; i++) {
            aTries[i] = null;
        }
    }
}
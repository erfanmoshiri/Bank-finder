public class NTrie {

    private static final int ALPHA = 26;
    boolean isEnd;
    int[][] coo;
    NTrie[] aTries = new NTrie[ALPHA];

    public NTrie() {
        isEnd = false;
        for (int i = 0; i < ALPHA; i++) {
            aTries[i] = null;
        }
    }
}

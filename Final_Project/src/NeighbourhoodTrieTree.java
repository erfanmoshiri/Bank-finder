public class NeighbourhoodTrieTree {

    static NTrie root;


    NTrie initRoot() {
        return root = new NTrie();
    }

    void add(String s, int[][] coos) {

        int l = s.length();
        NTrie t = root;
        int index;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.aTries[index] == null) {
                t.aTries[index] = new NTrie();
            }
            t = t.aTries[index];
        }
        t.isEnd = true;
        t.coo = coos;
    }

    boolean search(String s) {

        int l = s.length();
        int index;
        NTrie t = root;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.aTries[index] == null) {
                return false;
            }
            t = t.aTries[index];
        }
        if (t != null && t.isEnd) {
            return true;
        }
        return false;
    }

    boolean delete(String s) {

        int l = s.length();
        int index;
        NTrie t = root;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.aTries[index] == null) {
                return false;
            }
            t = t.aTries[index];
        }
        if (t != null && t.isEnd) {
            t.isEnd = false;
            return true;
        }
        return false;
    }

    int[][] findCoo(String s) {

        int l = s.length();
        int index;
        NTrie t = root;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.aTries[index] == null) {
                return null;
            }
            t = t.aTries[index];
        }
        if (t != null && t.isEnd) {
            return t.coo;
        }
        return null;
    }
}

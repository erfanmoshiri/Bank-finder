public class TrieTree {

    static Trie root;

    Trie initRoot() {
        return root = new Trie();
    }

    void add(String s, boolean isB) {

        int l = s.length();
        Trie t = root;
        int index;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.aTries[index] == null) {
                t.aTries[index] = new Trie();
            }
            t = t.aTries[index];
        }
        t.isEnd = true;
        if (isB) {
            t.isB = isB;
            t.branches = new MyStack<>();
        }
    }

    boolean search(String s) {

        int l = s.length();
        int index;
        Trie t = root;
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
        Trie t = root;
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


    public void addToBranches(String s, String name) {
        int l = s.length();
        Trie t = root;
        int index;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.aTries[index] == null) {
                t.aTries[index] = new Trie();
            }
            t = t.aTries[index];
        }
        t.branches.push(name);
    }
    MyStack<String> listBranches(String s) {

        int l = s.length();
        int index;
        Trie t = root;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.aTries[index] == null) {
                return null;
            }
            t = t.aTries[index];
        }
        if (t != null && t.isEnd) {
            return t.branches;
        }
        return null;
    }
    boolean ismain(String s) {

        int l = s.length();
        int index;
        Trie t = root;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.aTries[index] == null) {
                return false;
            }
            t = t.aTries[index];
        }
        if (t != null && t.isEnd && t.isB) {
            return true;
        }
        return false;
    }

    boolean removeFromBranches(String[] ss) {
        String s = ss[1];
        int l = s.length();
        Trie t = root;
        int index;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.aTries[index] == null) {
                t.aTries[index] = new Trie();
            }
            t = t.aTries[index];
        }
        MyStack<String> temp = new MyStack<>();
        while (!t.branches.isEmpty()){
            if (t.branches.peek().equals(ss[0])) {
                t.branches.pop();
            } else {
                temp.push(t.branches.pop());
            }
        }
        while (!temp.isEmpty()) {
            t.branches.push(temp.pop());
        }
        return true;
    }
}


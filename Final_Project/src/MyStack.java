class MyStack<T> {
    static int MAX = 20;

    Object[] elements = new Object[MAX];
    int index;

    MyStack() {
        index = 0;
    }

    boolean isEmpty() {
        if (index == 0)
            return true;
        return false;
    }

    void push(T element) {
        if (index >= MAX) {
            int MAX1 = MAX * 2;
            MAX = MAX1;
            Object[] elements1 = new Object[MAX];
            for (int i = 0; i < index; i++) {
                elements1[i] = element;
            }
            elements = elements1;
        }
        elements[index] = element;
        index++;
    }

    T pop() {
        index--;
        T t = (T) elements[index];
        return t;
    }

    int size() {
        return index;
    }

    T peek() {
        T t = (T) elements[index - 1];
        return t;
    }
}
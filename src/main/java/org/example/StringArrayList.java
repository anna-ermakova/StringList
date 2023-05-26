package org.example;

public class StringArrayList implements StringList {
    private String[] data;
    private int currentSize;

    public StringArrayList(int size) {
        data = new String[size];
        currentSize = 0;
    }

    public StringArrayList(String... args) {
        data = new String[args.length];
        System.arraycopy(args, 0, data, 0, args.length);
        currentSize = data.length;
    }

    @Override
    public String add(String item) {
        if (currentSize == data.length) {
            resize(currentSize + 1);
        }
        data[currentSize] = item;
        currentSize++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkBounds(index);
        if (currentSize == data.length) {
            resize(currentSize + 1);
        }
        for (int i = currentSize; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = item;
        currentSize++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkBounds(index);
        data[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        checkBounds(index);
        String result = data[index];
        for (int i = index + 1; i < currentSize; i++) {
            data[i - 1] = data[i];
        }
        currentSize--;
        return result;
    }

    @Override
    public boolean contains(String item) {
        return false;
    }

    @Override
    public int indexOf(String item) {
        return 0;
    }

    @Override
    public int lastIndexOf(String item) {
        return 0;
    }

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public boolean equals(StringList other) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= currentSize) {
            throw new StringIndexOutOfBoundsException();
        }
    }

    private void resize(int newSize) {
        int size = currentSize * 2;
        size = Math.max(size, newSize);
        String[] newData = new String[size];
        System.arraycopy(data, 0, newData, 0, currentSize);
        data = newData;
    }
}

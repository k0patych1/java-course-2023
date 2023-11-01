package edu.hw3.task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private int positionPointer;

    private final List<T> listOfObjs;

    public BackwardIterator(Collection<T> collection) {
        positionPointer = collection.size();
        this.listOfObjs = collection.stream().toList();
    }

    @Override
    public boolean hasNext() {
        return positionPointer > 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return listOfObjs.get(--positionPointer);
    }
}
